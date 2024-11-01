package com.oivi.ringding.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oivi.ringding.domain.PhonebookRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc

public class PhonebookControllerEndpointTests {

    private MockMvc mockMvc;

    @Autowired
    public PhonebookControllerEndpointTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @Test
    public void testLookupAndSaveEndpoint() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/db/lookup/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("92285833")


        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.phoneNum").value("92285833")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Oliver Bråten")

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.company").value(false)

        );
    }

    @Test
    public void insertPRReadAllReadLatestIntegrationTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper(); // Jackson mapper
        PhonebookRecord pr1 = PhonebookRecord.builder()
                .phoneNum("92285833")
                .name("Oliver Bråten")
                .isCompany(false)
                .build();

        PhonebookRecord pr2 = PhonebookRecord.builder()
                .phoneNum("92285833")
                .name("Oliver Bråten")
                .isCompany(false)
                .build();

        PhonebookRecord pr3 = PhonebookRecord.builder()
                .phoneNum("340593459")
                .name("Some name")
                .isCompany(true)
                .build();

        PhonebookRecord pr4 = PhonebookRecord.builder()
                .phoneNum("92285833")
                .name("Oliver Bråten")
                .isCompany(false)
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/db/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pr1))


        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$").value(1)


        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/db/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pr2))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/db/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pr3))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/db/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pr4))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/db/all/92285833")

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$",hasSize(3))
        );


        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/db/latest/92285833")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.recordId").value(4)
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/db/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pr3))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

      /*  mockMvc.perform(
                MockMvcRequestBuilders.get("/api/db/latest/92285833")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.recordId").value(4)
        );

       */
    }


}


