package com.wilfred.currencyexchange.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {
    @GetMapping("/test-circuit-breaker")
    //@Retry(name = "test-circuit-braker", fallbackMethod = "defaultMethod")
    @CircuitBreaker(name = "test-circuit-braker", fallbackMethod = "defaultMethod")
    @RateLimiter(name = "defaultLimiter")// time period and allow specific number of calls
    public String testCircuitBreaker() {
        log.info("Counting::::::::::test");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:9090/dummy-api", String.class);
        return forEntity.getBody();

    }

    public String defaultMethod(Exception exception) {
        exception.printStackTrace();
        return "This is the default method!";
    }
}


