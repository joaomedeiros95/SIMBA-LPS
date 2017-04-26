package br.ufrn.simba.model;

/**
 * Created by joao on 04/04/17.
 */
public class AtividadeSensor {

    private boolean ofereceRisco;

    public AtividadeSensor(boolean ofereceRisco) {
        this.ofereceRisco = ofereceRisco;
    }

    public boolean isOfereceRisco() {
        return ofereceRisco;
    }

    public void setOfereceRisco(boolean ofereceRisco) {
        this.ofereceRisco = ofereceRisco;
    }
}
