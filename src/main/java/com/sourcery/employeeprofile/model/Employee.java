package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private UUID id;
    private String name;
    private String surname;
    private String middleName;
    private String status;
    private List<EmploymentDate> employmentDates;
    private UUID imageId;
    private UUID titleId;
}
