package io.sanlo.sanlo.riskcalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Company {

    @JsonProperty("company_id")
    private Integer id;
    @JsonProperty("company_name")
    private String name;
    @JsonProperty("country_code")
    private Locale countryCode;

    @JsonIgnore
    List<FinancialMetrics> financialMetrics;
}
