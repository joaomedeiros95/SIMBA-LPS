package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.model.Estado;
import org.apache.commons.mail.EmailException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by joao on 12/06/17.
 */
@RunWith(Parameterized.class)
public class EstrategiaSegurancaAbertoTest {

    final Calendar data;

    final boolean isAberto;

    public EstrategiaSegurancaAbertoTest(final Calendar data, final boolean isAberto) {
        this.data = data;
        this.isAberto = isAberto;
    }

    final EstrategiaSegurancaAberto estrategiaSegurancaAberto = new EstrategiaSegurancaAberto() {
        @Override
        public void execute(List<Estado> estados) throws IOException, EmailException {
            return;
        }
    };

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {EstrategiaSegurancaAbertoTest.getCalendar(8, 0, 0), true},
                {EstrategiaSegurancaAbertoTest.getCalendar(8, 0, 1), true},
                {EstrategiaSegurancaAbertoTest.getCalendar(8, 1, 0), true},
                {EstrategiaSegurancaAbertoTest.getCalendar(7, 59, 59), false},
                {EstrategiaSegurancaAbertoTest.getCalendar(7, 59, 0), false},
                {EstrategiaSegurancaAbertoTest.getCalendar(21, 59, 59), true},
                {EstrategiaSegurancaAbertoTest.getCalendar(21, 59, 0), true},
                {EstrategiaSegurancaAbertoTest.getCalendar(22, 0, 0), true},
                {EstrategiaSegurancaAbertoTest.getCalendar(22, 0, 1), false},
                {EstrategiaSegurancaAbertoTest.getCalendar(22, 1, 0), false},
        });
    }

    private static Calendar getCalendar(final int hour, final int minute, final int second) {
        final Calendar retorno = Calendar.getInstance();
        retorno.set(Calendar.HOUR_OF_DAY, hour);
        retorno.set(Calendar.MINUTE, minute);
        retorno.set(Calendar.SECOND, second);

        return retorno;
    }

    @Test
    public void testAberto() throws Exception {
        if (isAberto) {
            Assert.assertTrue("Banco deveria estar aberto", estrategiaSegurancaAberto.isAberto(this.data));
        } else {
            Assert.assertFalse("Banco deveria estar fechado", estrategiaSegurancaAberto.isAberto(this.data));
        }
    }
}
