package com.oivi.ringding.dao;

import com.oivi.ringding.dao.impl.PhonebookRecordDaoImpl;
import com.oivi.ringding.domain.PhonebookRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PhonebookRecordDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PhonebookRecordDaoImpl underTest;

    @Test
    public void testThatCreateRecordGeneratesCorrectSql(){

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        PhonebookRecord pr = PhonebookRecord.builder()
                .recordId(1L)
                .phoneNum("92285833")
                .name("Oliver Bråten")
                .isCompany(false)
                .createdAt(timestamp)
                .build();

        underTest.create(pr);
        verify(jdbcTemplate).update(
                eq("INSERT INTO phonebook (phone_num,name,is_company,created_at) VALUES (?, ?, ?, ?)"),
                eq("92285833"), eq("Oliver Bråten"), eq(false), eq(timestamp)
        );
    }

    @Test
    public void testThatFindByNumGeneratesCorrectSql(){
        underTest.findByNum("92285833");
        verify(jdbcTemplate).query(
                eq("SELECT * FROM phonebook WHERE phone_num = ? LIMIT 1"),
                ArgumentMatchers.<PhonebookRecordDaoImpl.PhonebookRowMapper>any(),
                eq("92285833")
        );
    }
}
