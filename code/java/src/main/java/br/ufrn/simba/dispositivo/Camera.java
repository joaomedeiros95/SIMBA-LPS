package br.ufrn.simba.dispositivo;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

/**
 * Created by joao on 04/04/17.
 */
public class Camera implements WebcamMotionListener {

    public Camera() {
        WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
        detector.setInterval(500); // one check per 500 ms
        detector.addMotionListener(this);
        detector.start();
    }

    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {
        System.out.println("Movimento Detectado");
    }
}
