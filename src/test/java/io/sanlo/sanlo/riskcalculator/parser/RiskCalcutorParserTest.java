package io.sanlo.sanlo.riskcalculator.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskCalcutorParserTest {

    @Autowired
    RiskCaculatorParser parser;

    @Test
    public void loadCsvFileTest(){
        parser.loadCompanyAndFinanancialMetricsFromCSVFile();
    }

}
