package com.bank.retail.processor;

import com.bank.retail.model.Record;
import com.bank.retail.service.RecordsService;
import com.bank.retail.service.ReportCreatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class XmlRecordProcessorTest {

    RecordsService recordsService = mock(RecordsService.class);
    ReportCreatorService reportCreatorService = mock(ReportCreatorService.class);
    XmlRecordProcessor xmlRecordProcessor = new XmlRecordProcessor(recordsService, reportCreatorService, "records.xml", "failedRecordsFromXml.csv");

    List<Record> records = new ArrayList<>();

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
        records.add(record1);
        records.add(record2);
    }

    @Test
    void processXmlRecords() throws IOException, JAXBException {
        when(recordsService.getFailedRecords(anyList())).thenReturn(records);
        xmlRecordProcessor.processXmlRecords();

        verify(recordsService, atLeast(1)).getFailedRecords(anyList());
        verify(reportCreatorService, atLeast(1)).createReport(anyList(), anyString());
    }
}