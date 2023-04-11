package com.sourcery.employeeprofile.mapper;

import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.model.SkillEmployee;

import java.util.ArrayList;
import java.util.List;

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
                    skill.getParentId());
            outputList.add(skillDto);
        }
        return outputList;
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
        for (SkillEmployee skillEmployee: skillEmployeeList) {
            if (skillEmployee.getSkillId().equals(skill.getId())) {
                return skillEmployee;
            }
        }
        return null;
    }

    private static Integer getIndent(Skill iterateFrom, Integer counter, List<Skill> skillModelList) {
        for (Skill skill : skillModelList) {
            if (iterateFrom.getParentId() == null) {
                Integer tempCounter = counter;
                return tempCounter;
            } else if (skill.getId().equals(iterateFrom.getParentId())) {
                counter++;
                return getIndent(skill, counter, skillModelList);
            }
        }
        throw new RuntimeException("no values in DB!");
    }

}
