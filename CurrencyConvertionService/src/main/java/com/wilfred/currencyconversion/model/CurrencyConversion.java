package com.wilfred.currencyconversion.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyConversion {
    private Long id;
    private String to;
    private String from;
    private BigDecimal spotRate;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;

    public CurrencyConversion(Long id, String to, String from, BigDecimal spotRate, BigDecimal quantity, BigDecimal totalCalculatedAmount, String environment) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.spotRate = spotRate;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
        this.environment = environment;
    }
}
