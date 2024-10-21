package com.oivi.ringding.services.impl;

import com.oivi.ringding.services.LookupRecord;
import com.oivi.ringding.services.Scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;

public class GulesiderScraper implements Scraper {

    private Document connectToUrl(String url){
        Document doc = null;
        try{
            doc = Jsoup.connect(url).get();
        }catch(IOException e){
            System.out.println("Cannot get document from web url");
        }
        return doc;
    }

    @Override
    public LookupRecord lookup(String phoneNum) {
        String personString = phoneNum + "/personer";
        String companyString = phoneNum + "/bedrifter";
        String url = "http://gulesider.no/" + companyString;

        Document doc = connectToUrl(url);
        Elements elements = doc.selectXpath("//*[@id=\"company-link-name\"]");
        if (!elements.isEmpty()) {
            return new LookupRecord(phoneNum, elements.get(0).text(), true);
        }

        url = "http://gulesider.no/" + personString;
        doc = connectToUrl(url);
        elements = doc.selectXpath("//*[@id=\"person-link-name\"]");
        if (!elements.isEmpty()) {
            return new LookupRecord(phoneNum, elements.get(0).text(), false);
        }
        return new LookupRecord(phoneNum, "N/A", false);
    }

}
