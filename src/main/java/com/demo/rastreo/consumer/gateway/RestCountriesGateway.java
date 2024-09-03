package com.demo.rastreo.consumer.gateway;

import com.demo.rastreo.model.restcountries.DataCountry;
import com.demo.rastreo.model.restcountries.RestCountriesResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RestCountriesGateway {
    Flux<DataCountry> getByCountryName(String code);
}
