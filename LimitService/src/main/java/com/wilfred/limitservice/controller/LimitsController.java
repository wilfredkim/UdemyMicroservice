package com.wilfred.limitservice.controller;

import com.wilfred.limitservice.config.Configuration;
import com.wilfred.limitservice.model.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/limits")
public class LimitsController {
    @Autowired
    public Configuration configuration;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Limit getLimits() {
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }
}
