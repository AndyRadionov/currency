package aradionov.currencycollector.service;

import aradionov.currencycollector.dao.CurrencyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Service for cleaning expired currencies.
 * Expire days is stored in properties file.
 *
 * This Service implements Runnable for using with Scheduler
 * @author Andrey Radionov
 */
public class CleanerService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CleanerService.class);
    private CurrencyDAO currencyDAO = CurrencyDAO.getInstance();
    private int expireDays;

    /**
     * @param expireDays After this number of days currency becomes expired
     */
    public CleanerService(String expireDays) {
        this.expireDays = Integer.parseInt(expireDays);
    }

    public void cleanExpiredCurrencies() {
        LOGGER.debug("CleanerService Cleaning Expired Currencies");
        currencyDAO.deleteExpired(getExpireDate());
    }
    @Override
    public void run() {
        cleanExpiredCurrencies();
    }

    private Date getExpireDate() {
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        localDateTime = localDateTime.minusDays(expireDays);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
