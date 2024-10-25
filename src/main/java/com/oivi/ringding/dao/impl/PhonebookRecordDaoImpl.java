package com.oivi.ringding.dao.impl;

import com.oivi.ringding.dao.PhonebookRecordDao;
import com.oivi.ringding.domain.PhonebookRecord;
import org.springframework.jdbc.core.JdbcTemplate;

public class PhonebookRecordDaoImpl implements PhonebookRecordDao {

    private final JdbcTemplate jdbcTemplate;

    public PhonebookRecordDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(PhonebookRecord pr) {
        jdbcTemplate.update("INSERT INTO phonebook (record_id,phone_num,name,is_company,created_at) VALUES (?, ?, ?, ?, ?)",
                pr.getRecordId(),pr.getPhoneNum(),pr.getName(),pr.isCompany(),pr.getCreatedAt()
                );
    }
}
