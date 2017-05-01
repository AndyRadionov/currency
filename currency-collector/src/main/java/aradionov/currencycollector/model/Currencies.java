package aradionov.currencycollector.model;

import java.util.Date;
import java.util.Map;

/**
 * Model class describes current currencies
 * @author Andrey Radionov
 */
public class Currencies {
    private String base;
    private Date timestamp;
    private Map<String, Double> rates;

    /**
     * @param base - USD
     * @param timestamp - update time
     * @param rates - list of all currency/USD exchange rates
     */
    public Currencies(String base, Date timestamp, Map<String, Double> rates) {
        this.base = base;
        this.timestamp = timestamp;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
