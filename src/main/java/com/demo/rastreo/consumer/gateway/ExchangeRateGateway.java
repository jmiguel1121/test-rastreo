package com.demo.rastreo.consumer.gateway;

import com.demo.rastreo.model.exchangerate.ExchangeRateResp;
import com.demo.rastreo.model.fixer.FixerResp;
import reactor.core.publisher.Mono;

public interface ExchangeRateGateway {
    Mono<ExchangeRateResp> getByBaseAndCurrency(String base, String currency, String accessKey);
}
