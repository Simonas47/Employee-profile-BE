package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.*;
import com.sourcery.employeeprofile.enums.NotificationTypes;
import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.EmploymentDateRepository;
import com.sourcery.employeeprofile.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmploymentDateRepository employmentDateRepository;

    @Lazy
    @Autowired
    NotificationService notificationService;

    public ProjectDto createNewProject(ProjectDto project) throws IOException {
        projectRepository.createNewProject(project);

        if (project.getProjectEmployees() != null && project.getProjectEmployees().size() > 0) {
            projectRepository.addEmployeesToProject(project.getId(), project.getProjectEmployees());

            ArrayList<Integer> projectEmployeeIds = new ArrayList<>();
            project.getProjectEmployees().forEach(projectEmployee -> {
                    projectEmployeeIds.add(projectEmployee.getId());
            });
            List<Integer> projectEmployeeIdsList = projectEmployeeIds.stream().toList();
            notificationService.createNotification(
                            new NotificationRequestDto(
                            projectEmployeeIdsList,
                            null,
                            null,
                            project.getId(),
                            project.getCreatorEmployeeId(),
                            NotificationTypes.ADD_EMPLOYEE.name(),
                            false,
                            null));
        }

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public ProjectDto updateProject(ProjectDto project) throws IOException {
        List<ProjectEmployeeDto> oldProjectEmployees = employeeRepository.getProjectEmployeesByProjectId(project.getId());

        projectRepository.updateProject(project);
        projectRepository.removeEmployeesFromProject(project.getId());

        if (project.getProjectEmployees() != null && project.getProjectEmployees().size() > 0) {
            projectRepository.addEmployeesToProject(project.getId(), project.getProjectEmployees());
        }

        List<Integer> newProjectEmployeeIds = new ArrayList<>();
        project.getProjectEmployees().forEach(projectEmployee -> {
            boolean isNew = oldProjectEmployees.stream()
                    .noneMatch(oldProjectEmployee ->
                            oldProjectEmployee.getId().equals(projectEmployee.getId()));
            if (isNew) newProjectEmployeeIds.add(projectEmployee.getId());
        });
        notificationService.createNotification(
                new NotificationRequestDto(
                        newProjectEmployeeIds,
                        null,
                        null,
                        project.getId(),
                        project.getCreatorEmployeeId(),
                        NotificationTypes.ADD_EMPLOYEE.name(),
                        false,
                        null));

        List<Integer> removedProjectEmployeeIds = new ArrayList<>();
        oldProjectEmployees.forEach(oldProjectEmployee -> {
            boolean isRemoved = project.getProjectEmployees().stream().noneMatch(projectEmployee ->
                    projectEmployee.getId().equals(oldProjectEmployee.getId()));
            if (isRemoved) removedProjectEmployeeIds.add(oldProjectEmployee.getId());
        });
        notificationService.createNotification(
                new NotificationRequestDto(
                        removedProjectEmployeeIds,
                        null,
                        null,
                        project.getId(),
                        project.getCreatorEmployeeId(),
                        NotificationTypes.REMOVE_EMPLOYEE.name(),
                        false,
                        null));

        List<Integer> employeesToSendInformationUpdateNotificationsToIds = new ArrayList<>();
        project.getProjectEmployees().forEach(projectEmployee -> {
            if (newProjectEmployeeIds.stream().noneMatch(newProjectEmployeeId ->
                    projectEmployee.getId().equals(newProjectEmployeeId))
                    && removedProjectEmployeeIds.stream().noneMatch(removedProjectEmployeeId ->
                    projectEmployee.getId().equals(removedProjectEmployeeId))) {
                employeesToSendInformationUpdateNotificationsToIds.add(projectEmployee.getId());
            }
        });
        notificationService.createNotification(
                new NotificationRequestDto(
                        employeesToSendInformationUpdateNotificationsToIds,
                        null,
                        null,
                        project.getId(),
                        project.getCreatorEmployeeId(),
                        NotificationTypes.UPDATE_PROJECT_INFORMATION.name(),
                        false,
                        null));

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public Boolean validateProjectEmployeeDates(ProjectEmployeeDto projectEmployee, List<EmploymentDate> employmentDates) {
        Date projectEmployeeStartDate = projectEmployee.getProjectEmployeeStartDate();
        Date projectEmployeeEndDate = projectEmployee.getProjectEmployeeEndDate();

        for (EmploymentDate employmentDate : employmentDates) {
            Date hiringDate = employmentDate.getHiringDate();
            Date exitDate = employmentDate.getExitDate();

            if (exitDate == null) {
                if (projectEmployeeStartDate.compareTo(hiringDate) >= 0) {
                    return true;
                }
            } else if (projectEmployeeStartDate.compareTo(hiringDate) >= 0 &&
                       projectEmployeeStartDate.compareTo(exitDate) <= 0 &&
                       projectEmployeeEndDate != null &&
                       projectEmployeeEndDate.compareTo(hiringDate) >= 0 &&
                       projectEmployeeEndDate.compareTo(exitDate) <= 0) {
                return true;
            }
        }
        return false;
    }

    public List<ProjectEmployeeErrorDto> validateProjectEmployees(ProjectDto project) {
        List<ProjectEmployeeErrorDto> projectEmployeeErrors = new ArrayList<>();

        for (ProjectEmployeeDto projectEmployee : project.getProjectEmployees()) {
            List<EmploymentDate> employmentDates = employmentDateRepository.getEmploymentDates(projectEmployee.getId());
            String name = projectEmployee.getName() + " " + projectEmployee.getSurname();

            if (!validateProjectEmployeeDates(projectEmployee, employmentDates)) {
                String message;
                if (employmentDates.size() == 1) {
                    message = "Date should be within the employment period:";
                } else {
                    message = String.format("Date should be within the %s employment period:", name);
                }
                projectEmployeeErrors.add(new ProjectEmployeeErrorDto(projectEmployee.getId(), message, employmentDates));
            }
        }
        return projectEmployeeErrors;
    }

    public Optional<ProjectDto> getProjectById(Integer id) {
        Project project = projectRepository.getProjectById(id);
        List<ProjectEmployeeDto> projectEmployees = employeeRepository.getProjectEmployeesByProjectId(id);
        return Optional.of(new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getStartDate(),
                project.getEndDate(),
                project.getDescription(),
                projectEmployees,
                null)
        );
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();
        List<ProjectDto> projectsDto = new ArrayList<>();
        projects.forEach(project -> {
            List<ProjectEmployeeDto> projectEmployees = employeeRepository.getProjectEmployeesByProjectId(
                    project.getId()
            );
            projectsDto.add(new ProjectDto(
                    project.getId(),
                    project.getTitle(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getDescription(),
                    projectEmployees,
                    null)
            );
        });
        return projectsDto;
    }

    public Optional<ProjectDto> deleteProjectById(Integer id) {
        projectRepository.deleteProjectById(id);
        return this.getProjectById(id);
    }


    public int updateMyProject(Integer projectId, Integer employeeId, String responsibilities) {
        return projectRepository.updateMyProject(projectId, employeeId, responsibilities);
    }

    public List<MyProjectDto> getMyProjectsByEmployeeId(Integer id) {
        return projectRepository.getMyProjectsByEmployeeId(id);
    }
}
