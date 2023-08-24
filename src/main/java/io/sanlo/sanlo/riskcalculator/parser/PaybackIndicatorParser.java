package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.sanlo.sanlo.riskcalculator.utils.ParserUtils;
import io.sanlo.sanlo.riskengine.model.PaybackPeriodIndicator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PaybackIndicatorParser {


    @Value("${sanlo.paybackIndicator}")
    private String paybackIndicator;

    @SneakyThrows
    public List<PaybackPeriodIndicator> loadPaybackIndicator(){
        CsvSchema schema = loadCompanySchema();
        List<PaybackPeriodIndicator> indicators = new ArrayList<>();
        PaybackPeriodIndicator indicator = null;
        List<Map<String, String>> map = ParserUtils.loadMapFromSchema(schema,paybackIndicator);
        for (Map<String, String> row: map){
            indicator = new PaybackPeriodIndicator();
            indicator.setMinDays(Integer.valueOf(row.get("min_days")));
            indicator.setMaxDays(Integer.valueOf(row.get("max_days")));
            indicator.setValue(Integer.valueOf(row.get("value")));
            indicators.add(indicator);
        }
        return indicators;
    }

    private CsvSchema loadCompanySchema() {
        return CsvSchema.builder()
                .addColumn("min_days")
                .addColumn("max_days")
                .addColumn("value")
                .build();
    }

}
