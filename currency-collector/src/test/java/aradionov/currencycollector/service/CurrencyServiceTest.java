package aradionov.currencycollector.service;

import aradionov.currencycollector.model.Currencies;
import aradionov.currencycollector.service.utils.DAOHelper;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Andrey Radionov
 */
public class CurrencyServiceTest extends BaseServiceTest {
    @Test
    public void testLoadCurrencies() throws Exception {
        Currencies currencies = currencyService.loadCurrencies();

        assertTrue(currencies != null);
        assertTrue(currencies.getBase() != null);
        assertFalse(currencies.getBase().isEmpty());
        assertTrue(currencies.getTimestamp() != null);
        assertTrue(currencies.getRates() != null);
        assertFalse(currencies.getRates().isEmpty());
        assertTrue(currencies.getRates().size() > 0);
    }

    @Test
    public void testStoringCurrenciesInDB() {
        List<Map<String, Object>> before = DAOHelper.getAllCurrenciesFromDB();
        Currencies currencies = currencyService.loadCurrencies();
        currencyService.storeCurrencies(currencies);

        List<Map<String, Object>> after = DAOHelper.getAllCurrenciesFromDB();
        assertTrue(after.size() > before.size());
    }
}