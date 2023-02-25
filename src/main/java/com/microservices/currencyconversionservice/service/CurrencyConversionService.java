package com.microservices.currencyconversionservice.service;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CurrencyConversionService {
    void prepareCurrencyConversionResponse(CurrencyConversion currencyConversion,
                                           BigDecimal quantity);
}
