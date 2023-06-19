package com.wilfred.currencyconversion.controller;

import com.wilfred.currencyconversion.model.CurrencyConversion;
import com.wilfred.currencyconversion.service.CurrencyConversionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/api/currencyconversion")
public class CurrencyConversionController {
    @Autowired
    CurrencyConversionProxy currencyConversionProxy;

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                               @PathVariable String to,
                                                               @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyConversionProxy.convert(from, to, quantity);
        return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getSpotRate(), quantity, currencyConversion.getTotalCalculatedAmount(), currencyConversion.getEnvironment());
    }
}
