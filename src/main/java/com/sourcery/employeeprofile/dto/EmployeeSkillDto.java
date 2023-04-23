package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSkillDto {
    private boolean checked;
    private String skillLevel;
    private Integer skillId;
    private Integer employeeId;
}
