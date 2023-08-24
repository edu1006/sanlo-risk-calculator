package io.sanlo.sanlo.riskcalculator.riskengine;

import io.sanlo.sanlo.riskcalculator.model.CompanyRiskRating;
import io.sanlo.sanlo.riskcalculator.parser.RiskCaculatorParser;
import io.sanlo.sanlo.riskengine.services.RiskEngineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskEngineServiceTest {
    @Autowired
    RiskEngineService riskEngineService;

    @Autowired
    RiskCaculatorParser parser;

    @Test
    public void shouldCalculateRisk(){
        CompanyRiskRating risk = riskEngineService.getRiskCredit(parser.loadCompanyAndFinanancialMetricsFromCSVFile().get(0));
        assertThat(risk).isNotNull();
        assertThat(risk.getRiskRating()).isEqualTo("3 Moderate");
    }
}
