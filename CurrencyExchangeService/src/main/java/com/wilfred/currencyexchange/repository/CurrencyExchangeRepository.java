package com.wilfred.currencyexchange.repository;

import com.wilfred.currencyexchange.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findCurrencyExchangeByFromAndTo(String from, String to);
}
