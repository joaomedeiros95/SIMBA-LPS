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
    private int porta;
    private boolean analogico;

    public Sensor(int porta, boolean analogico) {
        this.porta = porta;
        this.analogico = analogico;
    }

    public boolean receberDados() throws IOException {
        final HTTPRequester.RespostaHTTP resposta =
                HTTPRequester.getInstance().get(isAnalogico(), getPorta());
        final Integer valor = Integer.parseInt(resposta.getResposta());
        return checarValor(valor);
    }

    abstract boolean checarValor(Integer valor);

    public int getPorta() {
        return porta;
    }

    public boolean isAnalogico() {
        return analogico;
    }
}
