package br.ufrn.simba.dispositivo;

import br.ufrn.simba.Controlador;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.utils.Propriedades;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

/**
 * Created by joao on 04/04/17.
 */
public class DetectorMovimento implements WebcamMotionListener {

    private static final int INTERVALO =
            Integer.parseInt(Propriedades.pegarPropriedade("intervalo_camera"));

    public DetectorMovimento() {
        WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
        detector.setInterval(INTERVALO); // one check per 500 ms
        detector.addMotionListener(this);
        detector.start();
    }

    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {
        Controlador.acionarMedidaSeguranca(TipoDispositivo.CAMERA);
    }
}
