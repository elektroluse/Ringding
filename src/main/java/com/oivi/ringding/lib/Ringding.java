package com.oivi.ringding.lib;


import java.util.List;

public class Ringding {

    private Scraper scraper;

    public Ringding(Scraper scraper) {
        this.scraper = scraper;
    }

    public LookupRecord lookup(String phoneNum){
        return scraper.lookup(phoneNum);
    }

    /*public List<LookupRecord> lookupBulkFile(String phoneNum){

    }*/
}
