package com.oivi.ringding.mappers.impl;

import com.oivi.ringding.domain.PhonebookRecord;
import com.oivi.ringding.mappers.Mapper;
import com.oivi.ringding.services.LookupRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LookupRecordMapperImpl implements Mapper<LookupRecord, PhonebookRecord> {

    private ModelMapper modelMapper;

    public LookupRecordMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.typeMap(LookupRecord.class, PhonebookRecord.class).addMapping(LookupRecord::getIdentity,PhonebookRecord::setName);
        modelMapper.typeMap(LookupRecord.class, PhonebookRecord.class).addMapping(LookupRecord::getPhoneNum,PhonebookRecord::setPhoneNum);
        modelMapper.typeMap(LookupRecord.class, PhonebookRecord.class).addMapping(LookupRecord::isCompany,PhonebookRecord::setCompany);


    }
    
    @Override
    public PhonebookRecord mapTo(LookupRecord lookupRecord) {
        return modelMapper.map(lookupRecord, PhonebookRecord.class);
    }

    @Override
    public LookupRecord mapFrom(PhonebookRecord phonebookRecord) {
        return modelMapper.map(phonebookRecord, LookupRecord.class);
    }
}
