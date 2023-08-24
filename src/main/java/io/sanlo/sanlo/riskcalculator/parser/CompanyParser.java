package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.sanlo.sanlo.riskcalculator.model.Company;
import io.sanlo.sanlo.riskcalculator.utils.ParserUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CompanyParser {
    @Value("${sanlo.companyFilePath}")
    private String companyFilePath;
    @SneakyThrows
    List<Company> loadCompaniesData(){
        CsvSchema schema = loadCompanySchema();
        List<Company> companies = new ArrayList<>();
        Company company = null;
        List<Map<String, String>> map = ParserUtils.loadMapFromSchema(schema,companyFilePath);
        for (Map<String, String> row: map){
            company = new Company();
            company.setId(Integer.valueOf(row.get("company_id")));
            company.setName(row.get("company_name"));
            company.setCountryCode(new Locale(row.get("country_code")));
            companies.add(company);
        }
        return companies;
    }

    private CsvSchema loadCompanySchema() {
        return CsvSchema.builder()
                .addColumn("company_id")
                .addColumn("company_name")
                .addColumn("country_code")
                .build();
    }
}
