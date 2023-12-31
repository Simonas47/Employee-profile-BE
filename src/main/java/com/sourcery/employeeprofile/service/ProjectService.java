package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.*;
import com.sourcery.employeeprofile.enums.NotificationTypes;
import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.model.Project;
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
            project.getProjectEmployees().forEach(projectEmployee ->
                    projectEmployeeIds.add(projectEmployee.getId()));
            List<Integer> projectEmployeeIdsList = projectEmployeeIds.stream().toList();
            notificationService.createNotification(
                    NotificationRequestDto.builder()
                            .employeeIds(projectEmployeeIdsList)
                            .projectId(project.getId())
                            .initiatorEmployeeId(project.getCreatorEmployeeId())
                            .notificationType(NotificationTypes.ADD_EMPLOYEE)
                            .build()
            );
        }

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public ProjectDto updateProject(ProjectDto project) throws IOException {
        Optional<ProjectDto> oldProjectDtoOptional = getProjectById(project.getId());
        if (oldProjectDtoOptional.isEmpty()) throw new RuntimeException("Project with the provided id is not found");

        projectRepository.updateProject(project);
        projectRepository.removeEmployeesFromProject(project.getId());

        if (project.getProjectEmployees() != null && project.getProjectEmployees().size() > 0)
            projectRepository.addEmployeesToProject(project.getId(), project.getProjectEmployees());

        createNotificationsForProjects(oldProjectDtoOptional.get(), project);

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    private void createNotificationsForProjects(ProjectDto oldProjectDto, ProjectDto project) {
        List<Integer> newProjectEmployeesIds = new ArrayList<>();
        project.getProjectEmployees().forEach(projectEmployee -> {
            boolean isNew = oldProjectDto.getProjectEmployees()
                    .stream()
                    .noneMatch(oldProjectEmployee ->
                            oldProjectEmployee.getId().equals(projectEmployee.getId()));
            if (isNew) newProjectEmployeesIds.add(projectEmployee.getId());
        });
        notificationService.createNotification(
                NotificationRequestDto.builder()
                        .employeeIds(newProjectEmployeesIds)
                        .projectId(project.getId())
                        .initiatorEmployeeId(project.getCreatorEmployeeId())
                        .notificationType(NotificationTypes.ADD_EMPLOYEE)
                        .build()
        );

        List<Integer> removedProjectEmployeesIds = new ArrayList<>();
        oldProjectDto.getProjectEmployees().forEach(oldProjectEmployee -> {
            boolean isRemoved = project.getProjectEmployees().stream().noneMatch(projectEmployee ->
                    projectEmployee.getId().equals(oldProjectEmployee.getId()));
            if (isRemoved) removedProjectEmployeesIds.add(oldProjectEmployee.getId());
        });
        notificationService.createNotification(
                NotificationRequestDto.builder()
                        .employeeIds(removedProjectEmployeesIds)
                        .projectId(project.getId())
                        .initiatorEmployeeId(project.getCreatorEmployeeId())
                        .notificationType(NotificationTypes.REMOVE_EMPLOYEE)
                        .build()
        );

        List<Integer> employeesToSendInformationUpdateNotificationsToIds = new ArrayList<>();
        project.getProjectEmployees().forEach(projectEmployee -> {
            if (newProjectEmployeesIds.stream().noneMatch(newProjectEmployeeId ->
                    projectEmployee.getId().equals(newProjectEmployeeId))
                    && removedProjectEmployeesIds.stream().noneMatch(removedProjectEmployeeId ->
                    projectEmployee.getId().equals(removedProjectEmployeeId))) {
                employeesToSendInformationUpdateNotificationsToIds.add(projectEmployee.getId());
            }
        });
        notificationService.createNotification(
                NotificationRequestDto.builder()
                        .employeeIds(employeesToSendInformationUpdateNotificationsToIds)
                        .projectId(project.getId())
                        .initiatorEmployeeId(project.getCreatorEmployeeId())
                        .notificationType(NotificationTypes.UPDATE_PROJECT_INFORMATION)
                        .build()
        );
    }

    public Boolean validateProjectEmployeeDates(ProjectEmployeeDto projectEmployee,
                                                List<EmploymentDate> employmentDates) {
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
                projectEmployeeErrors.add(new ProjectEmployeeErrorDto(
                        projectEmployee.getId(),
                        message,
                        employmentDates
                ));
            }
        }
        return projectEmployeeErrors;
    }

    public Optional<ProjectDto> getProjectById(Integer id) {
        Project project = projectRepository.getProjectById(id);
        List<ProjectEmployeeDto> projectEmployees = employeeRepository.getProjectEmployeesByProjectId(id);
        return Optional.of(
                ProjectDto.builder()
                        .id(project.getId())
                        .title(project.getTitle())
                        .startDate(project.getStartDate())
                        .endDate(project.getEndDate())
                        .description(project.getDescription())
                        .projectEmployees(projectEmployees)
                        .creatorEmployeeId(null)
                        .build()
        );
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();
        List<ProjectDto> projectsDto = new ArrayList<>();
        projects.forEach(project -> {
            List<ProjectEmployeeDto> projectEmployees = employeeRepository.getProjectEmployeesByProjectId(
                    project.getId()
            );
            projectsDto.add(
                    ProjectDto.builder()
                            .id(project.getId())
                            .title(project.getTitle())
                            .startDate(project.getStartDate())
                            .endDate(project.getEndDate())
                            .description(project.getDescription())
                            .projectEmployees(projectEmployees)
                            .creatorEmployeeId(null)
                            .build()
            );
        });
        return projectsDto;
    }

    public Optional<ProjectDto> deleteProjectById(Integer id) {
        projectRepository.deleteProjectById(id);
        return this.getProjectById(id);
    }

    public int updateMyProject(AddProjectEmployeeResponsibilitiesDto requestDto) {
        return projectRepository.updateMyProject(
                requestDto.getProjectId(),
                requestDto.getEmployeeId(),
                requestDto.getResponsibilities()
        );
    }

    public List<MyProjectDto> getMyProjectsByEmployeeId(Integer id) {
        return projectRepository.getMyProjectsByEmployeeId(id);
    }
}
