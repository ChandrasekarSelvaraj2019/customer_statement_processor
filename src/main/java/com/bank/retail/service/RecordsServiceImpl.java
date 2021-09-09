package com.bank.retail.service;

import com.bank.retail.model.Record;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordsServiceImpl implements RecordsService {

    @Override
    public List<Record> getFailedRecords(List<Record> actualRecords) {
        return actualRecords.stream()
                .filter(record -> isReferenceNotUnique(record, actualRecords) || isEndBalanceNotValid(record))
                .collect(Collectors.toList());
    }

    boolean isReferenceNotUnique(Record record, List<Record> actualRecords) {
        long referenceCount = actualRecords.stream()
                .filter(record1 -> record1.getReference().equals(record.getReference()))
                .count();
        return referenceCount > 1;
    }

    boolean isEndBalanceNotValid(Record record) {
        BigDecimal startBalance = BigDecimal.valueOf(record.getStartBalance()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal endBalance = BigDecimal.valueOf(record.getEndBalance()).setScale(2, RoundingMode.HALF_UP);
        String mutation = record.getMutation().trim();
        String operation = mutation.substring(0, 1);
        BigDecimal mutationValue = new BigDecimal(mutation.substring(1)).setScale(2, RoundingMode.HALF_UP);
        boolean isEndBalanceValid = (operation.equals("+") && (startBalance.add(mutationValue).equals(endBalance)))
                || (operation.equals("-") && (startBalance.subtract(mutationValue)).equals(endBalance));
        return !isEndBalanceValid;
    }
}
