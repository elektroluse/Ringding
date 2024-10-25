package com.oivi.ringding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication()
public class RingdingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RingdingApplication.class, args);
    }

}
