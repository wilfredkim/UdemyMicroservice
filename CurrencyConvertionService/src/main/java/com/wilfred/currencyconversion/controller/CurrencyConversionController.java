package com.wilfred.currencyconversion.controller;

import com.wilfred.currencyconversion.model.CurrencyConversion;
import com.wilfred.currencyconversion.service.CurrencyConversionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/api/currencyconversion")
public class CurrencyConversionController {
    @Autowired
    CurrencyConversionProxy currencyConversionProxy;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                               @PathVariable String to,
                                                               @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyConversionProxy.convert(from, to, quantity);
        return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getSpotRate(), quantity, currencyConversion.getTotalCalculatedAmount(), currencyConversion.getEnvironment());
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        uriVariables.put("spotRate",BigDecimal.ONE.toString());

        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity
                ("http://localhost:1072/api/v1/currencyconverter/convert/{from}/{to}/{spotRate}",
                        CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(),
                from, to, quantity,
                currencyConversion.getSpotRate(),
                quantity.multiply(currencyConversion.getSpotRate()),
                currencyConversion.getEnvironment()+ " " + "rest template");

    }
}
