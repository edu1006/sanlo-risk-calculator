package io.sanlo.sanlo.riskcalculator.model;

import lombok.Data;

@Data
public class CompanyRiskRating {

    private Integer companyId;
    private String companyName;
    private String appName;
    private Integer riskScore;
    private String riskRating;

}
