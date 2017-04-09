package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.TipoDispositivo;
import org.apache.commons.mail.EmailException;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public interface Alerta {

    void acionarAlerta(TipoDispositivo tipoDispositivo) throws EmailException, IOException;

}
