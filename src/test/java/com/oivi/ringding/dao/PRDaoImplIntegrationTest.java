package com.oivi.ringding.dao;

import com.oivi.ringding.dao.impl.PhonebookRecordDaoImpl;
import com.oivi.ringding.domain.PhonebookRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PRDaoImplIntegrationTest {

    private PhonebookRecordDaoImpl underTest;

    @Autowired
    public PRDaoImplIntegrationTest(PhonebookRecordDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatPrCanBeCreatedAndRetrievedFromDb(){

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        // Reading the timestamp value back from db loses precision,
        // so this is a temporary solution for now
        timestamp.setNanos(0);
        PhonebookRecord pr = PhonebookRecord.builder()
                .recordId(1L)
                .phoneNum("92285833")
                .name("Oliver Bråten")
                .isCompany(false)
                .createdAt(timestamp)
                .build();
        underTest.create(pr);

        Optional<PhonebookRecord> result = underTest.findByNum("92285833");
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(pr);
    }
}
