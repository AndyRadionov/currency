package aradionov.currencycollector.dao;

import aradionov.currencycollector.util.PropertiesLoader;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * JDBI helper class
 * @author Andrey Radionov
 */
public class JDBI {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBI.class);
    private static DBI dbi;

    /**
     * @return Handle - wrapper around JDBC connection
     */
    public static Handle getHandle() {
        return dbi.open();
    }

    /**
     * JDBI Initialization
     * @throws IOException if cannot load properties
     */
    public static void initDB() throws IOException {
        LOGGER.debug("Database Initialization");
        Properties dbProperties = PropertiesLoader.loadProperties("./config/database.properties");

        dbi = new DBI(
                dbProperties.getProperty("jdbc.url"),
                dbProperties.getProperty("jdbc.username"),
                dbProperties.getProperty("jdbc.password")
        );
    }
}
