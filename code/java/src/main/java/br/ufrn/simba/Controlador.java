package br.ufrn.simba;

import br.ufrn.simba.dispositivo.Camera;
import br.ufrn.simba.model.AtividadeSensor;
import br.ufrn.simba.monitoramento.Monitoramento;
import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public class Controlador {

    public static void monitorar() {
        try {
            final List<AtividadeSensor> sensores = Monitoramento.analisarDispositivos();
            for (AtividadeSensor sensor : sensores) {
                System.out.println("Tipo Dispositivo: " + sensor.getTipoDispositivo());
                System.out.println("Status Code: " + sensor.getValor().getStatusCode());
                System.out.println(sensor.getValor().getResposta());
            }
        } catch (IOException e) {
            // TODO: adicionar tratamento e melhorar log
            e.printStackTrace();
            System.out.println("Um erro ocorreu ao analisar os dispositivos");
        }
    }

    public static void main(String[] args) throws IOException {
//        monitorar();
        new Camera();
        System.in.read();
    }

}
