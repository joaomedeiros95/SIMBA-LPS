package br.ufrn.simba;

import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.dispositivo.sensor.Movimento;
import br.ufrn.simba.monitoramento.Monitoramento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 25/04/17.
 */
public class Instancia {

    public static void main(String[] args) {
        final Controlador controlador = new Controlador(new MonitoramentoImpl());
        controlador.iniciarMonitoramento();
    }

    static class MonitoramentoImpl extends Monitoramento {

        public List<Dispositivo> dispositivosAtivos() {
            List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
            dispositivos.add(new Movimento(13, false));
            return null;
        }
    }
}
