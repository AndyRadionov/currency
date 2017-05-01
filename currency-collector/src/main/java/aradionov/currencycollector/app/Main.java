package aradionov.currencycollector.app;

import aradionov.currencycollector.dao.JDBI;
import aradionov.currencycollector.service.CleanerService;
import aradionov.currencycollector.service.CurrencyService;
import aradionov.currencycollector.util.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * CurrencyClient main class. Bootstrap and shut down application
 * @author Andrey Radionov
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Scheduler cleanerScheduler;
    private static Scheduler currencyScheduler;

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        LOGGER.info("Currency Client starting");
        try {
            JDBI.initDB();
            Properties appProperties = PropertiesLoader.loadProperties("./config/app.properties");
            startServices(appProperties);
        } catch (IOException e) {
            LOGGER.error("Cannot Start App");
        }
    }

    public static void stopApp(){
        LOGGER.debug("App shutting down");
        if (cleanerScheduler != null && currencyScheduler != null) {
            cleanerScheduler.shutDownService();
            currencyScheduler.shutDownService();
        }
    }

    private static void startServices(Properties props) {
        CleanerService cleanerService = new CleanerService(props.getProperty("expireDays"));
        CurrencyService currencyService = new CurrencyService(props.getProperty("appId"));

        cleanerScheduler = new Scheduler(cleanerService, 1L, TimeUnit.DAYS);
        currencyScheduler = new Scheduler(currencyService, 1L, TimeUnit.HOURS);
    }
}
