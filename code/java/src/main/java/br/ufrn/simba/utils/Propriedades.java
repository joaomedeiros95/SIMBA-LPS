package br.ufrn.simba.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by joao on 08/04/17.
 */
public class Propriedades {

    private static final String ARQUIVO_PROPRIEDADES = "simba.properties";
    private static final Properties prop = new Properties();

    static {
        final InputStream input =
                Propriedades.class.getClassLoader().getResourceAsStream(ARQUIVO_PROPRIEDADES);

        try {
            prop.load(input);
        } catch (IOException e) {
            // TODO: refatorar isso em Log4J
            System.out.println("Um erro ocorreu ao carregar as propriedades do sistema!");
        }
    }

    public static String pegarPropriedade(final String propriedade) {
        return prop.getProperty(propriedade, "-1");
    }

}
