package com.example.zomind_assessment.repository;

import com.example.zomind_assessment.model.Priority;
import com.example.zomind_assessment.model.Status;
import com.example.zomind_assessment.model.TestCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TestCaseRepository extends MongoRepository<TestCase, String> {


    Page<TestCase> findAll(Pageable pageable);

    Page<TestCase> findByStatus(Status status, Pageable pageable);

    Page<TestCase> findByPriority(Priority priority, Pageable pageable);
    @Query("{ $and: ["
            + " { $or: [ { 'status': ?0 }, { ?0: null } ] },"
            + " { $or: [ { 'priority': ?1 }, { ?1: null } ] }"
            + "] }")
    Page<TestCase> findByStatusAndPriority(Status status, Priority priority, Pageable pageable);
}
