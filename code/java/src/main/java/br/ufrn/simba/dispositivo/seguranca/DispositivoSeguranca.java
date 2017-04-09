package br.ufrn.simba.dispositivo.seguranca;

import br.ufrn.simba.comunicacao.HTTPRequester;
import br.ufrn.simba.model.TipoDispositivo;
import br.ufrn.simba.seguranca.Alerta;
import br.ufrn.simba.utils.Propriedades;

import java.io.IOException;

/**
 * Created by joao on 04/04/17.
 */
public abstract class DispositivoSeguranca implements Alerta {

    private static final String ANALOGICO = "analog";
    private static final String DIGITAL = "digital";
    private static final String ALARME = "alarme";
    private static final String PORTA = "porta";
    private static final String TIPO = "tipo";
    private static final int ATIVADO = 1;

    public void acionarAlerta(TipoDispositivo tipoDispositivo) throws IOException {
        HTTPRequester.getInstance().get(analogico(), pegarPorta(), ATIVADO);
    }

    private int pegarPorta() {
        return Integer.parseInt(
                Propriedades.pegarPropriedade(ALARME + "_" + pegarNome() + "_" + PORTA ));
    }

    private String pegarTipo() {
        return Propriedades.pegarPropriedade(ALARME + "_" + pegarNome() + "_" + TIPO);
    }

    abstract String pegarNome();

    public boolean analogico() {
        final String s = pegarTipo();

        return s.equals(ANALOGICO);
    }

}
