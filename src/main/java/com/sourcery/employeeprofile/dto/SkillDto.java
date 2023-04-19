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
public class SkillDto {
    private UUID skillId;
    private String skillName;
    private Boolean checked;
    private String skillLevel;
    private boolean subItemsAreSkills;
    private Integer indent;
    private UUID parentSkillId;
    private boolean isLanguage;
    private boolean isCategory;
}
