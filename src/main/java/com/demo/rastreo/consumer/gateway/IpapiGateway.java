package com.demo.rastreo.consumer.gateway;

import com.demo.rastreo.model.ipapi.IpapiResp;
import reactor.core.publisher.Mono;

public interface IpapiGateway {

    Mono<IpapiResp> getByIp(String ip, String accessKey);
}
