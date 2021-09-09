package com.bank.retail.service;

import com.bank.retail.model.Record;

import java.util.List;

public interface RecordsService {
    List<Record> getFailedRecords(List<Record> actualRecords);
}
