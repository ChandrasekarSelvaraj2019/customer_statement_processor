package com.bank.retail.util;

import com.bank.retail.model.Record;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvToBeanConverterTest {

    @Test
    void getRecordsFromCsv() {
        //Given
        String inputFilepath = CsvToBeanConverterTest.class.getClassLoader().getResource("records.csv").getPath();
        //When
        List<Record> records = CsvToBeanConverter.getRecordsFromCsv(inputFilepath);

        //Then
        assertNotNull(records);
        assertFalse(records.isEmpty());
        assertEquals(10, records.size());

        Record record = records.get(0);
        assertNotNull(record);
        assertEquals("NL91RABO0315273637", record.getAccountNumber());
        assertEquals(194261, record.getReference());
        assertEquals(21.6, record.getStartBalance());
        assertEquals("-41.83  ", record.getMutation());
        assertEquals("Clothes from Jan Bakker        ", record.getDescription());
        assertEquals(-20.23, record.getEndBalance());
    }
}