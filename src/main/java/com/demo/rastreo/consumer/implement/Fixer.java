package com.demo.rastreo.consumer.implement;

import com.demo.rastreo.consumer.gateway.FixerGateway;
import com.demo.rastreo.model.fixer.FixerResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Fixer implements FixerGateway {
    private final WebClient webClient;

    public Fixer(@Value("${fixer.url.base}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    @Override
    public Mono<FixerResp> getByBaseAndCurrency(String base, String symbol, String accessKey) {
        return webClient.get().uri("?base={base}&symbols={symbol}&access_key={accessKey}", base, symbol, accessKey)
                .retrieve().bodyToMono(FixerResp.class);
    }
}
