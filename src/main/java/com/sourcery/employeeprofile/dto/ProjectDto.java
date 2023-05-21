package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectDto {
    private Integer id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;
    private List<ProjectEmployeeDto> projectEmployees;
    private Integer creatorEmployeeId;
}
