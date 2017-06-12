package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.seguranca.EstrategiaSeguranca;
import br.ufrn.simba.utils.Propriedades;

import java.util.Calendar;

/**
 * Created by joao on 10/06/17.
 */
public abstract class EstrategiaSegurancaAberto extends EstrategiaSeguranca {

    private static final String HORARIO_ABERTO = Propriedades.pegarPropriedade("abre");
    private static final String HORARIO_FECHADO = Propriedades.pegarPropriedade("fecha");
    private static final String[] ABRE = HORARIO_ABERTO.split(":");
    private final String[] FECHA = HORARIO_FECHADO.split(":");

    @Override
    protected boolean satisfazCondicoes() {
        return isAberto();
    }

    boolean isAberto() {
        final Calendar dataAtual = Calendar.getInstance();

        return isAberto(dataAtual);
    }

    boolean isAberto(final Calendar dataAtual) {
        return isMaior(dataAtual, ABRE) && isMenor(dataAtual, FECHA);
    }

    private boolean isMaior(final Calendar dataAtual, final String[] horarios) {
        if (dataAtual.get(Calendar.HOUR_OF_DAY) > Integer.parseInt(horarios[0])) {
            return true;
        } else if (dataAtual.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(horarios[0])
                && dataAtual.get(Calendar.MINUTE) > Integer.parseInt(horarios[1])) {
            return true;
        } else if (dataAtual.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(horarios[0])
                && dataAtual.get(Calendar.MINUTE) == Integer.parseInt(horarios[1])
                && dataAtual.get(Calendar.SECOND) >= Integer.parseInt(horarios[1])) {
            return true;
        }

        return false;
    }

    private boolean isMenor(final Calendar dataAtual, final String[] horarios) {
        if (dataAtual.get(Calendar.HOUR_OF_DAY) < Integer.parseInt(horarios[0])) {
            return true;
        } else if (dataAtual.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(horarios[0])
                && dataAtual.get(Calendar.MINUTE) < Integer.parseInt(horarios[1])) {
            return true;
        } else if (dataAtual.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(horarios[0])
                && dataAtual.get(Calendar.MINUTE) == Integer.parseInt(horarios[1])
                && dataAtual.get(Calendar.SECOND) <= Integer.parseInt(horarios[1])) {
            return true;
        }

        return false;
    }
}

