package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.instancia.Instancia;
import br.ufrn.simba.model.Estado;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by joao on 12/06/17.
 */
public class EstrategiaSegurancaAbertoCalorTest {

    final EstrategiaSegurancaAbertoCalor estrategia =
            Mockito.mock(EstrategiaSegurancaAbertoCalor.class);

    @Test
    public void testExecute() throws Exception {
        final List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(new Date(), "Teste", 1, Instancia.sensorCalorHash));

        this.estrategia.execute(estados);

        Mockito.verify(this.estrategia, Mockito.times(1)).notificar(estados);
    }
}
