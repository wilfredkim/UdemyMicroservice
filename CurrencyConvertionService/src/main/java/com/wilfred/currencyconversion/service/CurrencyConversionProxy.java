package com.wilfred.currencyconversion.service;

import com.wilfred.currencyconversion.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyConversionProxy {
    @GetMapping("/api/v1/currencyconverter/convert/{from}/{to}/{spotRate}")
    CurrencyConversion convert(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal spotRate);
}
