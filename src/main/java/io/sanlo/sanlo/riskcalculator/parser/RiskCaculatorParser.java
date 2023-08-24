package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.sanlo.sanlo.riskcalculator.model.Company;
import io.sanlo.sanlo.riskcalculator.model.CompanyRiskRating;
import io.sanlo.sanlo.riskcalculator.model.FinancialMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RiskCaculatorParser {

    @Value("${sanlo.ratingFilePath}")
    private String outputRiskRating;
    @Autowired
    CompanyParser companyParser;
    @Autowired
    FinancialMetricsParser financialMetricsParser;

    public List<Company> loadCompanyAndFinanancialMetricsFromCSVFile() {
        List<Company> companies = companyParser.loadDataFromCsv();
        List<FinancialMetrics> metrics =financialMetricsParser.loadDataFromCsv();
        joinCompaniesAndMetrics(companies,metrics);
        return companies;
    }

    private void joinCompaniesAndMetrics(List<Company> companies, List<FinancialMetrics> metrics) {
        List<FinancialMetrics> listMetrics = null;
        for (Company c: companies){
            listMetrics = metrics.stream().filter(m -> m.getCompany().getId().equals(c.getId())).collect(Collectors.toList());
            Collections.sort(metrics, Comparator.comparing(FinancialMetrics::getDate));
            c.setFinancialMetrics(listMetrics);

        }
    }

    public void writeOutputRating(CompanyRiskRating riskRating) throws IOException{

        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper.schemaFor(CompanyRiskRating.class)
                .withHeader()
                .withColumnSeparator(',');

        ObjectWriter csvWriter = csvMapper.writerFor(CompanyRiskRating.class).with(csvSchema);
        File file = new File(outputRiskRating+ "-" + riskRating.getAppName()+".csv");
        System.out.println(file.getAbsoluteFile());

        FileWriter writer = new FileWriter(file);

        try {
            csvWriter.writeValues(writer).writeAll(Collections.singletonList(riskRating));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
