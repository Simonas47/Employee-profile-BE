package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeSkillDto;
import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.dto.ChangedSkillsDto;
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

    public void updateEmployeeSkill(ChangedSkillsDto request) {
        for (EmployeeSkillDto employeeSkill : request.getChangedSkills())  {
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

}
