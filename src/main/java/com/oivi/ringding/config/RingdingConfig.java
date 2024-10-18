package com.oivi.ringding.config;

import com.oivi.ringding.lib.GulesiderScraper;
import com.oivi.ringding.lib.Ringding;
import com.oivi.ringding.lib.Scraper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RingdingConfig {

    @Bean
    public Scraper gulesiderScraper() {
        return new GulesiderScraper();
    }

    /*
        Add beans for any scrapers implemented for any
        other phonebook providers


     */

    @Bean
    public Ringding ringding(Scraper s) {
        return new Ringding(s);
    }
}
