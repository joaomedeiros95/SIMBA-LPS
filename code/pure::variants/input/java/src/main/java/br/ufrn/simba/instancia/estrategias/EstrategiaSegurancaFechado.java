package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.seguranca.EstrategiaSeguranca;

/**
 * Created by joao on 10/06/17.
 */
public abstract class EstrategiaSegurancaFechado extends EstrategiaSegurancaAberto {

    @Override
    protected boolean satisfazCondicoes() {
        return !isAberto();
    }

}
