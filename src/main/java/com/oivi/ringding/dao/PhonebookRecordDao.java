package com.oivi.ringding.dao;

import com.oivi.ringding.domain.PhonebookRecord;

import java.util.Optional;

public interface PhonebookRecordDao {
    void create(PhonebookRecord pr);

    //Optional<PhonebookRecord> findById(int id);
    Optional<PhonebookRecord> findByNum(String phoneNum);
}
