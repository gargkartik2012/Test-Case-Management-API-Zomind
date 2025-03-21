package com.example.zomind_assessment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest   // Loads the full application context
@AutoConfigureMockMvc  // Configures MockMvc so you can perform HTTP calls in tests
public class TestCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllTestCasesIntegration() throws Exception {
        // Send a GET request to /api/testcases and expect a 200 OK response.
        mockMvc.perform(get("/api/testcases"))
                .andExpect(status().isOk());
    }
}