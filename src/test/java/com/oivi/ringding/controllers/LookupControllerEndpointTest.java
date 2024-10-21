package com.oivi.ringding.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class LookupControllerEndpointTest {

    private MockMvc mockMvc;
    @Autowired
    public LookupControllerEndpointTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @Test
    public void testGETEndpointWithParam() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/lookup/92285833")


        ).andExpect(
                MockMvcResultMatchers.status().isOk()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.phoneNum").value("92285833")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.identity").value("Oliver Bråten")

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isCompany").value(false)

        );
    }

}

