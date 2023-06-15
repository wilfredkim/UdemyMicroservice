package com.wilfred.currencyexchange.controller;

import com.wilfred.currencyexchange.model.CurrencyExchange;
import com.wilfred.currencyexchange.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/currencyconverter")
@RequiredArgsConstructor
public class CurrencyExchangeController {


    private final CurrencyExchangeRepository repository;
    @Autowired
    Environment environment;

    @GetMapping("/convert/{from}/{to}/{spotRate}")
    public CurrencyExchange convert(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal spotRate) {
        //CurrencyExchange currencyExchange = new CurrencyExchange(1L, from, to, spotRate, "");
        CurrencyExchange currencyExchange = repository.findCurrencyExchangeByFromAndTo(from, to);
        if (currencyExchange == null)
            throw new RuntimeException("Cannot find currency with from currency " + from + " to " + to);
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnv(port);
        return currencyExchange;
    }
}
