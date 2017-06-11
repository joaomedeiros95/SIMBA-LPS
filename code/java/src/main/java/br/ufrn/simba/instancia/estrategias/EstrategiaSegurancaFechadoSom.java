package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.instancia.Instancia;
import br.ufrn.simba.model.Estado;
import br.ufrn.simba.seguranca.NotificacaoEmail;
import br.ufrn.simba.seguranca.NotificacaoSlack;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 10/06/17.
 */
public class EstrategiaSegurancaFechadoSom extends EstrategiaSegurancaFechado {

    public EstrategiaSegurancaFechadoSom() {
        this.addAlerta(new NotificacaoEmail());
        this.addAlerta(new NotificacaoSlack());
    }

    @Override
    public void execute(List<Estado> estados) throws IOException, EmailException {
        for (final Estado estado : estados) {
            if (estado.getHash() == Instancia.sensorSomHash) {
                if (estado.getValor() > 600) {
                    notificar(estados);
                }
            }
        }
    }

}
