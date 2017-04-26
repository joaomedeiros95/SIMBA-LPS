package br.ufrn.simba.monitoramento;

import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.dispositivo.sensor.Movimento;
import br.ufrn.simba.model.AtividadeSensor;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.utils.Propriedades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public abstract class Monitoramento {

    public List<AtividadeSensor> analisarDispositivos() throws IOException {
        final List<AtividadeSensor> resultado = new ArrayList<AtividadeSensor>();
        final List<Dispositivo> dispositivosMonitoramento = dispositivosAtivos();

        for (Dispositivo dispositivo : dispositivosMonitoramento) {
            resultado.add(new AtividadeSensor(dispositivo.receberDados()));
        }

        return resultado;
    }

    public abstract List<Dispositivo> dispositivosAtivos();

}
