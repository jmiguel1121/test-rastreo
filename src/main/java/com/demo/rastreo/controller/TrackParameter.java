package com.demo.rastreo.controller;

import com.demo.rastreo.model.ParamRequest;
import com.demo.rastreo.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/track/request")
public class TrackParameter {

    @Autowired
    private TrackService trackService;
//83.44.196.93
    @GetMapping("/info/{ip}")
    public Mono<ParamRequest> trackingRequest(@PathVariable("ip") String ip) {
        return this.trackService.getInfoTracking(ip);
    }

}
