package com.oivi.ringding.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhonebookRecordDto {
    private Long recordId;
    private String phoneNum;
    private String name;
    private boolean isCompany;
    private Timestamp createdAt;
}
