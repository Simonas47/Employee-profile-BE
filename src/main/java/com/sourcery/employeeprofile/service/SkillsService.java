package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.model.SkillEmployee;
import com.sourcery.employeeprofile.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.mapper.SkillMapper.mapModelsToDtos;

@Service
public class SkillsService {

    @Autowired
    SkillsRepository skillsRepository;

    public List<SkillDto> getAllByEmployeeId(UUID employeeId) {
        return mapModelsToDtos(skillsRepository.getAll(), skillsRepository.getSkillsByEmployeeId(employeeId));
    }

    public void updateEmployeeSkill(UUID skillId, UUID employeeId, boolean checked, String skillLevel) {
        skillsRepository.deleteSkillEmployeeRelationshipById(employeeId, skillId);
        if (checked) {
            skillsRepository.createNewSkillEmployeeRelationship(skillId, skillLevel, employeeId);
        }
    }
}
