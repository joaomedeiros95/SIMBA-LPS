package br.ufrn.simba.comunicacao;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.misc.IOUtils;

import java.io.*;

/**
 * Created by joao on 08/04/17.
 */
public class HTTPRequester {

    private static final String ARDUINO_IP = System.getenv().get("ARDUINO_IP");
    private static final String ARUINO_PATH = "arduino";

    public RespostaHTTP get(final boolean analogico, final int porta) throws IOException {
        return this.get(analogico, porta, -1);
    }

    public RespostaHTTP get(final boolean analogico, final int porta, final int value)
            throws IOException {
        final CloseableHttpClient clienteHTTP = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(montarURL(analogico, porta, value));
        final CloseableHttpResponse resposta = clienteHTTP.execute(httpGet);

        try {
            final int statusCode = resposta.getStatusLine().getStatusCode();
            final String conteudoString =
                    getStringFromInputStream(resposta.getEntity().getContent());

            return new RespostaHTTP(statusCode, conteudoString);
        } finally {
            resposta.close();
        }
    }

    private String montarURL(boolean analogico, int porta, int value) {
        final String tipoPorta = analogico ? "analog" : "digital";
        final String valor = value >= 0 ? ("/" + value) : "";
        return ARDUINO_IP + "/" + ARUINO_PATH + "/" + tipoPorta + "/" + porta + valor;
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

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getResposta() {
            return resposta;
        }

        public void setResposta(String resposta) {
            this.resposta = resposta;
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
