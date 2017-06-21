package br.ufrn.simba.dispositivo;

import br.ufrn.simba.model.Estado;
import br.ufrn.simba.utils.Propriedades;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * Created by joao on 04/04/17.
 */
public class CameraMovimento implements WebcamMotionListener, Dispositivo {

    private int intervaloCamera = Integer.parseInt(Propriedades.pegarPropriedade("intervalo_camera"));
    private static final Logger LOGGER = LogManager.getLogger(CameraMovimento.class);
    private boolean movimentoDetectado = false;
    public static final long HASH = 234567654;

    public CameraMovimento() {
        WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
        detector.setInterval(intervaloCamera); // one check per 500 ms
        detector.addMotionListener(this);
        detector.start();
    }

    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Movimento Detectado!");
        }
        this.movimentoDetectado = true;
    }

    public void acionarModoEconomia() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Acionando modo de economia da Câmera!");
        }

        intervaloCamera = Integer.parseInt(Propriedades.pegarPropriedade("intervalo_camera_bateria"));
    }

    public void acionarModoNormal() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Acionando modo normal da Câmera!");
        }

        intervaloCamera = Integer.parseInt(Propriedades.pegarPropriedade("intervalo_camera_bateria"));
    }

    @Override
    public Estado recuperarEstado() throws IOException {
        try {
            final boolean retorno = this.movimentoDetectado;

            if (retorno) {
                return new Estado(new Date(), "Sensor de Câmera", 1, HASH);
            } else {
                return new Estado(new Date(), "Sensor de Câmera", 0, HASH);
            }
        } finally {
            this.movimentoDetectado = false;
        }
    }
}
