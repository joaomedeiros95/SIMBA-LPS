package br.ufrn.simba.utils;

/**
 * Created by joao on 29/05/17.
 */
public class Util {

    public static long hashDispositivo(final String nome, final Integer id) {
        final String nomeComId = String.format("%s_%s", nome, id);

        return nomeComId.hashCode();
    }

}
