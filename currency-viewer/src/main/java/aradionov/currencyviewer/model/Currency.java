package aradionov.currencyviewer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Map;

/**
 * @author Andrey Radionov
 */
public class Currency {
    private String base;
    private String code;
    private Date timestamp;
    private Double rate;

    public Currency(String base, String code, Date timestamp, double rate) {
        this.base = base;
        this.code = code;
        this.timestamp = timestamp;
        this.rate = rate;
    }

    public String getBase() {
        return base;
    }

    public String getCode() {
        return code;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "EN", pattern="yyyy-MMM-dd HH:mm")
    public Date getTimestamp() {
        return timestamp;
    }

    public Double getRate() {
        return rate;
    }
}
