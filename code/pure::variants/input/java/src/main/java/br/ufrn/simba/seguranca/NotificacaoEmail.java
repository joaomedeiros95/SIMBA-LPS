package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.Estado;
import br.ufrn.simba.utils.Propriedades;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public class NotificacaoEmail extends Notificacao {

    private static final String[] EMAILS =
            Propriedades.pegarPropriedade("emails_notificacao").split(",");
    private static final String ASSUNTO = Propriedades.pegarPropriedade("email_assunto");
    private static final Logger LOGGER = LogManager.getLogger(NotificacaoEmail.class);

    public void acionarAlerta(final List<Estado> estados) throws EmailException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enviando email");
        }

        this.estadosSensores = estados;

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(Propriedades.pegarPropriedade("mail_hostname"));
        email.setSmtpPort(Integer.parseInt(Propriedades.pegarPropriedade("smtp_port")));

        final String username = Propriedades.pegarPropriedade("mail_username");
        final String password = Propriedades.pegarPropriedade("mail_password");
        email.setAuthenticator(new DefaultAuthenticator(username, password));

        email.setSSLOnConnect(true);
        email.setFrom(Propriedades.pegarPropriedade("mail_from"));
        email.setSubject(ASSUNTO + " " + NOME_BANCO);
        email.setMsg(montarEmail());
        email.addTo(EMAILS);

        email.send();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Email enviado");
        }
    }

    private String montarEmail() {
        final StringBuilder corpoEmail = new StringBuilder();
        corpoEmail.append("Banco: ").append(NOME_BANCO);
        corpoEmail.append("\n========= DISPOSITIVOS ACIONADOS =========");
        for (final Estado estado : this.estadosSensores) {
            corpoEmail.append("\nNome: ").append(estado.getNome());
            corpoEmail.append("\nData: ").append(estado.getData());
            corpoEmail.append("\nValor: ").append(estado.getValor());
        }

        return corpoEmail.toString();
    }
}
