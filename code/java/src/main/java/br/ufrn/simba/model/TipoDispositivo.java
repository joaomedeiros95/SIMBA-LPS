package br.ufrn.simba.model;

/**
 * Created by joao on 08/04/17.
 */
public enum TipoDispositivo {
    SENSOR_MOVIMENTO("Movimento"), CAMERA("Câmera");

    private String nome;

    TipoDispositivo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
