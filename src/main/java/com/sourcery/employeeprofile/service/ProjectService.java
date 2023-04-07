package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ProjectDto createNewProject(Project project) throws IOException {
        projectRepository.createNewProject(project);
        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<ProjectDto> getProjectById(UUID id) {
        Project project = projectRepository.getProjectById(id);
        List<EmployeeDto> employees = employeeRepository.getEmployeesByProjectId(id);
        return Optional.of(new ProjectDto(project.getId(),
                project.getTitle(),
                project.getStartDate(),
                project.getEndDate(),
                project.getDescription(),
                employees));
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();
        List<ProjectDto> projectsDto = new ArrayList<>();
        projects.forEach(project -> {
            List<EmployeeDto> employees = employeeRepository.getEmployeesByProjectId(project.getId());
            projectsDto.add(new ProjectDto(project.getId(),
                    project.getTitle(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getDescription(),
                    employees));
        });
        return projectsDto;
    }

    public List<ProjectEmployee> createNewProjectRelationship(UUID projectId, UUID employeeId) {
        projectRepository.createNewProjectRelationship(projectId, employeeId);
        return this.getProjectRelationshipsByProjectId(projectId);
    }

    public List<ProjectEmployee> getProjectRelationshipsByProjectId(UUID projectId) {
        return projectRepository.getProjectRelationshipsByProjectId(projectId);
    }

    public Optional<ProjectDto> deleteProjectById(UUID id) {
        projectRepository.deleteProjectById(id);
        return this.getProjectById(id);
    }
}