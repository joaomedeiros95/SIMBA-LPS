package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.Estado;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public interface Alerta {

    void acionarAlerta(List<Estado> estados) throws EmailException, IOException;

}
