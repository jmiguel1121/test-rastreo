package com.demo.rastreo.consumer.gateway;

import com.demo.rastreo.model.osrm.OsrmRouterResp;
import reactor.core.publisher.Mono;

public interface OsrmRouterGateway {
    Mono<OsrmRouterResp> getInfoDistance(Double longitude1, Double latitude1, Double longitude2, Double latitude2);
}
