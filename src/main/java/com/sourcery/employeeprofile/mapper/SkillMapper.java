package com.sourcery.employeeprofile.mapper;

import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.model.SkillEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SkillMapper {
    public static List<SkillDto> mapModelsToDtos(List<Skill> skillModelList, List<SkillEmployee> skillEmployeeList) {
        Integer counter = 0;
        List<SkillDto> outputList = new ArrayList<>();
        for (Skill skill : skillModelList) {
            SkillDto skillDto = new
                    SkillDto(skill.getId(),
                    skill.getSkillName(),
                    isChecked(skill, skillEmployeeList),
                    getSkillLevel(skill, skillEmployeeList),
                    skill.isSubItemsAreSkills(),
                    getIndent(skill, counter, skillModelList),
                    skill.getParentId(), skill.isLanguage(), isCategory(skill, skillModelList));
            outputList.add(skillDto);
        }
        return outputList;
    }

    private static boolean isCategory(Skill skill, List<Skill> skillModelList) {
        for (Skill childSkill : skillModelList) {
            if (skill.getId().equals(childSkill.getParentId())) return true;
        }
        return false;
    }

    private static Boolean isChecked(Skill skill, List<SkillEmployee> skillEmployeeList) {
        return getSkillEmployee(skill, skillEmployeeList) != null;
    }

    private static String getSkillLevel(Skill skill, List<SkillEmployee> skillEmployeeList) {
        SkillEmployee skillEmployee = getSkillEmployee(skill, skillEmployeeList);
        if (skillEmployee != null) return skillEmployee.getSkillLevel();
        return null;
    }

    private static SkillEmployee getSkillEmployee(Skill skill, List<SkillEmployee> skillEmployeeList) {
        for (SkillEmployee skillEmployee : skillEmployeeList) {
            if (skillEmployee.getSkillId().equals(skill.getId())) {
                return skillEmployee;
            }
        }
        return null;
    }

    private static Integer getIndent(Skill skill, Integer counter, List<Skill> skillModelList) {
        for (Skill skillModel : skillModelList) {
            if (skill.getParentId() == null) {
                return counter;
            } else if (Objects.equals(skillModel.getId(), skill.getParentId())) {
                counter++;
                return getIndent(skillModel, counter, skillModelList);
            }
        }
        throw new RuntimeException("no values in DB!");
    }
}
