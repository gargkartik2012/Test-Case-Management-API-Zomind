package com.example.zomind_assessment.dto;

import com.example.zomind_assessment.model.Priority;
import com.example.zomind_assessment.model.Status;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseDTO {

    private String title;
    private String description;
    private Status status;
    private Priority priority;

}
