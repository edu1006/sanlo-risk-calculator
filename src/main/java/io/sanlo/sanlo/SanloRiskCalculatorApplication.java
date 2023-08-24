package io.sanlo.sanlo;

import io.sanlo.sanlo.riskcalculator.service.RiskCalculatorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SanloRiskCalculatorApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =		SpringApplication.run(SanloRiskCalculatorApplication.class, args);

		RiskCalculatorService riskCalculatorService = applicationContext.getBean(RiskCalculatorService.class);
		riskCalculatorService.calculateRisk();
	}

}
