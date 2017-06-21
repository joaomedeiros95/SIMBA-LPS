package br.ufrn.simba.seguranca;

import br.ufrn.simba.comunicacao.HTTPRequester;
import br.ufrn.simba.model.Estado;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public class NotificacaoSlack extends Notificacao {

    @Override
    public void acionarAlerta(List<Estado> estados) throws EmailException, IOException {
        final StringBuilder alerta = new StringBuilder();

        alerta.append("-------------- Notificação de Segurança --------------");
        alerta.append("\nVocê tem um novo alerta de segurança no banco ").append(NOME_BANCO);

        for (final Estado estado : estados) {
            alerta.append("\n==========================");
            alerta.append("\nNome: ").append(estado.getNome());
            alerta.append("\nData: ").append(estado.getData());
            alerta.append("\nValor: ").append(estado.getValor());
            alerta.append("\n==========================");
        }

        HTTPRequester.getInstance().post(alerta.toString());
    }
}
