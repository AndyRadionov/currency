package aradionov.currencyviewer.repository;

import aradionov.currencyviewer.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Andrey Radionov
 */
@Repository
public class CurrencyRepository {
    private static final RowMapper<Currency> CURRENCY_MAPPER = new CurrencyMapper();
    private static final String GET_ALL = "select base, code, rate, MAX(update_time) AS update_time\n" +
            "FROM currencies group by base, code, rate ORDER BY code";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Currency> getAllLastUpdated() {
        return jdbcTemplate.query(GET_ALL, CURRENCY_MAPPER);
    }


    private static class CurrencyMapper implements RowMapper<Currency> {
        @Override
        public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Currency(
                    resultSet.getString("base"),
                    resultSet.getString("code"),
                    resultSet.getTimestamp("update_time"),
                    resultSet.getDouble("rate")
            );
        }
    }
}
