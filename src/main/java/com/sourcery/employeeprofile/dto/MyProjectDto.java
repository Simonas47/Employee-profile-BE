package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MyProjectDto {
    private Integer id;
    private Integer employeeId;
    private Integer projectId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date projectEmployeeStartDate;
    private Date projectEmployeeEndDate;
    private String responsibilities;
}
