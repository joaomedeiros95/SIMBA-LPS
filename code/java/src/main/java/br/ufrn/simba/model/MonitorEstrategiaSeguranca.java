package br.ufrn.simba.model;

import br.ufrn.simba.monitoramento.MonitorEvento;
import br.ufrn.simba.seguranca.EstrategiaSeguranca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 29/05/17.
 */
public class MonitorEstrategiaSeguranca {

    private final MonitorEvento monitorEvento;

    private final List<EstrategiaSeguranca> estrategiasSeguranca;

    public MonitorEstrategiaSeguranca(final MonitorEvento monitorEvento,
                                      final List<EstrategiaSeguranca> estrategiasSeguranca) {
        this.monitorEvento = monitorEvento;

        if (estrategiasSeguranca == null) {
            this.estrategiasSeguranca = new ArrayList<>();
        } else {
            this.estrategiasSeguranca = estrategiasSeguranca;
        }
    }

    public void addEstrategiaSeguranca(final EstrategiaSeguranca estrategiaSeguranca) {
        this.estrategiasSeguranca.add(estrategiaSeguranca);
    }

    public void removeEstrategiaSeguranca(final EstrategiaSeguranca estrategiaSeguranca) {
        this.estrategiasSeguranca.remove(estrategiaSeguranca);
    }

    public MonitorEvento getMonitorEvento() {
        return monitorEvento;
    }

    public List<EstrategiaSeguranca> getEstrategiasSeguranca() {
        return estrategiasSeguranca;
    }
}
