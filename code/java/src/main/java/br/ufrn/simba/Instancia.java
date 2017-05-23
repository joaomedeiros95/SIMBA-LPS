package br.ufrn.simba;

import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.dispositivo.sensor.Som;
import br.ufrn.simba.monitoramento.MonitorEvento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 25/04/17.
 */
public class Instancia {

    public static void main(String[] args) {
        final Controlador controlador = new Controlador(new MonitorEvento());
        controlador.iniciarMonitoramento();
    }

}
