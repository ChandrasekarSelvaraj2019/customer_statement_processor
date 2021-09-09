package com.bank.retail.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlType(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

    @XmlAttribute(name = "reference", required = true)
    @CsvBindByName(column = "Reference")
    protected Integer reference;
    @XmlElement(name = "accountNumber")
    @CsvBindByName(column = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "startBalance")
    @CsvBindByName(column = "Start Balance")
    protected Double startBalance;
    @XmlElement(name = "mutation")
    @CsvBindByName(column = "Mutation")
    protected String mutation;
    @XmlElement(name = "description")
    @CsvBindByName(column = "Description")
    protected String description;
    @XmlElement(name = "endBalance")
    @CsvBindByName(column = "End Balance")
    protected Double endBalance;

}
