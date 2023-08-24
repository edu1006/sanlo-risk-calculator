package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.sanlo.sanlo.riskcalculator.utils.ParserUtils;
import io.sanlo.sanlo.riskengine.model.LTVCACRatioIndicator;
import io.sanlo.sanlo.riskengine.model.PaybackPeriodIndicator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LtvCacRatioParser implements SanloParser{


    @Value("${sanlo.ltvCacRatio}")
    private String paybackIndicator;

    @Override
    public List<LTVCACRatioIndicator> loadDataFromCsv(){
        CsvSchema schema = loadSchema();
        List<LTVCACRatioIndicator> indicators = new ArrayList<>();
        LTVCACRatioIndicator indicator = null;
        List<Map<String, String>> map = ParserUtils.loadMapFromSchema(schema,paybackIndicator);
        for (Map<String, String> row: map){
            indicator = new LTVCACRatioIndicator();
            indicator.setMinValue(Double.valueOf(row.get("min_ratio")));
            indicator.setMaxValue(Double.valueOf(row.get("max_ratio")));
            indicator.setValue(Integer.valueOf(row.get("value")));
            indicators.add(indicator);
        }
        return indicators;
    }

    @Override
    public CsvSchema loadSchema() {
        return CsvSchema.builder()
                .addColumn("min_ratio")
                .addColumn("max_ratio")
                .addColumn("value")
                .build();
    }

}
