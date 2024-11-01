package com.oivi.ringding.services.impl;

import com.oivi.ringding.dao.PhonebookRecordDao;
import com.oivi.ringding.domain.PhonebookRecord;
import com.oivi.ringding.mappers.impl.LookupRecordMapperImpl;
import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.PhonebookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhonebookServiceImpl implements PhonebookService {

    private final PhonebookRecordDao pbDao;
    private LookupRecordMapperImpl mapper;

    public PhonebookServiceImpl(PhonebookRecordDao pbDao, LookupRecordMapperImpl mapper) {
        this.pbDao = pbDao;
        this.mapper = mapper;
    }
    @Override
    public Optional<PhonebookRecord> saveToPhonebook(LookupRecord lookupRecord) {
        PhonebookRecord pr = mapper.mapTo(lookupRecord);
        int rowsUpdated = pbDao.create(pr);
        if(rowsUpdated == 0) {
            return Optional.empty();
        }
        return Optional.of(pr);
    }

    @Override
    public int insertToPhonebook(PhonebookRecord pr){
         return pbDao.create(pr);
    }



}
