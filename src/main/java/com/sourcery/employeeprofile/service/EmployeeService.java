package com.sourcery.employeeprofile.service;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.sourcery.employeeprofile.dto.CreateEmployeeDto;
import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.EmploymentDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    @Value(value = "${auth0.domain}")
    private String domain;

    public EmployeeDto createNewEmployee(CreateEmployeeDto employeeDto) throws IOException {
        Image newImage = imageService.createNewImage(employeeDto.getImage());
        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .middleName(employeeDto.getMiddleName())
                .email(employeeDto.getEmail())
                .status(employeeDto.getStatus())
                .imageId(newImage.getId())
                .titleId(employeeDto.getTitleId())
                .isManager(employeeDto.getIsManager())
                .build();

        String auth0User = createAuth0User(employeeDto.getEmail(), employeeDto.getPassword());

        if (auth0User != null) {
            employeeRepository.createNewEmployee(employee);

            //Set the first date to today, if no dates exist.
            if (employee.getEmploymentDates() == null || employee.getEmploymentDates().size() == 0) {
                List<EmploymentDate> dates = new ArrayList<>();
                dates.add(new EmploymentDate().builder().hiringDate(new Date()).build());
                employee.setEmploymentDates(dates);
            }
            employmentDateRepository.setEmploymentDates(employee.getId(), employee.getEmploymentDates());
        }

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
        return employeeRepository.getByEmail(email);
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

    public boolean checkIfEmailExists(String email) {
        return employeeRepository.checkIfEmailExists(email);
    }

    private String createAuth0User(String email, String password) {
        try {
            HttpResponse<JsonNode> token = Unirest.post("https://" + domain + "/oauth/token")
                    .header("content-type", "application/json")
                    .body("{\"client_id\":\"p87WIifjISfh2ZC0RxKrOL10O9A7HlWF\",\"client_secret\":\"BDLTELXa1ZLKUOM7t4PYE98wuqvyx0OLU4s1BqDrGaUTKcYsM2OklGWW3PlKCX5O\",\"audience\":\"https://dev-0knqdj3ruz2l8l5k.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
                    .asJson();
            HttpResponse<JsonNode> response = Unirest.post("https://" + domain + "/api/v2/users")
                    .header("content-type", "application/json")
                    .header("authorization", "Bearer " + token.getBody().getObject().get("access_token"))
                    .body(String.format("{\"email\":\"%s\", \"password\":\"%s\", \"connection\":\"Username-Password-Authentication\"}", email, password))
                    .asJson();
            return response.toString();
        } catch (Exception e) {
            String error = e.getMessage();
            return error;
        }
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
