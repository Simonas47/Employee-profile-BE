package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectsEmployeeResponsibilityDto {
    private UUID projectId;
    private UUID employeeId;
    private String responsibility;

}
