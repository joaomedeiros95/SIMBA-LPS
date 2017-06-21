package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.Estado;
import br.ufrn.simba.utils.Propriedades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public abstract class Notificacao implements Alerta {

    protected static final String NOME_BANCO = Propriedades.pegarPropriedade("nome_banco");
    protected List<Estado> estadosSensores;

    private final SimpleDateFormat fomatoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    protected String pegarHoraAtual() {
        final Date now = new Date();

        return fomatoData.format(now);
    }

}
