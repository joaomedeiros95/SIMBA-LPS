package br.ufrn.simba.dispositivo.seguranca;

import br.ufrn.simba.comunicacao.HTTPRequester;
import br.ufrn.simba.model.Estado;
import br.ufrn.simba.seguranca.Alerta;

import java.io.IOException;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public abstract class DispositivoSeguranca implements Alerta {

    private static final int ATIVADO = 1;

    private final boolean analogico;

    private final int porta;

    public DispositivoSeguranca(final boolean analogico, final int porta) {
        this.analogico = analogico;
        this.porta = porta;
    }

    public void acionarAlerta(final List<Estado> estados) throws IOException {
        HTTPRequester.getInstance().get(analogico, porta, ATIVADO);
    }

}
