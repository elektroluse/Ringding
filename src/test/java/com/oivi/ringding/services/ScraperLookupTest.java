package com.oivi.ringding.services;

import com.oivi.ringding.services.impl.GulesiderScraper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScraperLookupTest {
    /*
        The identity registered to numbers are subject to change,
        as of 17 October 2024 these tests are valid but this is not guaranteed to be the case
        in the future...


     */

    @Test
    public void testGulesiderScraperPersonLookup(){
        Scraper s = new GulesiderScraper();

        LookupRecord actual = s.lookup("92285833");
        LookupRecord expected = new LookupRecord("92285833","Oliver Bråten", false);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGulesiderScraperCompanyLookup(){
        Scraper s = new GulesiderScraper();

        LookupRecord actual = s.lookup("41412582");
        LookupRecord expected = new LookupRecord("41412582","TELENOR NORGE AS", true);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGulesiderScraperLookupNotFound(){
        Scraper s = new GulesiderScraper();

        LookupRecord actual = s.lookup("34342323582");
        LookupRecord expected = new LookupRecord("34342323582","N/A", false);
        assertThat(actual).isEqualTo(expected);
    }
}
