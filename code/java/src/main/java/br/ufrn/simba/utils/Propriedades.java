package br.ufrn.simba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by joao on 08/04/17.
 */
public class Propriedades {

    private static final String ARQUIVO_PROPRIEDADES = "simba.properties";
    private static final Properties prop = new Properties();

    private static final Logger LOGGER = LogManager.getLogger(Propriedades.class);

    static {
        final InputStream input =
                Propriedades.class.getClassLoader().getResourceAsStream(ARQUIVO_PROPRIEDADES);

        try {
            prop.load(input);
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Um erro ocorreu ao carregar as propriedades do sistema!");
            }
        }
    }

    public static String pegarPropriedade(final String propriedade) {
        return prop.getProperty(propriedade, "-1");
    }

}
