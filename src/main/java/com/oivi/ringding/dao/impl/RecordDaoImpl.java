package com.oivi.ringding.dao.impl;

import com.oivi.ringding.dao.RecordDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class RecordDaoImpl implements RecordDao {

    private final JdbcTemplate jdbcTemplate;

    public RecordDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
