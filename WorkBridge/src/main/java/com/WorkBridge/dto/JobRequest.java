package com.WorkBridge.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class JobRequest {
    private String title;
    private String description;
    private String category;
    private String company;
    private String location;
    private BigDecimal salary;
    private Long recruiterId; // 🔹 New field for recruiter
}
