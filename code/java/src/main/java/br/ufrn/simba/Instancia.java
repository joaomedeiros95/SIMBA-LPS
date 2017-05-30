package br.ufrn.simba;

import br.ufrn.simba.dispositivo.sensor.Impacto;
import br.ufrn.simba.model.Estado;
import br.ufrn.simba.model.MonitorEstrategiaSeguranca;
import br.ufrn.simba.monitoramento.MonitorEvento;
import br.ufrn.simba.seguranca.EstrategiaSeguranca;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 25/04/17.
 */
public class Instancia {

    private static long sensorImpactoHash;

    public static void main(String[] args) {
        final MonitorEvento monitorEvento = new MonitorEvento();
        final Impacto impacto = new Impacto(13, false, "Sensor de Impacto", 1);
        sensorImpactoHash = impacto.getHash();

        monitorEvento.addDispositivo(impacto);

        final MonitorEstrategiaSeguranca monitor =
                new MonitorEstrategiaSeguranca(monitorEvento, null);
        monitor.addEstrategiaSeguranca(new EstrategiaSegurancaSimples());

        final List<MonitorEstrategiaSeguranca> monitores = new ArrayList<>();
        monitores.add(monitor);

        final Controlador controlador = new Controlador(monitores);
        controlador.iniciarMonitoramento();
    }

    static class EstrategiaSegurancaSimples extends EstrategiaSeguranca {

        @Override
        public void execute(final List<Estado> estados) throws IOException, EmailException {
            for (final Estado estado : estados) {
                if (estado.getHash() == sensorImpactoHash) {
                    if (estado.getValor() > 50) {
                        notificar(estados);
                    }
                }
            }
        }

        @Override
        protected boolean satisfazCondicoes() {
            return true;
        }
    }



}
