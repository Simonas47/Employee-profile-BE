package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillEmployee {
    private Integer id;
    private Integer skillId;
    private String skillLevel;
    private Integer employeeId;
}
