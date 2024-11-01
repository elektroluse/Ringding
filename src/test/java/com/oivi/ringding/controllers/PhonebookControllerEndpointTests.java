package com.oivi.ringding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    
}
