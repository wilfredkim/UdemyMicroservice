package com.wilfred.currencyexchange.controller;

import com.wilfred.currencyexchange.model.CurrencyExchange;
import com.wilfred.currencyexchange.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/currencyconverter")
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeController {


    private final CurrencyExchangeRepository repository;
    @Autowired
    Environment environment;

    @GetMapping("/convert/{from}/{to}/{spotRate}")
    public CurrencyExchange convert(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal spotRate) {
        log.info("{from}::" + from + "  {to}:::::: " + to + "  {spotRate}:::::" + spotRate);
        CurrencyExchange currencyExchange = repository.findCurrencyExchangeByFromAndTo(from, to);
        if (currencyExchange == null)
            throw new RuntimeException("Cannot find currency with from currency " + from + " to " + to);
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnv(port);
        return currencyExchange;
    }

    @PostMapping
    public CurrencyExchange saveOrUpdate(@RequestBody CurrencyExchange currencyExchange) {
        return repository.save(currencyExchange);
    }

    @GetMapping
    public List<CurrencyExchange> getList() {
        return repository.findAll();

    }
}
