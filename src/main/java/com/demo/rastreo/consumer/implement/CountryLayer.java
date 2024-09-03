package com.demo.rastreo.consumer.implement;

import com.demo.rastreo.consumer.gateway.CountryLayerGateway;
import com.demo.rastreo.model.countrylayer.CountryLayerResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CountryLayer implements CountryLayerGateway {

    private final WebClient webClient;

    public CountryLayer(@Value("${countrylayer.url.base}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }


    @Override
    public Mono<CountryLayerResp> getByCountryName(String name, String accessKey) {
        return webClient.get().uri("/name/{name}?access_key={accessKey}", name, accessKey)
                .retrieve().bodyToMono(CountryLayerResp.class);
    }
}
