package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
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

        ResponseEntity<CurrencyConversion> currencyConversionResponse = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{givenCurrency}/to/{targetCurrency}",
                CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = currencyConversionResponse.getBody();
        return new CurrencyConversion(currencyConversion.getId(), currencyConversion.getGivenCurrency(),
                currencyConversion.getTargetCurrency(), currencyConversion.getConversionMultiple(),
                quantity, quantity.multiply(currencyConversion.getConversionMultiple()));
    }
}
