package br.ufrn.simba.seguranca;

import br.ufrn.simba.dispositivo.seguranca.Sirene;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.utils.Propriedades;
import org.apache.commons.mail.EmailException;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public class MedidasSeguranca {

    private static final String[] MEDIDAS_SEGURANCA =
            Propriedades.pegarPropriedade("medidas_seguranca_habilitadas").split(",");

    private static final Alerta sirene = new Sirene();
    private static final Alerta email = new NotificacaoEmail();

    public static void acionarMedida(TipoDispositivo tipoDispositivo) throws IOException, EmailException {
        if (verificaMedidaHabilitada(Sirene.NOME)) {
            sirene.acionarAlerta(tipoDispositivo);
        }

        if (verificaMedidaHabilitada(NotificacaoEmail.NOME)) {
            email.acionarAlerta(tipoDispositivo);
        }
    }

    private static boolean verificaMedidaHabilitada(final String medida) {
        for (String medidaSeguranca : MEDIDAS_SEGURANCA) {
            if (medidaSeguranca.equals(medida)) {
                return true;
            }
        }

        return false;
    }

}
