package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.dispositivo.seguranca.Sirene;
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
public class EstrategiaSegurancaAbertoCalor extends EstrategiaSegurancaAberto {

    public EstrategiaSegurancaAbertoCalor() {
    	// PV:IFCOND(pv:hasFeature('email'))
        this.addAlerta(new NotificacaoEmail());
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('sms'))
        this.addAlerta(new NotificacaoSlack());
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('sirenes'))
        this.addAlerta(new Sirene(false, 5));
        // PV:ENDCOND
    }

    @Override
    public void execute(List<Estado> estados) throws IOException, EmailException {
        for (final Estado estado : estados) {
        	// PV:IFCOND(pv:hasFeature('calor'))
        	if (estado.getHash() == Instancia.sensorCalorHash) {
                if (estado.getValor() == 1) {
                    notificar(estados);
                }
            }
        	// PV:ENDCOND
        }
    }
}
