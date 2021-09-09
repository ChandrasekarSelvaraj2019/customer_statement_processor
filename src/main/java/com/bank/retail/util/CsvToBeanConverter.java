package com.bank.retail.util;

import com.bank.retail.model.Record;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvToBeanConverter {

    private CsvToBeanConverter() {

    }

    public static List<Record> getRecordsFromCsv(String fileLocation) {
        List<Record> records = new ArrayList<>();
        try {
            records = new CsvToBeanBuilder<Record>(new FileReader(fileLocation))
                    .withType(Record.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
