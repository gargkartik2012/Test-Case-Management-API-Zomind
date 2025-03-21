package com.example.zomind_assessment.service;


import com.example.zomind_assessment.dto.TestCaseDTO;
import com.example.zomind_assessment.exception.ResourceNotFoundException;
import com.example.zomind_assessment.model.Priority;
import com.example.zomind_assessment.model.Status;
import com.example.zomind_assessment.model.TestCase;
import org.springframework.data.domain.Page;



public interface TestCaseService {
    Page<TestCase> getAllTestCases(int page, int size, Status status, Priority priority);
    TestCase getTestCaseById(String id) throws ResourceNotFoundException;
    TestCase createTestCase(TestCaseDTO testCaseDTO);
    TestCase updateTestCase(String id, TestCaseDTO testCaseDTO);
    void deleteTestCase(String id);


    Page<TestCase> getAllTestCases();
}
