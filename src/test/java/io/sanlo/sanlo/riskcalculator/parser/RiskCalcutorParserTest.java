package io.sanlo.sanlo.riskcalculator.parser;

import io.sanlo.sanlo.riskcalculator.model.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskCalcutorParserTest {

    @Autowired
    RiskCaculatorParser parser;

    @Test
    public void loadCsvFileTest(){

        List<Company> data = parser.loadCompanyAndFinanancialMetricsFromCSVFile();
        assertThat(data).isNotNull();
    }

}
