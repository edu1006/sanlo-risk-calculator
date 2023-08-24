package io.sanlo.sanlo.riskcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialMetrics {
    private Date date;
    private String appName;
    private Company company;
    private Float revenue;
    private Float marketingSpend;

}
