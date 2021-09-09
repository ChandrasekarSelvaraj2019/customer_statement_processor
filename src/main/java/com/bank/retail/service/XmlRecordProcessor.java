package com.bank.retail.service;

import com.bank.retail.model.Record;
import com.bank.retail.model.Records;
import com.bank.retail.util.XmlToBeanConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class XmlRecordProcessor {

    private final RecordsService recordsService;

    private final ReportCreatorService reportCreatorService;

    private final String inputXmlPath;

    private final String outputForXmlPath;

    public XmlRecordProcessor(RecordsService recordsService, ReportCreatorService reportCreatorService,
                              @Value("${input.xml.location}") String inputXmlPath,
                              @Value("${output.xml.location}") String outputForXmlPath) {
        this.recordsService = recordsService;
        this.reportCreatorService = reportCreatorService;
        this.inputXmlPath = inputXmlPath;
        this.outputForXmlPath = outputForXmlPath;
    }

    public void processXmlRecords() throws IOException, JAXBException {
        String inputXmlFilePath = XmlRecordProcessor.class.getClassLoader().getResource(inputXmlPath).getPath();
        Path newFilePath = Paths.get(outputForXmlPath);
        File newFile = new File(newFilePath.toString());
        String xmlContent = new String(Files.readAllBytes(Paths.get(inputXmlFilePath)));
        Records recordsFromXML = XmlToBeanConverter.getRecordsFromXML(xmlContent);
        List<Record> failedRecordsFromXml = recordsService.getFailedRecords(recordsFromXML.getRecordList());
        reportCreatorService.createReport(failedRecordsFromXml, newFile.getPath());

    }
}
