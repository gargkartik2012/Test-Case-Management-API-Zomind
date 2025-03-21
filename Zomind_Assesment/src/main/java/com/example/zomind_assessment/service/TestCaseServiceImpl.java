package com.example.zomind_assessment.service;

import com.example.zomind_assessment.dto.TestCaseDTO;
import com.example.zomind_assessment.exception.ResourceNotFoundException;
import com.example.zomind_assessment.model.Priority;
import com.example.zomind_assessment.model.Status;
import com.example.zomind_assessment.model.TestCase;
import com.example.zomind_assessment.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseRepository repository;

    @Override
    public Page<TestCase> getAllTestCases(int page, int size, Status status, Priority priority) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<TestCase> result;
        if (status == null && priority == null) {
            result = repository.findAll(pageable); // Fetch all
        } else if (status == null) {
            result = repository.findByPriority(priority, pageable); // Filter by priority only
        } else if (priority == null) {
            result = repository.findByStatus(status, pageable); // Filter by status only
        } else {
            result = repository.findByStatusAndPriority(status, priority, pageable);
        }

        return result != null ? result : Page.empty(pageable); // ✅ Prevent NullPointerException
    }

    @Override
    public TestCase getTestCaseById(String id) {
        log.info("Fetching test case with ID: {}", id);
        try {
            return repository.findById(id)
                    .orElseThrow(() -> {
                        log.error("Test case not found with ID: {}", id);
                        return new ResourceNotFoundException("Test Case not found with ID: " + id);
                    });
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TestCase createTestCase(TestCaseDTO testCaseDTO) {
        log.info("Creating new test case with title: {}", testCaseDTO.getTitle());
        TestCase testCase = TestCase.builder()
                .title(testCaseDTO.getTitle())
                .description(testCaseDTO.getDescription())
                .status(testCaseDTO.getStatus())
                .priority(testCaseDTO.getPriority())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        return repository.save(testCase);
    }

    @Override
    public TestCase updateTestCase(String id, TestCaseDTO testCaseDTO) {
        log.info("Updating test case with ID: {}", id);
        TestCase testCase = getTestCaseById(id); // No need to catch and wrap
        testCase.setTitle(testCaseDTO.getTitle());
        testCase.setDescription(testCaseDTO.getDescription());
        testCase.setStatus(testCaseDTO.getStatus());
        testCase.setPriority(testCaseDTO.getPriority());
        testCase.setUpdatedAt(new Date());
        return repository.save(testCase);
    }

    @Override
    public void deleteTestCase(String id) {
        log.info("Deleting test case with ID: {}", id);
        TestCase testCase = getTestCaseById(id);
        repository.delete(testCase);
    }

    // ✅ Fix: Return an empty page instead of null
    @Override
    public Page<TestCase> getAllTestCases() {
        return Page.empty();
    }
}
