package com.bank.retail.service;

import com.bank.retail.model.Record;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordsServiceImplTest {

    RecordsServiceImpl recordsService = new RecordsServiceImpl();

    @Test
    void getFailedRecords() {
        //Given
        Record record1 = Record.builder()
                .reference(123)
                .startBalance(21.6)
                .endBalance(-20.23)
                .mutation("-41.83  ")
                .build();
        Record record2 = Record.builder()
                .reference(123)
                .startBalance(21.6)
                .endBalance(-20.23)
                .mutation("-41.83  ")
                .build();
        Record record3 = Record.builder()
                .reference(234)
                .startBalance(21.6)
                .endBalance(-20.22)
                .mutation("-41.83  ")
                .build();
        Record record4 = Record.builder()
                .reference(456)
                .startBalance(21.6)
                .endBalance(-20.23)
                .mutation("-41.83  ")
                .build();
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);


        //when
        List<Record> failedRecords = recordsService.getFailedRecords(records);

        //Then
        assertNotNull(failedRecords);
        assertFalse(failedRecords.isEmpty());
        assertEquals(3, failedRecords.size());

    }

    @Test
    void isReferenceNotUnique_recordIsNotUnique_returnsTrue() {
        //Given
        Record record1 = Record.builder()
                .reference(123)
                .build();
        Record record2 = Record.builder()
                .reference(123)
                .build();
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        //Then
        assertTrue(recordsService.isReferenceNotUnique(record1, records));
    }

    @Test
    void isReferenceNotUnique_recordIsUnique_returnsFalse() {
        //Given
        Record record1 = Record.builder()
                .reference(123)
                .build();
        Record record2 = Record.builder()
                .reference(1234)
                .build();
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        //Then
        assertFalse(recordsService.isReferenceNotUnique(record1, records));
    }

    @Test
    void isEndBalanceNotValid_validRecord_returnsFalse() {
        //Given
        Record record = Record.builder()
                .startBalance(21.6)
                .endBalance(-20.23)
                .mutation("-41.83  ")
                .build();

        //Then
        assertFalse(recordsService.isEndBalanceNotValid(record));
    }

    @Test
    void isEndBalanceNotValid_inValidRecord_returnsTrue() {
        //Given
        Record record = Record.builder()
                .startBalance(21.6)
                .endBalance(-20.22)
                .mutation("-41.83  ")
                .build();
        //Then
        assertTrue(recordsService.isEndBalanceNotValid(record));
    }
}