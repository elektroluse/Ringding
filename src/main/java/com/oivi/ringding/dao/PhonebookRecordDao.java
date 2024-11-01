package com.oivi.ringding.dao;

import com.oivi.ringding.domain.PhonebookRecord;

import java.util.List;
import java.util.Optional;

public interface PhonebookRecordDao {
    int create(PhonebookRecord pr);

    //Optional<PhonebookRecord> findById(int id);
    Optional<PhonebookRecord> findByNum(String phoneNum);

    List<PhonebookRecord> findAllByNum(String phoneNum);

    Optional<PhonebookRecord> findLatestNum(String phoneNum);

    int deleteOld(String phoneNum);

    List<PhonebookRecord> allNumsWithName(String name);
}
