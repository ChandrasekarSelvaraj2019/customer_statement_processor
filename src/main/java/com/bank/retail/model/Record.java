package com.bank.retail.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@XmlType(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
