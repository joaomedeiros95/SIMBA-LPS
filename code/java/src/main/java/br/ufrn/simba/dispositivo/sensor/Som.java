package br.ufrn.simba.dispositivo.sensor;

/**
 * Created by joao on 04/04/17.
 */
public class Som extends Sensor {
    public Som(int porta, boolean analogico) {
        super(porta, analogico);
    }

    boolean checarValor(Integer valor) {
        return false;
    }
}
