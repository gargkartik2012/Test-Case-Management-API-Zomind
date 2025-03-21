package com.example.zomind_assessment;


import com.example.zomind_assessment.controller.TestCaseController;
import com.example.zomind_assessment.model.TestCase;
import com.example.zomind_assessment.service.TestCaseService;
import com.example.zomind_assessment.service.TestCaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.mockito.Mockito.*;

@WebMvcTest(TestCaseController.class)
@ExtendWith(MockitoExtension.class)
public class TestCaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TestCaseService service;

    @Test
    void testGetAllTestCases() throws Exception {
        when(service.getAllTestCases(anyInt(), anyInt(), any(), any()))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/api/testcases"))
                .andExpect(status().isOk());
    }
}

