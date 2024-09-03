package com.demo.rastreo.service;

import com.demo.rastreo.entity.Statistic;
import com.demo.rastreo.repository.StatisticRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StatisticService {
    private final StatisticRepository repository;

    public Mono<Statistic> getMaxDistance() {
        return repository.getDistanceMax();
    }

    public Mono<Statistic> getMinDistance() {
        return repository.getDistanceMin();
    }

    public Mono<Long> getAverageDistance() {
        return repository.findAll().collectList().flatMap(statistics -> Mono.zip(
                Mono.just(statistics.stream().map(st -> st.getDistance() * st.getRequests()).toList()
                        .stream().mapToLong(Long::longValue).sum()),
                Mono.just(statistics.stream().map(Statistic::getRequests).toList()
                        .stream().mapToLong(Long::longValue).sum())
        )).flatMap(zip -> {
            var res = zip.getT1() / zip.getT2();
            return Mono.just(res);
        });
    }

    public Flux<Statistic> getAllDistance() {
        return repository.findAll();
    }

    public Mono<Statistic> save(Statistic entity) {
        return repository.findByCountryCode(entity.getCountryCode())
                .flatMap(statistic -> repository.save(statistic.toBuilder().requests(statistic.getRequests() + 1).build()))
                .switchIfEmpty(repository.save(entity.toBuilder().requests(1L).build()));
    }
}