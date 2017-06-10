package br.ufrn.simba;

import br.ufrn.simba.model.Estado;
import br.ufrn.simba.model.MonitorEstrategiaSeguranca;
import br.ufrn.simba.seguranca.EstrategiaSeguranca;
import br.ufrn.simba.utils.Propriedades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by joao on 04/04/17.
 */
public class Controlador {

    private static final int INTERVALO_SENSOR =
            Integer.parseInt(Propriedades.pegarPropriedade("intervalo_sensor"));

    private static final Logger LOGGER = LogManager.getLogger(Controlador.class);
    private final List<MonitorEstrategiaSeguranca> monitores;

    private class ControladorThread implements Callable<Integer> {

        public Integer call() throws Exception {
            monitorar();

            return null;
        }

        private void monitorar() {
            try {
                for (final MonitorEstrategiaSeguranca monitor : monitores) {
                    final List<Estado> estados = monitor.getMonitorEvento().analisarDispositivos();

                    // Chama estratégias de segurança cadastradas
                    for (final EstrategiaSeguranca estrategiaSeguranca : monitor.getEstrategiasSeguranca()) {
                        estrategiaSeguranca.execute(estados);
                    }
                }
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Um erro ocorreu ao analisar os dispositivos");
                }
            }
        }
    }

    public Controlador(final List<MonitorEstrategiaSeguranca> monitorEstrategiaSegurancas) {
        this.monitores = monitorEstrategiaSegurancas;
    }

    public void iniciarMonitoramento() {
        final ExecutorService threadpool =
                Executors.newFixedThreadPool(Integer.parseInt(Propriedades.pegarPropriedade("num_threads")));
        while (true) {
            final ControladorThread controladorThread = new ControladorThread();
            threadpool.submit(controladorThread);
            try {
                Thread.sleep(INTERVALO_SENSOR);
            } catch (InterruptedException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Um erro ocorreu ao analisar os dispositivos");
                }
            }
        }
    }
}
