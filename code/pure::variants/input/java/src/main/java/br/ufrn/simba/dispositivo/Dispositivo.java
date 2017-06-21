package br.ufrn.simba.dispositivo;

import br.ufrn.simba.model.Estado;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public interface Dispositivo {

    Estado recuperarEstado() throws IOException;

}
