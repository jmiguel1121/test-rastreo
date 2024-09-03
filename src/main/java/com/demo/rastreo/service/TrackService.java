package com.demo.rastreo.service;

import com.demo.rastreo.consumer.gateway.*;
import com.demo.rastreo.model.ParamRequest;
import com.demo.rastreo.util.UtilApp;
import com.demo.rastreo.util.UtilData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TrackService {

    @Value("${fixer.url.key}")
    private String fixerKey;
    @Value("${countrylayer.url.key}")
    private String countryLayerKey;
    @Value("${ipapi.url.key}")
    private String ipapiKey;
    @Value("${exchange-rate.url.key}")
    private String exchangeKey;
    @Value("${tracking.currency.base}")
    private String currencyBase;
    @Value("${tracking.country.code}")
    private String countryBase;
    @Autowired
    private CountryLayerGateway countryLayer;
    @Autowired
    private RestCountriesGateway restCountries;
    @Autowired
    private FixerGateway fixer;
    @Autowired
    private IpapiGateway ipapi;
    @Autowired
    private ExchangeRateGateway exchangeRate;
    @Autowired
    private OsrmRouterGateway osrmRouter;
    @Autowired
    private StatisticService statistic;

    public Mono<ParamRequest> getInfoTracking(String ip) {
        return ipapi.getByIp(ip, ipapiKey).flatMap(ipapi -> Mono.zip(
                restCountries.getByCountryName(ipapi.getCountryCode()).collectList(),
                restCountries.getByCountryName(countryBase).collectList()
        ).flatMap(zip1 -> {
            var countryInfo = zip1.getT1().stream().findFirst().get();
            var countryBase = zip1.getT2().stream().findFirst().get();
            double lat1 = countryBase.getCapitalInfo().getLatitude();
            double lon1 = countryBase.getCapitalInfo().getLongitude();
            double lat2 = countryInfo.getCapitalInfo().getLatitude();
            double lon2 = countryInfo.getCapitalInfo().getLongitude();
            return Mono.zip(
                    Mono.just(UtilApp.calculateDistance(lat1, lon1, lat2, lon2)),
                    exchangeRate.getByBaseAndCurrency(currencyBase, countryInfo.getCurrencyCode(), exchangeKey)
            ).flatMap(zip2 -> statistic.save(UtilData.buildStatistic(countryInfo, zip2.getT1()))
                    .flatMap(entity -> {
                        log.info("save record code country: " + entity.getCountryCode() + " - ip: " + ipapi.getIp());
                        return Mono.just(UtilData.buildParamRequest(ipapi, countryInfo, zip2.getT2(), zip2.getT1()));
                    }));
        }));
    }

}
