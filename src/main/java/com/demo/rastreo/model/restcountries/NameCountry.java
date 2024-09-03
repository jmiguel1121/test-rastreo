package com.demo.rastreo.model.restcountries;

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
public class NameCountry {
    private String common;
    private Map<String, Map<String, String>> nativeName;


    public String getSpaName(){
        return nativeName.get("spa").get("common");
    }
}
