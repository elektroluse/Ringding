package com.oivi.ringding.api.services.impl;

import com.oivi.ringding.api.services.LookupService;
import com.oivi.ringding.lib.LookupRecord;
import com.oivi.ringding.lib.Ringding;
import org.springframework.stereotype.Service;

@Service
public class LookupServiceImpl implements LookupService {

    private Ringding ringding;

    public LookupServiceImpl(Ringding ringding) {
        this.ringding = ringding;
    }

    @Override
    public LookupRecord lookup(String phoneNum) {
        return ringding.lookup(phoneNum);
    }
}
