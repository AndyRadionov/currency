package aradionov.currencyviewer.controller;

import aradionov.currencyviewer.model.Currency;
import aradionov.currencyviewer.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Andrey Radionov
 */
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping
    @ResponseBody
    public List<Currency> getAllLastUpdated() {
        return currencyRepository.getAllLastUpdated();
    }
}
