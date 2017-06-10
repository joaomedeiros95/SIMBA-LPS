package br.ufrn.simba.instancia;

import br.ufrn.simba.Controlador;
import br.ufrn.simba.dispositivo.sensor.Calor;
import br.ufrn.simba.dispositivo.sensor.Impacto;
import br.ufrn.simba.dispositivo.sensor.Som;
import br.ufrn.simba.instancia.estrategias.EstrategiaSegurancaAbertoCalor;
import br.ufrn.simba.instancia.estrategias.EstrategiaSegurancaAbertoImpacto;
import br.ufrn.simba.instancia.estrategias.EstrategiaSegurancaAbertoSom;
import br.ufrn.simba.model.MonitorEstrategiaSeguranca;
import br.ufrn.simba.monitoramento.MonitorEvento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 25/04/17.
 */
public class Instancia {

    public static long sensorImpactoHash;
    public static long sensorCalorHash;
    public static long sensorSomHash;

    public static void main(String[] args) {
        final MonitorEvento monitorEvento = new MonitorEvento();
        final Impacto impacto = new Impacto(13, false,
                "Sensor de Impacto", 1);
        final Calor calor = new Calor(10, false, "Sensor de Calor", 2);
        final Som som = new Som(3, true, "Sensor de Som", 3);
        sensorImpactoHash = impacto.getHash();
        sensorCalorHash = calor.getHash();
        sensorSomHash = som.getHash();

        monitorEvento.addDispositivo(impacto);
        monitorEvento.addDispositivo(calor);
        monitorEvento.addDispositivo(som);

        final MonitorEstrategiaSeguranca monitor =
                new MonitorEstrategiaSeguranca(monitorEvento, null);
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoSom());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoCalor());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoImpacto());

        final List<MonitorEstrategiaSeguranca> monitores = new ArrayList<>();
        monitores.add(monitor);

        final Controlador controlador = new Controlador(monitores);
        controlador.iniciarMonitoramento();
    }

}
