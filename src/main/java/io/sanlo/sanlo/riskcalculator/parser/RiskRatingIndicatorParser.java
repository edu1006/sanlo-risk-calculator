package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.sanlo.sanlo.riskcalculator.utils.ParserUtils;
import io.sanlo.sanlo.riskengine.indicators.RiskRating;
import io.sanlo.sanlo.riskengine.model.LTVCACRatioIndicator;
import io.sanlo.sanlo.riskengine.model.PaybackPeriodIndicator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RiskRatingIndicatorParser implements  SanloParser {


    @Value("${sanlo.riskRating}")
    private String riskRating;
    @Override
    public List<RiskRating> loadDataFromCsv() {
        CsvSchema schema = loadSchema();
        List<RiskRating> indicators = new ArrayList<>();
        RiskRating indicator = null;
        List<Map<String, String>> map = ParserUtils.loadMapFromSchema(schema,riskRating);
        for (Map<String, String> row: map){
            indicator = new RiskRating();
            indicator.setRating(row.get("rating"));
            indicator.setMinScore(Integer.valueOf(row.get("min_score")));
            indicator.setMaxScore(Integer.valueOf(row.get("max_score")));
            indicator.setDescription(row.get("description"));
            indicators.add(indicator);
        }
        return indicators;
 }

    @Override
    public CsvSchema loadSchema() {
        return CsvSchema.builder()
                .addColumn("rating")
                .addColumn("min_score")
                .addColumn("max_score")
                .addColumn("description")
                .build();
    }
}
