package br.ufrn.simba.seguranca;

import br.ufrn.simba.model.Estado;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 04/04/17.
 */
public abstract class EstrategiaSeguranca {

    private final List<Alerta> alertasHabilitados = new ArrayList<>();

    public abstract void execute(final List<Estado> estados) throws IOException, EmailException;

    public void notificar(final List<Estado> estados) throws IOException, EmailException {
        if (this.satisfazCondicoes()) {
            for (final Alerta alerta : alertasHabilitados) {
                alerta.acionarAlerta(estados);
            }
        }
    }

    protected abstract boolean satisfazCondicoes();

    public final void addAlerta(final Alerta alerta) {
        this.alertasHabilitados.add(alerta);
    }

    public final void removeAlerta(final Alerta alerta) {
        this.alertasHabilitados.remove(alerta);
    }

    public final void limparAlertas() {
        this.alertasHabilitados.clear();
    }

}
