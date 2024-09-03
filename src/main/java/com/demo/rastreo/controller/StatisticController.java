package com.demo.rastreo.controller;

import com.demo.rastreo.entity.Statistic;
import com.demo.rastreo.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/tracking/max")
    public Mono<Statistic> getStatisticsByMaxValue() {
        return statisticService.getMaxDistance();
    }

    @GetMapping("/tracking/min")
    public Mono<Statistic> getStatisticsByMinValue() {
        return statisticService.getMinDistance();
    }

    @GetMapping("/tracking/average")
    public Mono<Long> getStatisticsByAverageValue() {
        return statisticService.getAverageDistance();
    }

    @GetMapping("/tracking")
    public Flux<Statistic> getAllStatistics() {
        return statisticService.getAllDistance();
    }
}
