package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import com.microservices.currencyconversionservice.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    private static final String CURRENCY_EXCHANGE_SERVICE_SRL = "http://localhost:8000/currency-exchange/from/{givenCurrency}/to/{targetCurrency}";


    @Autowired
    private CurrencyConversionService currencyConversionService;


    @GetMapping("/hard-coded/from/{givenCurrency}/to/{targetCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String givenCurrency,
                                                          @PathVariable String targetCurrency,
                                                          @PathVariable BigDecimal quantity) {
        return new CurrencyConversion(1L, givenCurrency, targetCurrency, BigDecimal.ONE, quantity, BigDecimal.ONE);
    }

    @GetMapping("/by-rest-template/from/{givenCurrency}/to/{targetCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionByRestTemplate(@PathVariable String givenCurrency,
                                                                        @PathVariable String targetCurrency,
                                                                        @PathVariable BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("givenCurrency", givenCurrency);
        uriVariables.put("targetCurrency", targetCurrency);

        ResponseEntity<CurrencyConversion> currencyConversionResponse = new RestTemplate().getForEntity(CURRENCY_EXCHANGE_SERVICE_SRL,
                CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = currencyConversionResponse.getBody();
        this.currencyConversionService.prepareCurrencyConversionResponse(currencyConversion, quantity);

        return currencyConversion;
    }


}
