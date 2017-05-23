package br.ufrn.simba.monitoramento;

import br.ufrn.simba.dispositivo.Dispositivo;
import br.ufrn.simba.model.Estado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public class MonitorEvento {

    private final List<Dispositivo> dispositivosAtivos = new ArrayList<>();

    public List<Estado> analisarDispositivos() throws IOException {
        final List<Estado> resultado = new ArrayList<>();

        for (final Dispositivo dispositivo : this.dispositivosAtivos) {
            resultado.add(dispositivo.recuperarEstado());
        }

        return resultado;
    }

    public void addDispositivo(final Dispositivo dispositivo) {
        this.dispositivosAtivos.add(dispositivo);
    }

    public void removeDispositivo(final Dispositivo dispositivo) {
        this.dispositivosAtivos.remove(dispositivo);
    }

    public void limparDispositivos() {
        this.dispositivosAtivos.clear();
    }

}
