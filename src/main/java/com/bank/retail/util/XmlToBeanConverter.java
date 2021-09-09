package com.bank.retail.util;

import com.bank.retail.model.Records;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;

public class XmlToBeanConverter {

    private static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(Records.class);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    private XmlToBeanConverter() {
    }

    public static Records getRecordsFromXML(InputStream inputStream) throws JAXBException {
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Records) jaxbUnmarshaller.unmarshal(inputStream);
    }

    public static Records getRecordsFromXML(String xml) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Records) unmarshaller.unmarshal(new StringReader(xml));
    }
}
