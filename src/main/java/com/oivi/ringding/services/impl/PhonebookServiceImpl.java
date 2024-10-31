package com.oivi.ringding.services.impl;

import com.oivi.ringding.dao.PhonebookRecordDao;
import com.oivi.ringding.domain.PhonebookRecord;
import com.oivi.ringding.mappers.Mapper;
import com.oivi.ringding.mappers.impl.LookupRecordMapperImpl;
import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.PhonebookService;
import org.springframework.stereotype.Service;

@Service
public class PhonebookServiceImpl implements PhonebookService {

    private final PhonebookRecordDao pbDao;
    private LookupRecordMapperImpl mapper;

    public PhonebookServiceImpl(PhonebookRecordDao pbDao, LookupRecordMapperImpl mapper) {
        this.pbDao = pbDao;
        this.mapper = mapper;
    }
    @Override
    public PhonebookRecord saveToPhonebook(LookupRecord lookupRecord) {
        PhonebookRecord pr = mapper.mapTo(lookupRecord);
        pbDao.create(pr);
        return pr;
    }



}
