package com.demo.rastreo.repository;

import com.demo.rastreo.entity.Statistic;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StatisticRepository extends ReactiveCrudRepository<Statistic, Long> {
    @Query("SELECT s.* FROM statistic s WHERE s.country_code = :countryCode")
    Mono<Statistic> findByCountryCode(@Param("countryCode") String countryCode);

    @Query("SELECT s.* FROM statistic s WHERE s.distance = (SELECT MAX(distance) FROM statistic)")
    Mono<Statistic> getDistanceMax();

    @Query("SELECT s.* FROM statistic s WHERE s.distance = (SELECT MIN(distance) FROM statistic)")
    Mono<Statistic> getDistanceMin();

    @Query("SELECT (s.distance * s.requests) AS finalResult FROM statistic s)")
    Mono<Double> getDistanceAverage();
}
