package com.bank.retail.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlType(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

    @XmlAttribute(name = "reference", required = true)
    protected Integer reference;
    @XmlElement(name = "accountNumber")
    protected String accountNumber;
    @XmlElement(name = "startBalance")
    protected Double startBalance;
    @XmlElement(name = "mutation")
    protected String mutation;
    @XmlElement(name = "description")
    protected String description;
    @XmlElement(name = "endBalance")
    protected Double endBalance;

}
