package com.bank.retail.service;

import com.bank.retail.model.Record;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportCreatorService {

    public void createReport(List<Record> records, String fileName) throws IOException {
        List<String[]> csvRecord = createCsvRecord(records);
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName));
            writer.writeAll(csvRecord);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    List<String[]> createCsvRecord(List<Record> records) {
        String[] header = {"Reference", "Description"};
        List<String[]> list = new ArrayList<>();
        list.add(header);
        records.stream().forEach(
                record -> {
                    String[] csvRecord = {record.getReference().toString(), record.getDescription()};
                    list.add(csvRecord);
                }
        );
        return list;
    }

}
