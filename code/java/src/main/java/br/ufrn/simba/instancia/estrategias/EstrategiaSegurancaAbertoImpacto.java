package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.dispositivo.seguranca.Bollands;
import br.ufrn.simba.dispositivo.seguranca.Sirene;
import br.ufrn.simba.instancia.Instancia;
import br.ufrn.simba.model.Estado;
import br.ufrn.simba.seguranca.NotificacaoEmail;
import br.ufrn.simba.seguranca.NotificacaoPolicia;
import br.ufrn.simba.seguranca.NotificacaoSlack;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 10/06/17.
 */
public class EstrategiaSegurancaAbertoImpacto extends EstrategiaSegurancaAberto {

    public EstrategiaSegurancaAbertoImpacto() {
        this.addAlerta(new NotificacaoEmail());
        this.addAlerta(new Sirene(false, 5));
        this.addAlerta(new Bollands(false, 8));
        this.addAlerta(new NotificacaoPolicia());
        this.addAlerta(new NotificacaoSlack());
    }

    @Override
    public void execute(List<Estado> estados) throws IOException, EmailException {
        for (final Estado estado : estados) {
            if (estado.getHash() == Instancia.sensorImpactoHash) {
                if (estado.getValor() >= 1) {
                    notificar(estados);
                }
            }
        }
    }
}
