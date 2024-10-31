package com.oivi.ringding.services;

import com.oivi.ringding.domain.PhonebookRecord;

public interface PhonebookService {


    PhonebookRecord saveToPhonebook(LookupRecord pbRecord);
}
