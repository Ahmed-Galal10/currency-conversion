package com.microservices.currencyconversionservice.service.impl;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import com.microservices.currencyconversionservice.service.CurrencyConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Override
    public void prepareCurrencyConversionResponse(CurrencyConversion currencyConversion, BigDecimal quantity) {
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(this.calculateTotalAmount(quantity, currencyConversion.getConversionMultiple()));
    }

    private BigDecimal calculateTotalAmount(BigDecimal quantity, BigDecimal conversionMultiply) {
        return quantity.multiply(conversionMultiply);
    }
}
