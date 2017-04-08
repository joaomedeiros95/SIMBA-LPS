package br.ufrn.simba.seguranca;

import br.ufrn.simba.utils.Propriedades;

/**
 * Created by joao on 04/04/17.
 */
public class MedidasSeguranca {

    private static final String[] MEDIDAS_SEGURANCA =
            Propriedades.pegarPropriedade("medidas_seguranca_habilitadas").split(",");

    private static boolean verificaMedidaHabilitada(final String medida) {
        for (String medidaSeguranca : MEDIDAS_SEGURANCA) {
            if (medidaSeguranca.equals(medida)) {
                return true;
            }
        }

        return false;
    }

}
