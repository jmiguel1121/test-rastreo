package com.demo.rastreo.consumer.implement;

import com.demo.rastreo.consumer.gateway.ExchangeRateGateway;
import com.demo.rastreo.model.exchangerate.ExchangeRateResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ExchangeRate implements ExchangeRateGateway {

    private final WebClient webClient;

    public ExchangeRate(@Value("${exchange-rate.url.base}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    @Override
    @Cacheable(cacheNames = "exchangeRateResp", key = "#base + '_' + #currency", cacheManager = "cacheManager")
    public Mono<ExchangeRateResp> getByBaseAndCurrency(String base, String currency, String accessKey) {
        String uri = "/" + accessKey + "/pair/" + base + "/" + currency;
        return call(uri, ExchangeRateResp.class);
    }

    private <T> Mono<T> call(String uri, Class<T> type) {
        return webClient.get().uri(uri)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .retrieve().bodyToMono(type);
    }
}
