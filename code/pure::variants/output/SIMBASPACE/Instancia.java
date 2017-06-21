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
	// 
    // 
    // 
    // 
    public static long cameraMovimentoHash;
    // 
    

    public static void main(String[] args) {
        final MonitorEvento monitorEvento = new MonitorEvento();
        
        // 
        
        // 
        
        // 
        
        // 

        // 
        final CameraMovimento cameraMovimento = new CameraMovimento();
        cameraMovimentoHash = CameraMovimento.HASH;
        monitorEvento.addDispositivo(cameraMovimento);
        // 
        
                   
        

        final MonitorEstrategiaSeguranca monitor =
                new MonitorEstrategiaSeguranca(monitorEvento, null);
        // 
        
        // 
        
        // 
        
        // 
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaFechadoCamera());
        // 
        
        // 
       

        final List<MonitorEstrategiaSeguranca> monitores = new ArrayList<>();
        monitores.add(monitor);

        final Controlador controlador = new Controlador(monitores);
        controlador.iniciarMonitoramento();
    }

}
