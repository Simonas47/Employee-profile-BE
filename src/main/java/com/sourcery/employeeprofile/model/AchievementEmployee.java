package com.sourcery.employeeprofile.model;

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
public class AchievementEmployee {
    private UUID id;
    private UUID achievementId;
    private Date issueDate;
    private Date expiringDate;
    private UUID employeeId;
}
