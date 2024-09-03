package com.demo.rastreo.model.ipapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpapiResp implements Serializable {
    private String ip;
    private String type;
    @JsonProperty("continent_code")
    private String continentCode;
    @JsonProperty("continent_name")
    private String continentName;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("region_code")
    private String regionCode;
    @JsonProperty("region_name")
    private String regionName;
    private String city;
    private String zip;
    private Double latitude;
    private Double longitude;
    private String msa;
    private String dma;
    private Double radius;
    @JsonProperty("ip_routing_type")
    private String ipRoutingType;
    @JsonProperty("connection_type")
    private String connectionType;
    private LocationIpapi location;
}
