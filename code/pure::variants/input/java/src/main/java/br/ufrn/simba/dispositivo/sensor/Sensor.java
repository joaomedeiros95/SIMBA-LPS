package br.ufrn.simba.dispositivo.sensor;

import br.ufrn.simba.comunicacao.HTTPRequester;
import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.model.Estado;
import br.ufrn.simba.utils.Propriedades;
import br.ufrn.simba.utils.Util;

import java.io.IOException;
import java.util.Date;

/**
 * Created by joao on 04/04/17.
 */
public abstract class Sensor implements Dispositivo {

    private int porta;
    private boolean analogico;
    private String nome;
    private Integer id;
    private long hash;

    public Sensor(int porta, boolean analogico, String nome, Integer id) {
        this.porta = porta;
        this.analogico = analogico;
        this.nome = nome;
        this.id = id;
        this.hash = Util.hashDispositivo(nome, id);
    }

    public Estado recuperarEstado() throws IOException {
        final HTTPRequester.RespostaHTTP resposta =
                HTTPRequester.getInstance().get(this.analogico, this.porta);
        final Integer valor = Integer.parseInt(resposta.getResposta());
        return new Estado(new Date(), this.nome, valor, this.hash);
    }

    public long getHash() {
        return hash;
    }
}
