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

	// 
	public static long sensorImpactoHash;
	// 
	// 
    // 
    public static long sensorSomHash;
    // 
    // 
    public static long sensorMovimentoHash;
    // 
    // 
    public static long cameraMovimentoHash;
    // 
    

    public static void main(String[] args) {
        final MonitorEvento monitorEvento = new MonitorEvento();
        
        // 
        final Impacto impacto = new Impacto(2, false,
                "Sensor de Impacto", 1);
        sensorImpactoHash = impacto.getHash();
        monitorEvento.addDispositivo(impacto);
        // 
        
        // 
        
        // 
        final Som som = new Som(3, true, "Sensor de Som", 3);
        sensorSomHash = som.getHash();
        monitorEvento.addDispositivo(som);
        // 
        
        // 
        final Movimento movimento = new Movimento(12, false, "Sensor de Movimento", 4);
        sensorMovimentoHash = movimento.getHash();
        monitorEvento.addDispositivo(movimento);
        // 

        // 
        final CameraMovimento cameraMovimento = new CameraMovimento();
        cameraMovimentoHash = CameraMovimento.HASH;
        monitorEvento.addDispositivo(cameraMovimento);
        // 
        
                   
        

        final MonitorEstrategiaSeguranca monitor =
                new MonitorEstrategiaSeguranca(monitorEvento, null);
        // 
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoSom());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoSom());
        // 
        
        // 
        
        // 
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoMovimento());
        // 
        
        // 
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoCamera());
        // 
        
        // 
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaAbertoImpacto());
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoImpacto());
        // 
       

        final List<MonitorEstrategiaSeguranca> monitores = new ArrayList<>();
        monitores.add(monitor);

        final Controlador controlador = new Controlador(monitores);
        controlador.iniciarMonitoramento();
    }

}
