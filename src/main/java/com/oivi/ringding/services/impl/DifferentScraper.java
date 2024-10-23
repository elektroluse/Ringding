package com.oivi.ringding.services.impl;

import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.Scraper;

/*
      Just to have a different implementation for the Scanner Bean
      as an example in RingdingConfig


 */

public class DifferentScraper implements Scraper {

    @Override
    public LookupRecord lookup(String phoneNum){

        return new LookupRecord("","",false);
    }
}
