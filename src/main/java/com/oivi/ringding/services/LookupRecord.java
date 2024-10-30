package com.oivi.ringding.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookupRecord{

    private String phoneNum;
    private String identity;
    private boolean isCompany;



}