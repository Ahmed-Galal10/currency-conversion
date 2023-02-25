package com.microservices.currencyconversionservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyConversion {
    private Long id;
    private String givenCurrency;
    private String targetCurrency;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;

    public CurrencyConversion(Long id, String givenCurrency, String targetCurrency, BigDecimal conversionMultiple, BigDecimal quantity, BigDecimal totalCalculatedAmount) {
        this.id = id;
        this.givenCurrency = givenCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
    }
}
