package com.bank.retail.util;


import com.bank.retail.model.Record;
import com.bank.retail.model.Records;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XmlToBeanConverterTest {

    @Test
    void getRecordsFromXML() throws IOException, JAXBException {
        //Given
        String inputFilepath = XmlToBeanConverterTest.class.getClassLoader().getResource("records.xml").getPath();
        String xmlContent = new String(Files.readAllBytes(Paths.get(inputFilepath)));
        //When
        Records recordsFromXML = XmlToBeanConverter.getRecordsFromXML(xmlContent);

        //Then
        assertNotNull(recordsFromXML);

        List<Record> records = recordsFromXML.getRecordList();
        assertNotNull(records);
        assertFalse(records.isEmpty());
        assertEquals(10, records.size());

        Record record = records.get(0);
        assertNotNull(record);
        assertEquals("NL69ABNA0433647324", record.getAccountNumber());
        assertEquals(130498, record.getReference());
        assertEquals(26.9, record.getStartBalance());
        assertEquals("-18.78", record.getMutation());
        assertEquals("Tickets for Peter Theuß", record.getDescription());
        assertEquals(8.12, record.getEndBalance());

    }
}