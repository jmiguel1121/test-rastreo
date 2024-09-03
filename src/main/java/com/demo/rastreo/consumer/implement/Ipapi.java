package com.demo.rastreo.consumer.implement;

import com.demo.rastreo.consumer.gateway.IpapiGateway;
import com.demo.rastreo.model.ipapi.IpapiResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Ipapi implements IpapiGateway {

    private final WebClient webClient;

    public Ipapi(@Value("${ipapi.url.base}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }


    @Override
    @Cacheable(cacheNames = "ipapiResp", key = "#ip", cacheManager = "cacheManager")
    public Mono<IpapiResp> getByIp(String ip, String accessKey) {
        String uri = "/" + ip + "?access_key=" + accessKey;
        return call(uri, IpapiResp.class);
    }

    private <T> Mono<T> call(String uri, Class<T> type) {
        return webClient.get().uri(uri)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .retrieve().bodyToMono(type);
    }
}
