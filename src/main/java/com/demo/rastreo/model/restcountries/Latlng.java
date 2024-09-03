package com.demo.rastreo.model.restcountries;

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
public class Latlng {
    private List<Double> latlng;

    public Double getLatitude() {
        return latlng.get(0);
    }

    public Double getLongitude() {
        return latlng.get(1);
    }
}
