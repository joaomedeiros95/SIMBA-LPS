package br.ufrn.simba.model;

import br.ufrn.simba.comunicacao.HTTPRequester;

/**
 * Created by joao on 04/04/17.
 */
public class AtividadeSensor {

    public static final int SENSOR_MOVIMENTO = 1;

    private int tipoDispositivo;

    private HTTPRequester.RespostaHTTP valor;

    public AtividadeSensor(int tipoDispositivo, HTTPRequester.RespostaHTTP valor) {
        this.tipoDispositivo = tipoDispositivo;
        this.valor = valor;
    }

    public int getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(int tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public HTTPRequester.RespostaHTTP getValor() {
        return valor;
    }

    public void setValor(HTTPRequester.RespostaHTTP valor) {
        this.valor = valor;
    }
}
