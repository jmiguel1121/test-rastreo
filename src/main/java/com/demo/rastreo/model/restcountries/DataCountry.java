package com.demo.rastreo.model.restcountries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCountry {
    private NameCountry name;
    private Map<String, Map<String, String>> currencies;
    //private List<String> capital;
    private String region;
    private String subregion;
    private Map<String, String> languages;
    private List<Double> latlng;
    private Integer population;
    private String fifa;
    private List<String> timezones;
    private String cca2;
    private Latlng capitalInfo;
    public String getLanguage(String code) {
        return this.languages.get(code);
    }

    public String getCurrencyCode() {
        String code = "";
        for (String key : currencies.keySet()) {
            code += code.isEmpty() ? key : "," + key;
        }
        return code;
    }

    public String getCurrencyName(String code) {
        return currencies.get(code).get("name");
    }

    public String getCurrencySymbol(String code) {
        return currencies.get(code).get("symbol");
    }
}
