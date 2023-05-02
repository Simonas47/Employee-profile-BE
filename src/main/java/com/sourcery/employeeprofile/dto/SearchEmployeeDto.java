package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchEmployeeDto {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private String title;
    private String status;
    private String imageType;
    private String imageBytes;
    private Boolean isManager;
}
