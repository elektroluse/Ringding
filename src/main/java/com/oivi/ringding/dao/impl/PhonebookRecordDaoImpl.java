package com.oivi.ringding.dao.impl;

import com.oivi.ringding.dao.PhonebookRecordDao;
import com.oivi.ringding.domain.PhonebookRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PhonebookRecordDaoImpl implements PhonebookRecordDao {

    private final JdbcTemplate jdbcTemplate;

    public PhonebookRecordDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(PhonebookRecord pr) {
        jdbcTemplate.update("INSERT INTO phonebook (phone_num,name,is_company,created_at) VALUES (?, ?, ?, ?)",
                pr.getPhoneNum(),pr.getName(),pr.isCompany(),pr.getCreatedAt()
                );
    }

    @Override
    public Optional<PhonebookRecord> findByNum(String phoneNum) {
        List<PhonebookRecord> results = jdbcTemplate.query(
                "SELECT * FROM phonebook WHERE phone_num = ? LIMIT 1",
                new PhonebookRowMapper(), phoneNum);
        return results.stream().findFirst();
    }

    @Override
    public List<PhonebookRecord> findAllByNum(String phoneNum) {
        List<PhonebookRecord> results = jdbcTemplate.query(
                "SELECT * FROM phonebook WHERE phone_num = ?",
                new PhonebookRowMapper(), phoneNum);

        results = results.stream().toList();
        return results;
    }

    @Override
    public List<PhonebookRecord> findLatestNum(String phoneNum) {
        List<PhonebookRecord> results = jdbcTemplate.query(
                "SELECT * FROM phonebook WHERE phone_num = ? ORDER BY created_at DESC LIMIT 1",
                new PhonebookRowMapper(), phoneNum);

        results = results.stream().toList();
        return results;
    }

    /*
        Refactor later
        deleteOld
     */
    @Override
    public int deleteOld(String phoneNum) {
        int deletedRows = jdbcTemplate.update(
                "DELETE FROM phonebook WHERE phone_num = ? AND record_id NOT IN(SELECT record_id FROM phonebook WHERE phone_num = ? ORDER BY created_at DESC LIMIT 1)",
               phoneNum,phoneNum);
        return deletedRows;
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
