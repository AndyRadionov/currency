package aradionov.currencycollector.dao;

import aradionov.currencycollector.model.Currencies;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.PreparedBatch;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * DAO class provides access for database
 * @author Andrey Radionov
 */
public class CurrencyDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyDAO.class);
    private static final CurrencyDAO INSTANCE = new CurrencyDAO();
    private static final String BATCH_INSERT_QUERY =
            "INSERT INTO currencies (base, code, rate, update_time) VALUES (:base, :code, :rate, :update_time)";
    private static final String DELETE_EXPIRED_QUERY =
            "DELETE FROM currencies WHERE update_time < :expire_time";

    private CurrencyDAO() {
    }

    public static CurrencyDAO getInstance() {
        return INSTANCE;
    }

    /**
     * Inserts current currencies into database
     * @param currencies current currencies
     */
    @Transaction
    public void insertCurrency(Currencies currencies) {
        LOGGER.debug("DAO INSERT Currencies");
        try(Handle handle = JDBI.getHandle()) {
            PreparedBatch batchInsert = handle.prepareBatch(BATCH_INSERT_QUERY);
            for (Map.Entry<String, Double> rates : currencies.getRates().entrySet()) {
                batchInsert.add()
                        .bind("base", currencies.getBase())
                        .bind("code", rates.getKey())
                        .bind("rate", rates.getValue())
                        .bind("update_time", currencies.getTimestamp());
            }
            batchInsert.execute();
        }
    }

    /**
     * Removes expired currencies from database
     * @param expireTime after this dateTime currency becomes expired
     */
    @Transaction
    public void deleteExpired(Date expireTime) {
        LOGGER.debug("DAO Delete Expired Currencies");
        try(Handle handle = JDBI.getHandle()) {
            handle.createStatement(DELETE_EXPIRED_QUERY)
                    .bind("expire_time", expireTime)
                    .execute();
        }
    }
}
