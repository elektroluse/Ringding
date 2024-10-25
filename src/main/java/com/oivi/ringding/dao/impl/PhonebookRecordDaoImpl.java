package com.oivi.ringding.dao.impl;

import com.oivi.ringding.dao.PhonebookRecordDao;
import com.oivi.ringding.domain.PhonebookRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<PhonebookRecord> findByNum(String phoneNum) {
        List<PhonebookRecord> results = jdbcTemplate.query(
                "SELECT name FROM phonebook WHERE phone_num = ? LIMIT 1",
                new PhonebookRowMapper(), phoneNum);
        return results.stream().findFirst();
    }

    public static class PhonebookRowMapper implements RowMapper<PhonebookRecord> {

        @Override
        public PhonebookRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            return PhonebookRecord.builder()
                    .recordId(rs.getLong("record_id"))
                    .phoneNum(rs.getString("phone_num"))
                    .name(rs.getString("name"))
                    .isCompany(rs.getBoolean("is_company"))
                    .createdAt(rs.getTimestamp("created_at"))
                    .build();
        }
    }
}
