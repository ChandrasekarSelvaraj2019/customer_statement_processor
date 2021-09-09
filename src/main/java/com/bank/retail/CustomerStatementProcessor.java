package com.bank.retail;

import com.bank.retail.service.CsvRecordProcessor;
import com.bank.retail.service.XmlRecordProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static java.lang.System.exit;

@SpringBootApplication
@Slf4j
public class CustomerStatementProcessor implements CommandLineRunner {

    private final XmlRecordProcessor xmlRecordProcessor;

    private final CsvRecordProcessor csvRecordProcessor;

    public CustomerStatementProcessor(XmlRecordProcessor xmlRecordProcessor, CsvRecordProcessor csvRecordProcessor) {
        this.xmlRecordProcessor = xmlRecordProcessor;
        this.csvRecordProcessor = csvRecordProcessor;
    }


    public static void main(String[] args) {
        log.error("Starting the application: Customer Statement Processor");
        SpringApplication.run(CustomerStatementProcessor.class, args);
        log.error("Customer Statement Processor completed");
    }

    public void run(String... args) {
        log.info("Executing command line runner for Customer Statement Processor");
        try {
            xmlRecordProcessor.processXmlRecords();
            csvRecordProcessor.processCsvRecords();
            exit(0);
        } catch (IOException | JAXBException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            exit(1);
        }

    }


}
