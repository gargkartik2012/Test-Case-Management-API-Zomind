package com.example.zomind_assessment;

import com.example.zomind_assessment.dto.TestCaseDTO;
import com.example.zomind_assessment.exception.ResourceNotFoundException;
import com.example.zomind_assessment.model.Priority;
import com.example.zomind_assessment.model.Status;
import com.example.zomind_assessment.model.TestCase;
import com.example.zomind_assessment.repository.TestCaseRepository;
import com.example.zomind_assessment.service.TestCaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestCaseServiceTest {

    @Mock
    private TestCaseRepository repository;

    @InjectMocks
    private TestCaseServiceImpl service;

    private TestCase testCase;

    @BeforeEach
    void setUp() {
        testCase = new TestCase();
        testCase.setId("1");
        testCase.setTitle("Test Case 1");
        testCase.setStatus(Status.PENDING);
        testCase.setPriority(Priority.HIGH);
    }

    @Test
    void testGetAllTestCases() {
        Pageable pageable = PageRequest.of(0, 10); // Create a Pageable object matching the method call
        Page<TestCase> expectedPage = new PageImpl<>(Collections.singletonList(testCase)); // Mocked pageable result

        when(repository.findAll(pageable)).thenReturn(expectedPage); // Match the Pageable argument

        Page<TestCase> result = service.getAllTestCases(0, 10, null, null); // Ensure arguments align with tested method

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals(testCase, result.getContent().getFirst());
    }

    @Test
    void testGetTestCaseById() {
        when(repository.findById("1")).thenReturn(Optional.of(testCase));

        TestCase result = service.getTestCaseById("1");

        assertNotNull(result);
        assertEquals("Test Case 1", result.getTitle());
    }

    @Test
    public void testGetTestCaseById_NotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getTestCaseById("2");
        });
        assertTrue(exception.getCause() instanceof ResourceNotFoundException);
    }


    @Test
    void testCreateTestCase() {
        TestCaseDTO testCaseDTO = new TestCaseDTO();
        testCaseDTO.setTitle("Test Case 1");
        testCaseDTO.setDescription("Sample Description");
        testCaseDTO.setStatus(Status.PENDING);
        testCaseDTO.setPriority(Priority.HIGH);

        when(repository.save(any(TestCase.class))).thenReturn(testCase);

        TestCase result = service.createTestCase(testCaseDTO);

        assertNotNull(result);
        assertEquals("Test Case 1", result.getTitle());
    }

    @Test
    void testUpdateTestCase() {
        TestCaseDTO testCaseDTO = new TestCaseDTO();
        testCaseDTO.setTitle("Updated Test Case");
        testCaseDTO.setDescription("Updated Description");
        testCaseDTO.setStatus(Status.PASSED);
        testCaseDTO.setPriority(Priority.LOW);

        when(repository.findById("1")).thenReturn(Optional.of(testCase));
        when(repository.save(any(TestCase.class))).thenReturn(testCase);

        TestCase updatedTestCase = service.updateTestCase("1", testCaseDTO);

        assertNotNull(updatedTestCase);
        assertEquals("Updated Test Case", updatedTestCase.getTitle());
        assertEquals(Status.PASSED, updatedTestCase.getStatus());
        assertEquals(Priority.LOW, updatedTestCase.getPriority());
    }

    @Test
    void testDeleteTestCase() {
        when(repository.findById("1")).thenReturn(Optional.of(testCase));
        doNothing().when(repository).delete(any(TestCase.class));

        service.deleteTestCase("1");

        verify(repository, times(1)).delete(testCase);
    }

    @Test
    void testDeleteTestCase_NotFound() {
        when(repository.findById("2")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> service.deleteTestCase("2"));

        assertEquals("com.example.zomind_assessment.exception.ResourceNotFoundException: Test Case not found with ID: 2", exception.getMessage());
    }

    @Test
    void testGetAllTestCasesWithPagination() {
        Page<TestCase> mockPage = new PageImpl<>(Collections.singletonList(testCase), PageRequest.of(0, 10), 1);

        when(repository.findAll(any(PageRequest.class))).thenReturn(mockPage);

        Page<TestCase> result = service.getAllTestCases(0, 10, null, null);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
    }
}
