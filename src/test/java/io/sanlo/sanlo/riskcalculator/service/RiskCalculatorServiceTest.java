package io.sanlo.sanlo.riskcalculator.service;
import io.sanlo.sanlo.riskcalculator.model.Company;
import io.sanlo.sanlo.riskcalculator.parser.RiskCaculatorParser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskCalculatorServiceTest {

    @Autowired
    private RiskCalculatorService riskCalculatorService;
    @Autowired
    private RiskCaculatorParser parser;

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRiskEmptyList() {
        List<Company> campanies = new ArrayList<>();
        riskCalculatorService.calculateRisk(campanies);
    }


    @Test
    public void testCalculateRisk() {
        List<Company> campanies =parser.loadCompanyAndFinanancialMetricsFromCSVFile();
        riskCalculatorService.calculateRisk(campanies);
    }

}
