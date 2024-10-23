package com.oivi.ringding.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsInAnyOrder;

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

    @Test
    public void testPOSTEndpointWithJsonArray() throws Exception {
        String testArray = "[\"92285833\", \"23291700\",\"41353151\",\"48841237\"]";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/lookup/list")
                        .content(testArray)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                // Contains in any order is good enough for the number fields and makes the testcase less verbose
                MockMvcResultMatchers.jsonPath("$[*].phoneNum").value(containsInAnyOrder("92285833","23291700","41353151","48841237"))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isCompany").value(false)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].isCompany").value(true)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2].isCompany").value(false)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[3].isCompany").value(true)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].identity").value("Oliver Bråten")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].identity").value("NORSK GALLUP INSTITUTT AS")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2].identity").value("N/A")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[3].identity").value("NORSTAT AS")
        );

    }

    @Test
    public void testPOSTEndpointDisallowedFileType() throws Exception {

        MockMultipartFile pdfFile = new MockMultipartFile("file", "test.pdf", "application/pdf", "2342340234".getBytes());
        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/api/lookup/upload")
                        .file(pdfFile)


        ).andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType());
    }

    @Test
    public void testLookupFromMultipartFile() throws Exception{

        String testFileContent = "92285833\n" + "48841237";
        MockMultipartFile validTestFile = new MockMultipartFile("file", "test.txt", "text/plain", testFileContent.getBytes());
        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/api/lookup/upload")
                        .file(validTestFile)


        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[*].phoneNum").value(containsInAnyOrder("92285833","48841237"))
        ).andExpect(MockMvcResultMatchers.jsonPath("$[*].identity").value(containsInAnyOrder("Oliver Bråten","NORSTAT AS"))
        );

    }
}

