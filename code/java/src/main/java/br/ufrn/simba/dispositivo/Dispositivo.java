package br.ufrn.simba.dispositivo;

import br.ufrn.simba.comunicacao.HTTPRequester;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public interface Dispositivo {

    HTTPRequester.RespostaHTTP receberDados() throws IOException;

}
