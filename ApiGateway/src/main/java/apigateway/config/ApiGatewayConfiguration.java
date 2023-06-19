package apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        Function<PredicateSpec, Buildable<Route>> function =
                p -> p.path("/get").filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpin.org:80");
        return routeLocatorBuilder.routes().route(
                        p -> p.path("/get").filters(f -> f
                                        .addRequestHeader("MyHeader", "MyURI")
                                        .addRequestParameter("Param", "MyValue"))
                                .uri("http://httpin.org:80")).
                route(p -> p.path("/api/v1/currencyconverter/**")
                        .uri("lb:///api/v1/currencyconverter"))
                .route(p -> p.path("/api/v1/currencyconversion/**")
                        .uri("lb:///api/v1/currencyconversion"))
                .route(p -> p.path("/api/v1/limits/**")
                        .uri("lb:///api/v1/limits")).build();
    }
}
