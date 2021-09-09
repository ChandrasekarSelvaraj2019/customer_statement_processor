package com.bank.retail.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "records")
public class Records {
    @XmlElement(name = "record")
    protected List<Record> recordList;
}
