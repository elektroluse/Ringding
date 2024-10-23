package com.oivi.ringding.config;

import com.oivi.ringding.services.impl.DifferentScraper;
import com.oivi.ringding.services.impl.GulesiderScraper;
import com.oivi.ringding.services.impl.Ringding;
import com.oivi.ringding.services.Scraper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RingdingConfig {

    /*

        If you want to use a different scraper implementation
        change the return value in Scraper Scraper()

     */

    @Bean
    public Scraper Scraper() {
        return new GulesiderScraper();
        // ie : return new DifferentScraper()
    }


    @Bean
    public Ringding ringding(Scraper s) {
        return new Ringding(s);
    }
}
