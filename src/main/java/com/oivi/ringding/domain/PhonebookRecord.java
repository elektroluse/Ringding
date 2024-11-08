package com.oivi.ringding.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/*
     @Data and @all/no args constructor
     ensures that jackson can serialize/deserialize the object to and from json.

     @Builder generates the code necessary to use the builder pattern to instantiate objects
     eg :
     Record r = Record.builder()
                .recordId()
                .phoneNum()
                ...etc
                .build();
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhonebookRecord {

    private Long recordId;
    private String phoneNum;
    private String name;
    private boolean isCompany;
    private Timestamp createdAt;

    /*
        If timestamp is not set then the record does not originate from db,
        A timestamp is then generated when the DAO calls getCreatedAt to pass the column to db.

        Should probably refactor this to be done somewhere else... not sure
     */
    public Timestamp getCreatedAt() {
        if(createdAt == null){
            createdAt = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.MICROS));
        }
        return createdAt;
    }
}
