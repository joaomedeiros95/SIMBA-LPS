package br.ufrn.simba;

import br.ufrn.simba.dispositivo.CameraMovimento;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.monitoramento.Bateria;
import br.ufrn.simba.monitoramento.MonitorEvento;
import br.ufrn.simba.utils.Propriedades;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
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
    private CameraMovimento detectorMovimento;
    private MonitorEvento monitorEvento;

    private class ControladorThread implements Callable<Integer> {

        public Integer call() throws Exception {
            monitorar();
            analisarRedundanciaEletrica();

            return null;
        }

        private void monitorar() {
            try {
                final List<AtividadeSensor> sensores = monitorEvento.analisarDispositivos();
                for (AtividadeSensor sensor : sensores) {
                    if (sensor.isOfereceRisco()) {
                        acionarMedidaSeguranca(TipoDispositivo.SENSOR_MOVIMENTO);
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Oferece risco: " + sensor.isOfereceRisco());
                    }
                }
            } catch (IOException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Um erro ocorreu ao analisar os dispositivos");
                }
            }
        }

        private void analisarRedundanciaEletrica() throws IOException {
            if (Bateria.analisarNivelBateria()) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("Rodando em modo de economia de energia");
                }
                detectorMovimento.acionarModoEconomia();
            } else {
                detectorMovimento.acionarModoNormal();
            }
        }
    }

    public static void acionarMedidaSeguranca(TipoDispositivo tipoDispositivo) {
        try {
            MedidasSeguranca.acionarMedida(tipoDispositivo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public Controlador(MonitorEvento monitorEvento) {
        // Acionar detector de movimento
        this.detectorMovimento = new CameraMovimento();
        this.monitorEvento = monitorEvento;
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
