package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.Estado;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 10/06/17.
 */
public class NotificacaoPolicia extends Notificacao {

    @Override
    public void acionarAlerta(List<Estado> estados) throws EmailException, IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("============= ATENÇÃO =============");
        stringBuilder.append("\nNotificação Policial");

        for (final Estado estado : estados) {
            stringBuilder.append("\n==========================");
            stringBuilder.append("\nNome: ").append(estado.getNome());
            stringBuilder.append("\nData: ").append(estado.getData());
            stringBuilder.append("\nValor: ").append(estado.getValor());
            stringBuilder.append("\n==========================");
        }
    }
}
