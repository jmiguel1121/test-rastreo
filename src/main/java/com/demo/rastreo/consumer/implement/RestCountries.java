package com.demo.rastreo.consumer.implement;

import com.demo.rastreo.consumer.gateway.RestCountriesGateway;
import com.demo.rastreo.model.restcountries.DataCountry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class RestCountries implements RestCountriesGateway {
    private final WebClient webClient;

    public RestCountries(@Value("${rest-countries.url.base}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }


    @Override
    @Cacheable(cacheNames = "dataCountry", key = "#code", cacheManager = "cacheManager")
    public Flux<DataCountry> getByCountryName(String code) {
        String uri = "/alpha/" + code;
        return call(uri, DataCountry.class);
    }

    private <T> Flux<T> call(String uri, Class<T> type) {
        return webClient.get().uri(uri)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .retrieve().bodyToFlux(type);
    }
}
