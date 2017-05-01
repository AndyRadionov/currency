package aradionov.currencycollector.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Andrey Radionov
 */
public class PropertiesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    private PropertiesLoader() {
    }

    public static Properties loadProperties(String filePath) throws IOException {
        LOGGER.debug("Start Loading Properties From " + filePath);
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            prop.load(input);
            return prop;
        } catch (IOException e) {
            LOGGER.error("Cannot Load Properties From " + filePath);
            throw e;
        }
    }
}
