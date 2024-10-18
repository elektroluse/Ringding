package com.oivi.ringding.lib;

public record LookupRecord(String phoneNum, String identity, boolean isCompany) {

    @Override
    public String toString() {
        return phoneNum + " --> " + identity + " | Company : " + isCompany;
    }


}