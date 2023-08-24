package io.sanlo.sanlo.riskcalculator.service;

import io.sanlo.sanlo.riskcalculator.model.Company;
import io.sanlo.sanlo.riskcalculator.model.CompanyRiskRating;
import io.sanlo.sanlo.riskcalculator.parser.RiskCaculatorParser;
import io.sanlo.sanlo.riskengine.services.RiskEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RiskCalculatorService {

    @Autowired
    private RiskCaculatorParser parser;
    @Autowired
    private RiskEngineService service;

    public void calculateRisk(List<Company> companies) {
        if (companies == null  || companies.isEmpty()){
            throw new IllegalArgumentException("company list should not be empty");
        }
        List<CompanyRiskRating> ratings = new ArrayList<>();
        for (Company c: companies){
            ratings.add(service.getRiskCredit(c));
        }

        writeOutputRating(ratings);
    }
    public void calculateRisk() {
        List<Company> companies = parser.loadCompanyAndFinanancialMetricsFromCSVFile();
        this.calculateRisk(companies);
    }

    private void writeOutputRating(List<CompanyRiskRating> ratings) {
        for (CompanyRiskRating rating: ratings){
            try {
                parser.writeOutputRating (rating);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
