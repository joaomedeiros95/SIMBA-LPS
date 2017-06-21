package br.ufrn.simba.model;

import java.util.Date;

/**
 * Created by joao on 22/05/17.
 */
public class Estado {

    private Date data;
    private String nome;
    private Integer valor;
    // identificador único para o dispositivo
    private long hash;

    public Estado(final Date data, final String nome, final Integer valor, final long hash) {
        this.data = data;
        this.nome = nome;
        this.valor = valor;
        this.hash = hash;
    }

    public Date getData() {
        return data;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public long getHash() {
        return hash;
    }
}

