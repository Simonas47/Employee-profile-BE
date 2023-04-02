package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectRelationship;
import com.sourcery.employeeprofile.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public Project createNewProject(Project project) throws IOException {
        projectRepository.createNewProject(project);
        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<Project> getProjectById(UUID id) {
        return projectRepository.getProjectById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public List<ProjectRelationship> createNewProjectRelationship(Project project, Employee employee) {
        projectRepository.createNewProjectRelationship(project, employee);
        return this.getProjectRelationshipsByProjectId(project.getId());
    }

    public List<ProjectRelationship> getProjectRelationshipsByProjectId(UUID projectId) {
        return projectRepository.getProjectRelationshipsByProjectId(projectId);
    }

    public List<ProjectRelationship> getProjectRelationshipsByEmployeeId(UUID employeeId) {
        return projectRepository.getProjectRelationshipsByEmployeeId(employeeId);
    }
}
