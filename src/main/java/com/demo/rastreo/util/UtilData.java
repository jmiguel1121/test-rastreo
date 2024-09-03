package com.demo.rastreo.util;

import com.demo.rastreo.common.Constant;
import com.demo.rastreo.entity.Statistic;
import com.demo.rastreo.model.ParamRequest;
import com.demo.rastreo.model.exchangerate.ExchangeRateResp;
import com.demo.rastreo.model.ipapi.IpapiResp;
import com.demo.rastreo.model.restcountries.DataCountry;

import java.time.LocalDateTime;
import java.util.List;

public class UtilData {

    public static ParamRequest buildParamRequest(IpapiResp ipapi, DataCountry country,
                                                 ExchangeRateResp ex, Double distance) {
        final String currency = country.getCurrencyCode();
        final String language = country.getLanguages().keySet().stream().toList().stream()
                .map(code -> country.getLanguage(code) + " (" + code + ")")
                .map(Object::toString).reduce("", String::concat);

        final List<String> timeList = country.getTimezones()
                .stream().filter(utc -> utc.length() > 8 && utc.contains("UTC")).toList()
                .stream().map(utc -> {
                    LocalDateTime time = UtilApp.getTimeFromUTCTimeZone(utc);
                    return time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " (" + utc + ")";
                }).toList();

        return ParamRequest.builder()
                .ip(ipapi.getIp())
                .currentDate(UtilApp.getStrDate(LocalDateTime.now(), Constant.FORMAT_DATE_RQ))
                .country(country.getName().getSpaName() + " (" + country.getName().getCommon() + ")")
                .ISOCode(country.getCca2())
                .language(language)
                .currency(currency + " (" + ex.getTargetCode() + " 1 " + ex.getTargetCode() + " = "
                        + ex.getConversionRate() + " " + ex.getBaseCode() + ")")
                .time(timeList)
                .distance(Math.round(distance) + " km")
                .build();

    }

    public static Statistic buildStatistic(DataCountry country, Double distance) {
        return Statistic.builder()
                .countryCode(country.getCca2())
                .country(country.getName().getCommon())
                .measure("KM")
                .distance(distance.longValue())
                .build();
    }
}
