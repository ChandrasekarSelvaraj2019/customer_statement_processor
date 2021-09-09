package com.bank.retail.service;

import com.bank.retail.CustomerStatementProcessor;
import com.bank.retail.model.Record;
import com.bank.retail.util.CsvToBeanConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CsvRecordProcessor {
    private final RecordsService recordsService;

    private final ReportCreatorService reportCreatorService;

    private final String inputCsvPath;

    private final String outputForCsvPath;

    public CsvRecordProcessor(RecordsService recordsService, ReportCreatorService reportCreatorService,
                              @Value("${input.csv.location}") String inputCsvPath,
                              @Value("${output.csv.location}") String outputForCsvPath) {
        this.recordsService = recordsService;
        this.reportCreatorService = reportCreatorService;
        this.inputCsvPath = inputCsvPath;
        this.outputForCsvPath = outputForCsvPath;
    }

    public void processCsvRecords() throws IOException {
        String inputCsvFilePath = CustomerStatementProcessor.class.getClassLoader().getResource(inputCsvPath).getPath();
        Path newFilePath = Paths.get(outputForCsvPath);
        File newFile = new File(newFilePath.toString());
        List<Record> failedRecordsFromCsv = CsvToBeanConverter.getRecordsFromCsv(inputCsvFilePath);
        List<Record> failedRecordsFromXml = recordsService.getFailedRecords(failedRecordsFromCsv);
        reportCreatorService.createReport(failedRecordsFromXml, newFile.getPath());

    }
}
