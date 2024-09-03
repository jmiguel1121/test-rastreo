package com.demo.rastreo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("statistic")
public class Statistic {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String countryCode;
    private String country;
    private Long distance;
    private String measure;
    private Long requests;
}
