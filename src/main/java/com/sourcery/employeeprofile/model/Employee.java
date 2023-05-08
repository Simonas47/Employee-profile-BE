package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String status;
    private List<EmploymentDate> employmentDates;
    private Integer imageId;
    private Integer titleId;
    private Boolean isManager;
}
