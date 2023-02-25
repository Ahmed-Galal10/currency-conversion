package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @GetMapping("/from/{givenCurrency}/to/{targetCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String givenCurrency,
                                                          @PathVariable String targetCurrency,
                                                              @PathVariable BigDecimal quantity){
        return new CurrencyConversion(1L, givenCurrency, targetCurrency, BigDecimal.ONE, quantity, BigDecimal.ONE);
    }
}
