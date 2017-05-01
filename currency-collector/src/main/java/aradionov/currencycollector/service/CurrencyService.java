package aradionov.currencycollector.service;

import aradionov.currencycollector.dao.CurrencyDAO;
import aradionov.currencycollector.model.Currencies;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Service for loading current currencies from https://openexchangerates.org/api/lates.json
 * And storing results in database.
 *
 * This Service implements Runnable for using with Scheduler
 * @author Andrey Radionov
 */
public class CurrencyService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    private String appId;
    private CurrencyDAO currencyDAO = CurrencyDAO.getInstance();
    private Client client = ClientBuilder.newBuilder().build();

    /**
     * @param appId appId for openexchangerates.org
     */
    public CurrencyService(String appId) {
        this.appId = appId;
    }

    /**
     * Loading current currencies from openexchangerates.org
     * @return Currencies model class
     */
    public Currencies loadCurrencies() {
        LOGGER.debug("CurrencyService - Retrieving Actual Currencies");
        String jsonResponse = client.target("https://openexchangerates.org/api")
                .path("latest.json")
                .queryParam("app_id", appId)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        return GSON.fromJson(jsonResponse, Currencies.class);
    }

    /**
     * Storing currencies in Database
     * @param currencies Actual Currencies
     */
    public void storeCurrencies(Currencies currencies) {
        LOGGER.debug("CurrencyService - Storing Currencies in Database");
        currencyDAO.insertCurrency(currencies);
    }

    @Override
    public void run() {
        LOGGER.debug("CurrencyService Start");
        storeCurrencies(loadCurrencies());
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            long timeStamp = json.getAsJsonPrimitive().getAsInt();
            return new Date(timeStamp * 1000);
        }
    }
}
