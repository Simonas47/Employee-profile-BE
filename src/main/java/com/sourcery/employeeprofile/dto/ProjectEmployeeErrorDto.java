package com.sourcery.employeeprofile.dto;

import java.util.List;

import com.sourcery.employeeprofile.model.EmploymentDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectEmployeeErrorDto {
    private Integer employeeId;
    private String message;
    private List<EmploymentDate> employmentDates;
}