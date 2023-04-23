package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAchievementDto {
    private boolean checked;
    private Date issueDate;
    private Date expiringDate;
    private Integer achievementId;
    private Integer employeeId;
}
