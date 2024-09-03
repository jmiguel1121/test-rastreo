package com.demo.rastreo.consumer.gateway;

import com.demo.rastreo.consumer.implement.CountryLayer;
import com.demo.rastreo.model.countrylayer.CountryLayerResp;
import reactor.core.publisher.Mono;

public interface CountryLayerGateway {
    Mono<CountryLayerResp> getByCountryName(String name, String accessKey);
}
