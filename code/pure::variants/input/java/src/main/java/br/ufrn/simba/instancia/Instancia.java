package br.ufrn.simba.instancia;

import br.ufrn.simba.Controlador;
import br.ufrn.simba.dispositivo.CameraMovimento;
import br.ufrn.simba.dispositivo.sensor.Calor;
import br.ufrn.simba.dispositivo.sensor.Impacto;
import br.ufrn.simba.dispositivo.sensor.Movimento;
import br.ufrn.simba.dispositivo.sensor.Som;
import br.ufrn.simba.instancia.estrategias.*;
import br.ufrn.simba.model.MonitorEstrategiaSeguranca;
import br.ufrn.simba.monitoramento.MonitorEvento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 25/04/17.
 */
public class Instancia {

	// PV:IFCOND(pv:hasFeature('impacto'))
	public static long sensorImpactoHash;
	// PV:ENDCOND
	// PV:IFCOND(pv:hasFeature('calor'))
    public static long sensorCalorHash;
    // PV:ENDCOND
    // PV:IFCOND(pv:hasFeature('som'))
    public static long sensorSomHash;
    // PV:ENDCOND
    // PV:IFCOND(pv:hasFeature('movimento'))
    public static long sensorMovimentoHash;
    // PV:ENDCOND
    // PV:IFCOND(pv:hasFeature('camera_unica'))
    public static long cameraMovimentoHash;
    // PV:ENDCOND
    

    public static void main(String[] args) {
        final MonitorEvento monitorEvento = new MonitorEvento();
        
        // PV:IFCOND(pv:hasFeature('impacto'))
        final Impacto impacto = new Impacto(2, false,
                "Sensor de Impacto", 1);
        sensorImpactoHash = impacto.getHash();
        monitorEvento.addDispositivo(impacto);
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('calor'))
        final Calor calor = new Calor(10, false, "Sensor de Calor", 2);
        sensorCalorHash = calor.getHash();
        monitorEvento.addDispositivo(calor);
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('som'))
        final Som som = new Som(3, true, "Sensor de Som", 3);
        sensorSomHash = som.getHash();
        monitorEvento.addDispositivo(som);
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('movimento'))
        final Movimento movimento = new Movimento(12, false, "Sensor de Movimento", 4);
        sensorMovimentoHash = movimento.getHash();
        monitorEvento.addDispositivo(movimento);
        // PV:ENDCOND

        // PV:IFCOND(pv:hasFeature('camera_unica'))
        final CameraMovimento cameraMovimento = new CameraMovimento();
        cameraMovimentoHash = CameraMovimento.HASH;
        monitorEvento.addDispositivo(cameraMovimento);
        // PV:ENDCOND
        
                   
        

        final MonitorEstrategiaSeguranca monitor =
                new MonitorEstrategiaSeguranca(monitorEvento, null);
        // PV:IFCOND(pv:hasFeature('som'))
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoSom());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoSom());
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('calor'))
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoCalor());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoCalor());
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('movimento'))
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoMovimento());
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('camera_unica'))
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoCamera());
        // PV:ENDCOND
        
        // PV:IFCOND(pv:hasFeature('impacto'))
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoImpacto());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoImpacto());
        // PV:ENDCOND
       

        final List<MonitorEstrategiaSeguranca> monitores = new ArrayList<>();
        monitores.add(monitor);

        final Controlador controlador = new Controlador(monitores);
        controlador.iniciarMonitoramento();
    }

}
