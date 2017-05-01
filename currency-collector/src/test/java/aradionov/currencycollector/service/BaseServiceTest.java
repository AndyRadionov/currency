package aradionov.currencycollector.service;

import aradionov.currencycollector.dao.JDBI;
import aradionov.currencycollector.util.PropertiesLoader;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Andrey Radionov
 */
public class BaseServiceTest {
    protected static CurrencyService currencyService;

    @BeforeClass
    public static void setUp() throws IOException {
        Properties appProperties = PropertiesLoader.loadProperties("src/../config/app.properties");
        currencyService = new CurrencyService(appProperties.getProperty("appId"));
        JDBI.initDB();
    }
}
