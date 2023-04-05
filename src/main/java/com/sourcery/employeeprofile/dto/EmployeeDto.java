package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDto {
    private UUID id;
    private String name;
    private String surname;
    private String middleName;
    private String title;
    private Date hiringDate;
    private Date exitDate;
    private String status;
    private String imageName;
    private String imageType;
    private byte[] imageBytes;
}
