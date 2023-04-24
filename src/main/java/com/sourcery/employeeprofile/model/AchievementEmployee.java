package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementEmployee {
    private Integer id;
    private Integer achievementId;
    private Date issueDate;
    private Date expiringDate;
    private Integer employeeId;
}
