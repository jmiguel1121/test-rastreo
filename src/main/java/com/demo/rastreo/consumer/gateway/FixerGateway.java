package com.demo.rastreo.consumer.gateway;

import com.demo.rastreo.model.fixer.FixerResp;
import reactor.core.publisher.Mono;

public interface FixerGateway {

    Mono<FixerResp> getByBaseAndCurrency(String base, String symbol, String accessKey);
}
