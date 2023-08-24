package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.sanlo.sanlo.riskcalculator.model.Company;
import io.sanlo.sanlo.riskcalculator.model.FinancialMetrics;
import io.sanlo.sanlo.riskcalculator.utils.ParserUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FinancialMetricsParser implements SanloParser{


    @Value("${sanlo.metricsFilePath}")
    private String metricsFilePath;




    @SneakyThrows
    @Override
    public List loadDataFromCsv() {
        CsvSchema schema = loadSchema();
        List<FinancialMetrics> metrics = new ArrayList<>();
        FinancialMetrics  metric = null;
        List<Map<String, String>> map = ParserUtils.loadMapFromSchema(schema,metricsFilePath);
        Company company = null;
        for (Map<String, String> row: map){
            metric = new FinancialMetrics();
            company = new Company();
            company.setId(Integer.valueOf(row.get("company_id")));
            metric.setDate(ParserUtils.convertStringToDate(row.get("date"),"yyyy-MM-dd" ));
            metric.setAppName(row.get("app_name"));
            metric.setCompany(company);
            metric.setRevenue(Float.valueOf(row.get("revenue")));
            metric.setMarketingSpend(!row.get("marketing_spend").isEmpty() ? Float.valueOf(row.get("marketing_spend")):null ) ;
            metrics.add(metric);
        }
        return metrics;
    }

    @Override
    public CsvSchema loadSchema() {
        return CsvSchema.builder()
                .addColumn("date")
                .addColumn("app_name")
                .addColumn("company_id")
                .addColumn("revenue")
                .addColumn("marketing_spend")
                .build();
    }
}
