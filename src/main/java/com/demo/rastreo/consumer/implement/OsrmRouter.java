package com.demo.rastreo.consumer.implement;

import com.demo.rastreo.consumer.gateway.OsrmRouterGateway;
import com.demo.rastreo.model.osrm.OsrmRouterResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OsrmRouter implements OsrmRouterGateway {

    private final WebClient webClient;

    public OsrmRouter(@Value("${osrm.url.base}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }


    @Override
    @Cacheable(cacheNames = "OsrmRouterResp", key = "#longitude1 + '_' + #latitude1 + '_' + #longitude2 + '_' + #latitude2"
            , cacheManager = "cacheManager")
    public Mono<OsrmRouterResp> getInfoDistance(Double longitude1, Double latitude1, Double longitude2, Double latitude2) {
        String uri = "/" + longitude1 + "," + latitude1 + ";" + longitude2 + "," + latitude2 + "?overview=false";
        return call(uri, OsrmRouterResp.class);
    }

    private <T> Mono<T> call(String uri, Class<T> type) {
        return webClient.get().uri(uri)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .retrieve().bodyToMono(type);
    }
}
