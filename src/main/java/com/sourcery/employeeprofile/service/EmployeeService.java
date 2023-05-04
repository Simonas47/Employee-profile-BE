package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.SearchAchievementDto;
import com.sourcery.employeeprofile.dto.SearchEmployeeDto;
import com.sourcery.employeeprofile.dto.SearchSkillDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.EmploymentDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmploymentDateRepository employmentDateRepository;
    @Autowired
    ImageService imageService;

    public EmployeeDto createNewEmployee(Employee employee, MultipartFile file) throws IOException {
        Image newImage = imageService.createNewImage(file);
        employee.setImageId(newImage.getId());
        employeeRepository.createNewEmployee(employee);

        if (employee.getEmploymentDates() != null && employee.getEmploymentDates().size() > 0)
            employmentDateRepository.setEmploymentDates(employee.getId(), employee.getEmploymentDates());

        return this.getById(employee.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<EmployeeDto> getById(Integer id) {
        Optional<EmployeeDto> employee = employeeRepository.getById(id);
        if (employee.isPresent()) {
            List<EmploymentDate> employmentDates = employmentDateRepository.getEmploymentDates(id);
            employee.get().setEmploymentDates(employmentDates);
        }
        return employee;
    }

    public Optional<EmployeeDto> getByEmail(String email) {
        Optional<EmployeeDto> employee = employeeRepository.getByEmail(email);
        return employee;
    }

    public List<SearchEmployeeDto> getEmployees(String searchValue,
                                                List<Integer> selectedSkillsIds,
                                                List<Integer> selectedAchievementsIds,
                                                Integer page,
                                                Integer size,
                                                Boolean isLimited) {
        String nameLike = "%" + searchValue + "%";
        String searchBySkillIdSqlCode = getSearchBySkillIdSqlCode(selectedSkillsIds);
        String searchByAchievementIdSqlCode = getSearchByAchievementIdSqlCode(selectedAchievementsIds);
        return employeeRepository.getEmployees(
                nameLike,
                searchBySkillIdSqlCode,
                searchByAchievementIdSqlCode,
                page,
                size,
                isLimited
        );
    }

    private String getSearchBySkillIdSqlCode(List<Integer> selectedSkillsIds) {
        StringBuilder sqlCode = new StringBuilder();
        for (int i = 0; i < selectedSkillsIds.size(); i++) {
            if (i > 0) {
                sqlCode.append(" AND");
            }
            sqlCode.append(" e1.id IN (SELECT se2.employeeId FROM skills_employees se2 " +
                    "WHERE se2.employeeId = e1.id AND se2.skillId = ");
            sqlCode.append(selectedSkillsIds.get(i));
            sqlCode.append(")");
        }
        return sqlCode.toString();
    }

    private String getSearchByAchievementIdSqlCode(List<Integer> selectedAchievementsIds) {
        StringBuilder sqlCode = new StringBuilder();
        for (int i = 0; i < selectedAchievementsIds.size(); i++) {
            if (i > 0) {
                sqlCode.append(" AND");
            }
            sqlCode.append(" e1.id IN (SELECT ae2.employeeId FROM achievements_employees ae2 " +
                    "WHERE ae2.employeeId = e1.id AND ae2.achievementId = ");
            sqlCode.append(selectedAchievementsIds.get(i));
            sqlCode.append(")");
        }
        return sqlCode.toString();
    }
}
