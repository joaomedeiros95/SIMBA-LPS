package br.ufrn.simba.instancia.estrategias;

import br.ufrn.simba.instancia.Instancia;
import br.ufrn.simba.model.Estado;
import br.ufrn.simba.seguranca.EstrategiaSeguranca;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.*;

/**
 * Created by joao on 12/06/17.
 */
@RunWith(Parameterized.class)
public class EstrategiaSegurancaGeneralTest {

    private static final String NOME_ESTADO = "Teste";

    private final EstrategiaSeguranca estrategiaSeguranca;
    private final int[] valoresMatched;
    private final int[] valoresGarbage;
    private final long estadoHash;

    public EstrategiaSegurancaGeneralTest(final EstrategiaSeguranca estrategiaSeguranca,
                                          final int[] valoresMatched, final int[] valoresGarbage,
                                          final long estadoHash) {
        this.estrategiaSeguranca = estrategiaSeguranca;
        this.valoresMatched = valoresMatched;
        this.valoresGarbage = valoresGarbage;
        this.estadoHash = estadoHash;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {Mockito.spy(new EstrategiaSegurancaAbertoCalor()), new int[] {1, 1}, new int[] {0, 2, 3, -1},
                        Instancia.sensorCalorHash},
                {Mockito.spy(new EstrategiaSegurancaAbertoImpacto()), new int[] {1, 2, 3, 4}, new int[] {0, -1, -2, -3},
                        Instancia.sensorImpactoHash},
                {Mockito.spy(new EstrategiaSegurancaAbertoSom()), new int[] {1001, 1002, 1003},
                        new int[] {-1, 0, 999, 1000, 998}, Instancia.sensorSomHash},
                {Mockito.spy(new EstrategiaSegurancaFechadoCalor()), new int[] {1},
                        new int[] {0, 2, 3, -1}, Instancia.sensorCalorHash},
                {Mockito.spy(new EstrategiaSegurancaFechadoImpacto()), new int[] {1, 2, 3, 4},
                        new int[] {0, -1, -2, -3}, Instancia.sensorImpactoHash},
                {Mockito.spy(new EstrategiaSegurancaFechadoMovimento()), new int[] {599, 598, 597, 0},
                        new int[] {600, 601, 602, 603}, Instancia.sensorMovimentoHash},
                {Mockito.spy(new EstrategiaSegurancaFechadoSom()), new int[] {601, 602, 603, 604},
                        new int[] {599, 600, 0, -1}, Instancia.sensorSomHash},
        });
    }

    @Before
    public void setUp() throws Exception {
        this.estrategiaSeguranca.limparAlertas();
    }

    @Test
    public void testExecuteComAlerta() throws Exception {
        final List<Estado> estados = new ArrayList<>();
        for (final int valorAberto : valoresMatched) {
            estados.add(new Estado(new Date(), NOME_ESTADO, valorAberto, this.estadoHash));
        }

        this.estrategiaSeguranca.execute(estados);

        Mockito.verify(this.estrategiaSeguranca, Mockito.times(valoresMatched.length)).notificar(
                Mockito.<Estado>anyList());
    }

    @Test
    public void testExecuteSemAlerta() throws Exception {
        final List<Estado> estados = new ArrayList<>();
        for (final int valorAberto : valoresGarbage) {
            estados.add(new Estado(new Date(), NOME_ESTADO, valorAberto, this.estadoHash));
        }

        this.estrategiaSeguranca.execute(estados);

        Mockito.verify(this.estrategiaSeguranca, Mockito.never()).notificar(Mockito.<Estado>anyList());
    }


}
