package br.ufrn.simba.model;

/**
 * Created by joao on 04/04/17.
 */
public class AtividadeSensor {

    private int tipoDispositivo;

    private Object valor;

    public int getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(int tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
