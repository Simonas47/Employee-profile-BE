package com.sourcery.employeeprofile.dto;

import com.sourcery.employeeprofile.model.EmploymentDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDto {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private String title;
    private String status;
    private List<EmploymentDate> employmentDates;
    private String imageType;
    private String imageBytes;
    private Boolean isManager;
}
