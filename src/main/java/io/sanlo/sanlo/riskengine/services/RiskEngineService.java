package io.sanlo.sanlo.riskengine.services;

import io.sanlo.sanlo.riskcalculator.model.Company;
import io.sanlo.sanlo.riskcalculator.model.CompanyRiskRating;
import io.sanlo.sanlo.riskcalculator.model.FinancialMetrics;
import io.sanlo.sanlo.riskengine.indicators.RiskScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskEngineService {


    @Autowired
    RiskScoreService riskScoreService;

    public CompanyRiskRating getRiskCredit(Company c){
        int riskScore = calculateRiskScore(c);
        CompanyRiskRating companyRiskRating = new CompanyRiskRating();

        companyRiskRating.setRiskRating( this.riskScoreService.getRiskRating(riskScore).getRating());
        companyRiskRating.setCompanyId(c.getId());
        companyRiskRating.setAppName(c.getFinancialMetrics().get(0).getAppName());
        companyRiskRating.setRiskScore(riskScore);
        companyRiskRating.setCompanyName(c.getName());
        return companyRiskRating;

    }

    private int calculateRiskScore(Company c) {
        double marketingSpend = c.getFinancialMetrics().get(0).getMarketingSpend();
        int payBackPeriod = findPayBackPeriod(c, marketingSpend);
        double ltv = findLTV(c);
        double cacRatio = calcCACRation(ltv, marketingSpend);
        return (int) riskScoreService.calculateScore(payBackPeriod,cacRatio);

    }

    private double calcCACRation(double ltv, double marketingSpend) {
        return ltv / marketingSpend;
    }

    private double findLTV(Company c) {
        double totalRevenuePerDay = 0;
        for (FinancialMetrics m: c.getFinancialMetrics()){
            totalRevenuePerDay+=m.getRevenue();
        }
        return totalRevenuePerDay;

    }

    private int findPayBackPeriod(Company c, double marketingSpend) {
        double totalRevenuePerDay = 0;
        int totalDays =0;
        for (FinancialMetrics m: c.getFinancialMetrics()){
            totalRevenuePerDay+=m.getRevenue();
            totalDays++;

            if (totalRevenuePerDay > marketingSpend){
                return totalDays;
            }
        }
        throw new IllegalCallerException("no valid payback");
    }
}
