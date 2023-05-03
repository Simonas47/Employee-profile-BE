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
public class AddProjectEmployeeResponsibilitiesDto {
    private Integer projectId;
    private Integer employeeId;
    private String responsibilities;

}
