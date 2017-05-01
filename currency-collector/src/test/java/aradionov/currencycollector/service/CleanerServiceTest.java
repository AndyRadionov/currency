package aradionov.currencycollector.service;

import aradionov.currencycollector.model.Currencies;
import aradionov.currencycollector.service.utils.DAOHelper;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Andrey Radionov
 */
public class CleanerServiceTest extends BaseServiceTest {
    @Test
    public void testCleanExpiredCurrencies() throws Exception {
        Currencies currencies = currencyService.loadCurrencies();
        Currencies oldCurrencies = new Currencies(currencies.getBase(), createOldDate(), currencies.getRates());
        currencyService.storeCurrencies(oldCurrencies);
        List<Map<String, Object>> before = DAOHelper.getAllCurrenciesFromDB();

        new CleanerService("5").cleanExpiredCurrencies();
        List<Map<String, Object>> after = DAOHelper.getAllCurrenciesFromDB();

        assertTrue(after.size() < before.size());
    }

    private Date createOldDate() {
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        localDateTime = localDateTime.minusDays(10);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
