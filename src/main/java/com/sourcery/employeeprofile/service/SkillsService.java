package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.*;
import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.sourcery.employeeprofile.mapper.SkillMapper.mapModelsToDtos;

@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    public List<SkillDto> getAllByEmployeeId(Integer employeeId) {
        return mapModelsToDtos(skillsRepository.getAll(), skillsRepository.getSkillsByEmployeeId(employeeId));
    }

    public void updateEmployeeSkills(ChangedSkillsDto changedSkills) {
        for (EmployeeSkillDto employeeSkill : changedSkills.getChangedSkills()) {
            skillsRepository.deleteSkillEmployeeRelationshipById(
                    employeeSkill.getEmployeeId(),
                    employeeSkill.getSkillId()
            );
            if (employeeSkill.isChecked()) {
                skillsRepository.createNewSkillEmployeeRelationship(
                        employeeSkill.getSkillId(),
                        employeeSkill.getSkillLevel(),
                        employeeSkill.getEmployeeId()
                );
            }
        }
    }

    public List<SearchSkillDto> getSkillsCategories() {
        Map<String, List<DropdownSkillDto>> categoriesAndSkillsMap = new TreeMap<>();
        List<Skill> skillBottomCategories = skillsRepository.getBottomCategories();
        skillBottomCategories.forEach(skillSubcategory -> {
            String category = getTopCategoryName(skillSubcategory);
            List<Skill> skills = skillsRepository.getBottomSkills(skillSubcategory.getId());
            List<DropdownSkillDto> dropdownSkills = new ArrayList<>();
            skills.forEach(skill -> dropdownSkills.add(new DropdownSkillDto(skill.getId(), skill.getSkillName())));
            if (categoriesAndSkillsMap.containsKey(category)) {
                dropdownSkills.addAll(categoriesAndSkillsMap.get(category));
                categoriesAndSkillsMap.put(category, sortSkillsList(dropdownSkills));
            } else {
                categoriesAndSkillsMap.put(category, sortSkillsList(dropdownSkills));
            }
        });
        List<SearchSkillDto> categoriesAndSkills = new ArrayList<>();
        categoriesAndSkillsMap.forEach((category, skills) -> {
            categoriesAndSkills.add(new SearchSkillDto(category, skills));
        });
        return categoriesAndSkills;
    }

    private String getTopCategoryName(Skill skillCategory) {
        String category = skillCategory.getSkillName();
        while (skillCategory.getParentId() != null) {
            skillCategory = skillsRepository.getTopCategory(skillCategory.getParentId());
            category = skillCategory.getSkillName();
        }
        return category;
    }

    private List<DropdownSkillDto> sortSkillsList(List<DropdownSkillDto> unsortedSkills) {
        return unsortedSkills
                .stream()
                .sorted(Comparator.comparing(DropdownSkillDto::getSkillName))
                .collect(Collectors.toList());
    }
}
