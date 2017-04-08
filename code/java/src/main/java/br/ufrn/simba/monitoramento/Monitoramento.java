package br.ufrn.simba.monitoramento;

import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.dispositivo.sensor.Movimento;
import br.ufrn.simba.model.AtividadeSensor;
import br.ufrn.simba.utils.Propriedades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public class Monitoramento {

    private static final String[] dispositivos =
            Propriedades.pegarPropriedade("sensores_habilitados").split(",");

    private static final Dispositivo sensorMovimento = new Movimento();

    public static List<AtividadeSensor> analisarDispositivos() throws IOException {
        final List<AtividadeSensor> resultado = new ArrayList<AtividadeSensor>();
        if (verificaSensorHabilitado(Movimento.NOME)) {
            resultado.add(new AtividadeSensor(AtividadeSensor.SENSOR_MOVIMENTO, sensorMovimento.receberDados()));
        }

        return resultado;
    }

    private static boolean verificaSensorHabilitado(final String nomeDispostivo) {
        for (String dispositivo : dispositivos) {
            if (dispositivo.equals(nomeDispostivo)) {
                return true;
            }
        }

        return false;
    }

}
