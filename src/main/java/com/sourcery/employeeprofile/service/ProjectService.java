package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.dto.ProjectEmployeeDto;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ProjectDto createNewProject(ProjectDto project) throws IOException {
        projectRepository.createNewProject(project);

        if (project.getProjectEmployees() != null && project.getProjectEmployees().size() > 0)
            projectRepository.addEmployeesToProject(project.getId(), project.getProjectEmployees());

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public ProjectDto updateProject(ProjectDto project) throws IOException {
        projectRepository.updateProject(project);
        projectRepository.removeEmployeesFromProject(project.getId());

        if (project.getProjectEmployees() != null && project.getProjectEmployees().size() > 0)
            projectRepository.addEmployeesToProject(project.getId(), project.getProjectEmployees());

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
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
                projectEmployees)
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
                    projectEmployees)
            );
        });
        return projectsDto;
    }

    public List<ProjectEmployee> createNewProjectRelationship(Integer projectId,
                                                              Integer employeeId,
                                                              Date projectEmployeeStartDate,
                                                              Date projectEmployeeEndDate) {
        projectRepository.createNewProjectRelationship(
                projectId,
                employeeId,
                projectEmployeeStartDate,
                projectEmployeeEndDate
        );
        return this.getProjectRelationshipsByProjectId(projectId);
    }

    public List<ProjectEmployee> getProjectRelationshipsByProjectId(Integer projectId) {
        return projectRepository.getProjectRelationshipsByProjectId(projectId);
    }

    public Optional<ProjectDto> deleteProjectById(Integer id) {
        projectRepository.deleteProjectById(id);
        return this.getProjectById(id);
    }
}
