package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    private Integer id;
    private String skillName;
    private Integer parentId;
    private boolean subItemsAreSkills;
    private String uniqueSkillIdentifier;
    private boolean isLanguage;
}
