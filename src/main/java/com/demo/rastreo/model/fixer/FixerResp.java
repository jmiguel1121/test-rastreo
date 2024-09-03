package com.demo.rastreo.model.fixer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerResp {
    private Boolean success;
    private Integer timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
