package io.sanlo.sanlo.riskcalculator.parser;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.util.List;


public interface SanloParser <T>{
    List<T> loadDataFromCsv();
    CsvSchema loadSchema();
}
