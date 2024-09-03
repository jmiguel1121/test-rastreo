package com.demo.rastreo.model.osrm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteOsrm {
    private List<Leg> legs;
    private String weight_name;
    private Double weight;
    private Integer duration;
    private Double distance;
}
