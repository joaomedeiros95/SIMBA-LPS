package br.ufrn.simba.dispositivo.sensor;

import br.ufrn.simba.comunicacao.HTTPRequester;
import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.utils.Propriedades;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public abstract class Sensor implements Dispositivo {

    private static final String ANALOGICO = "analog";
    private static final String DIGITAL = "digital";
    private static final String SENSOR = "sensor";
    private static final String PORTA = "porta";
    private static final String TIPO = "tipo";

    public HTTPRequester.RespostaHTTP receberDados() throws IOException {
        return HTTPRequester.getInstance().get(analogico(), pegarPorta());
    }

    private int pegarPorta() {
        return Integer.parseInt(
                Propriedades.pegarPropriedade(SENSOR + "_" + pegarNome() + "_" + PORTA ));
    }

    private String pegarTipo() {
        return Propriedades.pegarPropriedade(SENSOR + "_" + pegarNome() + "_" + TIPO);
    }

    abstract String pegarNome();

    private boolean analogico() {
        final String s = pegarTipo();

        return s.equals(ANALOGICO);
    }

}
