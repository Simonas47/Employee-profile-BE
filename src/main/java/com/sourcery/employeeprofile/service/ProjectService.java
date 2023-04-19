package com.sourcery.employeeprofile.service;
import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.dto.TeamMemberDto;
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
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ProjectDto createNewProject(ProjectDto project) throws IOException {
        projectRepository.createNewProject(project);
        if (project.getTeamMembers() != null && project.getTeamMembers().size() > 0)
            projectRepository.addTeamMembersToProject(project.getId(), project.getTeamMembers());

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public ProjectDto updateProject(ProjectDto project) throws IOException {
        projectRepository.updateProject(project);
        projectRepository.removeTeamMembersFromProject(project.getId());

        if (project.getTeamMembers() != null && project.getTeamMembers().size() > 0)
            projectRepository.addTeamMembersToProject(project.getId(), project.getTeamMembers());

        return this.getProjectById(project.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<ProjectDto> getProjectById(UUID id) {
        Project project = projectRepository.getProjectById(id);
        List<TeamMemberDto> teamMembers = employeeRepository.getTeamMembersByProjectId(id);
        return Optional.of(new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getStartDate(),
                project.getEndDate(),
                project.getDescription(),
                teamMembers));
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();
        List<ProjectDto> projectsDto = new ArrayList<>();
        projects.forEach(project -> {
            List<TeamMemberDto> teamMembers = employeeRepository.getTeamMembersByProjectId(project.getId());
            projectsDto.add(new ProjectDto(
                    project.getId(),
                    project.getTitle(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getDescription(),
                    teamMembers));
        });
        return projectsDto;
    }

    public List<ProjectEmployee> createNewProjectRelationship(UUID projectId,
                                                              UUID employeeId,
                                                              String teamMemberStatus,
                                                              Date teamMemberStartDate,
                                                              Date teamMemberEndDate) {
        projectRepository.createNewProjectRelationship(projectId, employeeId, teamMemberStatus, teamMemberStartDate, teamMemberEndDate);
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
