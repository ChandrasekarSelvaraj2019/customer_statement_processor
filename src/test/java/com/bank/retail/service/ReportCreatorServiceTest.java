package com.bank.retail.service;

import com.bank.retail.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportCreatorServiceTest {

    ReportCreatorService reportCreatorService = new ReportCreatorService();
    List<Record> records = new ArrayList<>();
    Path path1;


    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        Record record1 = Record.builder()
                .reference(123)
                .startBalance(21.6)
                .endBalance(-20.23)
                .mutation("-41.83  ")
                .description("record1")
                .build();
        Record record2 = Record.builder()
                .reference(123)
                .startBalance(21.6)
                .endBalance(-20.23)
                .mutation("-41.83  ")
                .description("record2")
                .build();
        Record record3 = Record.builder()
                .reference(234)
                .startBalance(21.6)
                .endBalance(-20.22)
                .mutation("-41.83  ")
                .description("record3")
                .build();
        records.add(record1);
        records.add(record2);
        records.add(record3);

        try {
            path1 = tempDir.resolve("output.csv");
        } catch (InvalidPathException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createReport() throws IOException {
        String fileLocation = path1.toString();
        reportCreatorService.createReport(records, fileLocation);
        assertTrue(path1.toFile().exists());
    }

    @Test
    void createCsvRecord() {

        List<String[]> csvRecord = reportCreatorService.createCsvRecord(records);

        assertNotNull(csvRecord);
        assertFalse(csvRecord.isEmpty());

        String[] header = csvRecord.get(0);
        assertNotNull(header);
        assertEquals("Reference", header[0]);
        assertEquals("Description", header[1]);

        String[] record1 = csvRecord.get(1);
        assertNotNull(record1);
        assertEquals("123", record1[0]);
        assertEquals("record1", record1[1]);
    }
}