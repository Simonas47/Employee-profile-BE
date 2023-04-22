package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.*;
import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        List<SearchSkillDto> categoriesAndSkills = new ArrayList<>();
        List<Skill> skillBottomCategories = skillsRepository.getBottomCategories();
        skillBottomCategories.forEach(skillSubcategory -> {
            String category = getTopCategoryName(skillSubcategory);
            List<Skill> skills = skillsRepository.getBottomSkills(skillSubcategory.getId());
            skills.forEach(skill -> {
                categoriesAndSkills.add(new SearchSkillDto(category, skill.getId(), skill.getSkillName()));
            });
        });
        return categoriesAndSkills
                .stream()
                .sorted(Comparator.comparing(SearchSkillDto::getCategory).thenComparing(SearchSkillDto::getSkillName))
                .collect(Collectors.toList());
    }

    private String getTopCategoryName(Skill skillCategory) {
        String category = skillCategory.getSkillName();
        while (skillCategory.getParentId() != null) {
            skillCategory = skillsRepository.getTopCategory(skillCategory.getParentId());
            category = skillCategory.getSkillName();
        }
        return category;
    }
}
