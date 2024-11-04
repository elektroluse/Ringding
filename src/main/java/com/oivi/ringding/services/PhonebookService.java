package com.oivi.ringding.services;

import com.oivi.ringding.domain.PhonebookRecord;

import java.util.List;
import java.util.Optional;

public interface PhonebookService {


    Optional<PhonebookRecord> saveToPhonebook(LookupRecord pbRecord);

    int insertToPhonebook(PhonebookRecord pr);

    Optional<PhonebookRecord> readOne(String number);

    List<PhonebookRecord> readAll();

    List<PhonebookRecord> readAll(String number);

    Optional<PhonebookRecord> findLatestNum(String number);

    Integer deleteOld(String number);
}
