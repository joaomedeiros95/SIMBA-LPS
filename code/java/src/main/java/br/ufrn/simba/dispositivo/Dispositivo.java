package br.ufrn.simba.dispositivo;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public interface Dispositivo {

    boolean receberDados() throws IOException;

}
