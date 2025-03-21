package com.example.zomind_assessment.controller;

import com.example.zomind_assessment.dto.TestCaseDTO;
import com.example.zomind_assessment.exception.ResourceNotFoundException;
import com.example.zomind_assessment.model.Priority;
import com.example.zomind_assessment.model.Status;
import com.example.zomind_assessment.model.TestCase;
import com.example.zomind_assessment.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService service;

    @GetMapping
    public ResponseEntity<Page<TestCase>> getAllTestCases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority) {
        Page<TestCase> testCases = service.getAllTestCases(page, size, status, priority);
        return ResponseEntity.ok(testCases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable String id) throws ResourceNotFoundException {
        TestCase testCase = service.getTestCaseById(id);
        return ResponseEntity.ok(testCase);
    }

    @PostMapping
    public ResponseEntity<TestCase> createTestCase(@RequestBody TestCaseDTO testCaseDTO) {
        TestCase createdTestCase = service.createTestCase(testCaseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTestCase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable String id, @RequestBody TestCaseDTO testCaseDTO) {
        TestCase updatedTestCase = service.updateTestCase(id, testCaseDTO);
        return ResponseEntity.ok(updatedTestCase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable String id) {
        service.deleteTestCase(id);
        return ResponseEntity.noContent().build();
    }
}
