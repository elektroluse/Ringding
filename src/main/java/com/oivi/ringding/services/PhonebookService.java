package com.oivi.ringding.services;

import com.oivi.ringding.domain.PhonebookRecord;

import java.util.Optional;

public interface PhonebookService {


    Optional<PhonebookRecord> saveToPhonebook(LookupRecord pbRecord);

    int insertToPhonebook(PhonebookRecord pr);
}
