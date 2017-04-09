package br.ufrn.simba.monitoramento;

import br.ufrn.simba.comunicacao.HTTPRequester;
import br.ufrn.simba.utils.Propriedades;

import java.io.IOException;

/**
 * Created by joao on 09/04/17.
 */
public class Bateria {

    private static final int NIVEL_SEGURO_BATERIA =
            Integer.parseInt(Propriedades.pegarPropriedade("bateria_nivel_seguro"));

    public static boolean analisarNivelBateria() throws IOException {
        final String nivelBateria =
                HTTPRequester.getInstance().get(Boolean.parseBoolean(Propriedades.pegarPropriedade("bateria_analogico")),
                        Integer.parseInt(Propriedades.pegarPropriedade("bateria_porta"))).getResposta();

        return Integer.parseInt(nivelBateria) < NIVEL_SEGURO_BATERIA;
    }

}
