package io.sanlo.sanlo.riskcalculator.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ParserUtils {
    @SneakyThrows
    public static List<Map<String, String>> loadMapFromSchema(CsvSchema schema, String pathFile) {
        CsvMapper mapper = new CsvMapper();
        File file = ResourceUtils.getFile(pathFile);
        MappingIterator<Map<String, String>> it = mapper
                .readerForMapOf(String.class)
                .with(schema)
                .readValues(file);
        it.next();
        return it.readAll();
    }

    public static Date convertStringToDate(String date, String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

}
