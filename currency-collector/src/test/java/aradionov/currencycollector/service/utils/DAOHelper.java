package aradionov.currencycollector.service.utils;

import aradionov.currencycollector.dao.JDBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.Map;

/**
 * @author Andrey Radionov
 */
public class DAOHelper {
    private static final String SELECT_ALL_CURRENCIES_QUERY = "SELECT * FROM currencies";
    private DAOHelper() {
    }

    public static List<Map<String, Object>> getAllCurrenciesFromDB() {
        try(Handle handle = JDBI.getHandle()) {
            return handle.select(SELECT_ALL_CURRENCIES_QUERY);
        }
    }
}
