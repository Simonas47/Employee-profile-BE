package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAchievementDto {
    private boolean checked;
    private Date startDate;
    private Date endDate;
    private UUID achievementId;
    private UUID employeeId;
}
