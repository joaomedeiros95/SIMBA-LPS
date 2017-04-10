package br.ufrn.simba.dispositivo;

import br.ufrn.simba.Controlador;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.utils.Propriedades;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by joao on 04/04/17.
 */
public class CameraMovimento implements WebcamMotionListener {

    private int intervaloCamera = Integer.parseInt(Propriedades.pegarPropriedade("intervalo_camera"));
    private static final Logger LOGGER = LogManager.getLogger(CameraMovimento.class);

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
        Controlador.acionarMedidaSeguranca(TipoDispositivo.CAMERA);
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
}
