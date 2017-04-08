package br.ufrn.simba;

import br.ufrn.simba.dispositivo.DetectorMovimento;
import br.ufrn.simba.model.AtividadeSensor;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.monitoramento.Monitoramento;
import br.ufrn.simba.seguranca.NotificacaoEmail;
import br.ufrn.simba.utils.Propriedades;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public class Controlador {

    private static final int INTERVALO_SENSOR =
            Integer.parseInt(Propriedades.pegarPropriedade("intervalo_sensor"));

    public static void monitorar() {
        try {
            final List<AtividadeSensor> sensores = Monitoramento.analisarDispositivos();
            for (AtividadeSensor sensor : sensores) {
                System.out.println("Tipo Dispositivo: " + sensor.getTipoDispositivo().getNome());
                System.out.println("Status Code: " + sensor.getValor().getStatusCode());
                System.out.println(sensor.getValor().getResposta());
            }
        } catch (IOException e) {
            // TODO: adicionar tratamento e melhorar log
            e.printStackTrace();
            System.out.println("Um erro ocorreu ao analisar os dispositivos");
        }
    }

    public static void acionarMedidaSeguranca(TipoDispositivo tipoDispositivo) {
        try {
            new NotificacaoEmail().acionarAlerta(tipoDispositivo);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Acionar detector de movimento
        new DetectorMovimento();

        while (true) {
            monitorar();
            try {
                Thread.sleep(INTERVALO_SENSOR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
