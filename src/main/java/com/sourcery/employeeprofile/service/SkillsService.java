package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.SearchSkillDto;
import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sourcery.employeeprofile.mapper.SkillMapper.mapModelsToDtos;

@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    public List<SkillDto> getAllByEmployeeId(UUID employeeId) {
        return mapModelsToDtos(skillsRepository.getAll(), skillsRepository.getSkillsByEmployeeId(employeeId));
    }

    public void updateEmployeeSkill(UUID skillId, UUID employeeId, boolean checked, String skillLevel) {
        if (checked) {
            skillsRepository.deleteSkillEmployeeRelationshipById(employeeId, skillId);
            skillsRepository.createNewSkillEmployeeRelationship(skillId, skillLevel, employeeId);
        } else {
            skillsRepository.deleteSkillEmployeeRelationshipById(employeeId, skillId);
        }
    }

    public List<SearchSkillDto> getSkillsCategories() {
        List<SearchSkillDto> categoriesAndSkills = new ArrayList<>();
        List<Skill> skillBottomCategories = skillsRepository.getBottomCategories();
        skillBottomCategories.forEach(skillCategory -> {
            String category = getFullCategoryName(skillCategory);
            List<Skill> skills = skillsRepository.getBottomSkills(skillCategory.getId());
            List<String> skillsNames = new ArrayList<>();
            skills.forEach(skill -> skillsNames.add(skill.getSkillName()));
            categoriesAndSkills.add(new SearchSkillDto(category, skillsNames));
        });
        return categoriesAndSkills
                .stream()
                .sorted(Comparator.comparing(SearchSkillDto::getCategories))
                .collect(Collectors.toList());
    }

    private String getFullCategoryName(Skill skillCategory) {
        StringBuilder category = new StringBuilder(skillCategory.getSkillName());
        while (skillCategory.getParentId() != null) {
            skillCategory = skillsRepository.getTopCategory(skillCategory.getParentId());
            category.insert(0, "/");
            category.insert(0, skillCategory.getSkillName());
        }
        return category.toString();
    }
}
