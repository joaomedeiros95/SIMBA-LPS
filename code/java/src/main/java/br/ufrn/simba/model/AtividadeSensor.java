package br.ufrn.simba.model;

import br.ufrn.simba.comunicacao.HTTPRequester;

/**
 * Created by joao on 04/04/17.
 */
public class AtividadeSensor {

    private TipoDispositivo tipoDispositivo;

    private HTTPRequester.RespostaHTTP valor;

    public AtividadeSensor(TipoDispositivo tipoDispositivo, HTTPRequester.RespostaHTTP valor) {
        this.tipoDispositivo = tipoDispositivo;
        this.valor = valor;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public HTTPRequester.RespostaHTTP getValor() {
        return valor;
    }

    public void setValor(HTTPRequester.RespostaHTTP valor) {
        this.valor = valor;
    }
}
