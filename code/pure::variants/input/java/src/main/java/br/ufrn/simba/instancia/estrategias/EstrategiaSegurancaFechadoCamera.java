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
public class EstrategiaSegurancaFechadoCamera extends EstrategiaSegurancaFechado {

    public EstrategiaSegurancaFechadoCamera() {
    	// PV:IFCOND(pv:hasFeature('email'))
        this.addAlerta(new NotificacaoEmail());
        // PV:ENDCOND
        // PV:IFCOND(pv:hasFeature('sirenes'))
        this.addAlerta(new Sirene(false, 5));
        // PV:ENDCOND
        // PV:IFCOND(pv:hasFeature('autoridades'))
        this.addAlerta(new NotificacaoPolicia());
        // PV:ENDCOND
        // PV:IFCOND(pv:hasFeature('sms'))
        this.addAlerta(new NotificacaoSlack());
        // PV:ENDCOND
    }

    @Override
    public void execute(List<Estado> estados) throws IOException, EmailException {
        boolean notificar = false;
        for (final Estado estado : estados) {
        	// PV:IFCOND(pv:hasFeature('movimento'))
            if (estado.getHash() == Instancia.sensorMovimentoHash) {
                notificar = estado.getValor() == 1;
            }
            // PV:ENDCOND
            // PV:IFCOND(pv:hasFeature('camera_unica'))
            if (notificar && estado.getHash() == Instancia.cameraMovimentoHash) {
                notificar = estado.getValor() == 1;
            }
            // PV:ENDCOND
        }

        if (notificar) {
            notificar(estados);
        }
    }
}
