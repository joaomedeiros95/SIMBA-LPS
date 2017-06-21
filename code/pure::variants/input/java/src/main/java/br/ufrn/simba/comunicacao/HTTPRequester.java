package br.ufrn.simba.comunicacao;

import br.ufrn.simba.utils.Propriedades;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by joao on 08/04/17.
 */
public class HTTPRequester {

    private static final String ARDUINO_IP = Propriedades.pegarPropriedade("arduino_ip_ethernet");
    private static final String ARDUINO_IP_FALLBACK = Propriedades.pegarPropriedade("arduino_ip_wifi");
    private static final String SLACK_URL = Propriedades.pegarPropriedade("slack_url");
    private static final String CONTENT_TYPE_JSON = Propriedades.pegarPropriedade("application/json");
    private static final String ARUINO_PATH = "arduino";
    private static final int TIMEOUT_CONEXA0 = Integer.parseInt(Propriedades.pegarPropriedade("timeout_conexao"));
    private static HTTPRequester instance;
    private static final Logger LOGGER = LogManager.getLogger(HTTPRequester.class);

    public static HTTPRequester getInstance() {
        if (instance == null) {
            instance = new HTTPRequester();
        }

        return instance;
    }

    public RespostaHTTP get(final boolean analogico, final int porta) throws IOException {
        return this.get(analogico, porta, -1);
    }

    public RespostaHTTP get(final boolean analogico, final int porta, final int value) throws IOException {
        return this.get(analogico, porta, value, ARDUINO_IP);
    }

    public int post(final String mensagem) throws IOException {
        return post(SLACK_URL, CONTENT_TYPE_JSON, mensagem);
    }

    private int post(final String url, final String contentType, final String mensagem) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enviando POST request para " + url);
        }

        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(TIMEOUT_CONEXA0)
                .setConnectTimeout(TIMEOUT_CONEXA0)
                .setSocketTimeout(TIMEOUT_CONEXA0)
                .build();

        final CloseableHttpClient clienteHTTP = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", contentType);

        final String conteudo = "{\"text\":\"" + mensagem + "\"}";
        final HttpEntity entity = new ByteArrayEntity(conteudo.getBytes());
        httpPost.setEntity(entity);

        final CloseableHttpResponse resposta = clienteHTTP.execute(httpPost);

        return resposta.getStatusLine().getStatusCode();
    }

    private RespostaHTTP get(final boolean analogico, final int porta, final int value, final String ip)
            throws IOException {
        final String url = this.montarURL(ip, analogico, porta, value);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enviando GET request para " + url);
        }

        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(TIMEOUT_CONEXA0)
                .setConnectTimeout(TIMEOUT_CONEXA0)
                .setSocketTimeout(TIMEOUT_CONEXA0)
                .build();

        final CloseableHttpClient clienteHTTP = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse resposta = null;
        try {
            resposta = clienteHTTP.execute(httpGet);

            final int statusCode = resposta.getStatusLine().getStatusCode();
            final String conteudoString =
                    getStringFromInputStream(resposta.getEntity().getContent());

            return new RespostaHTTP(statusCode, conteudoString);
        } catch (ConnectTimeoutException e) {
            // Checa se já está usando o fallback
            if (!ip.equals(ARDUINO_IP_FALLBACK)) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Usando IP de fallback");
                }
                return get(analogico, porta, value, ARDUINO_IP_FALLBACK);
            }

            throw e;
        } finally {
            if (resposta != null) {
                resposta.close();
            }
        }
    }

    private String montarURL(final String ip, final boolean analogico, final int porta, final int value) {
        final String tipoPorta = analogico ? "analog" : "digital";
        final String valor = value >= 0 ? ("/" + value) : "";
        return ip + "/" + ARUINO_PATH + "/" + tipoPorta + "/" + porta + valor;
    }

    public class RespostaHTTP {
        private int statusCode;
        private String resposta;

        public RespostaHTTP(int statusCode, String resposta) {
            this.statusCode = statusCode;
            this.resposta = resposta;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getResposta() {
            return resposta;
        }

    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}
