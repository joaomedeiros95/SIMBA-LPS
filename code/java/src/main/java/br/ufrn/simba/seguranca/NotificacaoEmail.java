package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.utils.Propriedades;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 * Created by joao on 04/04/17.
 */
public class NotificacaoEmail extends Notificacao {

    private static final String[] EMAILS =
            Propriedades.pegarPropriedade("emails_notificacao").split(",");
    private static final String ASSUNTO = Propriedades.pegarPropriedade("email_assunto");

    public void acionarAlerta(TipoDispositivo tipoDispositivo) throws EmailException {
        System.out.println("Enviando email");
        this.tipoDispotivo = tipoDispositivo.getNome();

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
        System.out.println("Email enviado");
    }

    private String montarEmail() {
        final StringBuilder corpoEmail = new StringBuilder();
        corpoEmail.append("Banco: " + NOME_BANCO);
        corpoEmail.append("\nDispositivo: " + tipoDispotivo);
        corpoEmail.append("\nHora do ocorrido: " + pegarHoraAtual());

        if (informacaoAdicional != null && informacaoAdicional != "") {
            corpoEmail.append("\nMais Informações: " + informacaoAdicional);
        }

        return corpoEmail.toString();
    }
}
