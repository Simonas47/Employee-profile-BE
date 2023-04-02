package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectRelationship;
import com.sourcery.employeeprofile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping()
    public ResponseEntity<Project> createNewProject(@RequestPart("project") Project project) {
        try {
            return ResponseEntity.ok(projectService.createNewProject(project));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
        return projectService.getProjectById(id)
                .map(Project -> ResponseEntity.ok(Project))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @PostMapping()
    public ResponseEntity<List<ProjectRelationship>> createNewProjectRelationship(@RequestPart("project") Project project,
                                                                                  @RequestPart("employee") Employee employee) {
        return ResponseEntity.ok(projectService.createNewProjectRelationship(project, employee));
    }

    @GetMapping(value = "/byProject/{projectId}", produces = "application/json")
    public ResponseEntity<List<ProjectRelationship>> getProjectRelationshipsByProjectId(@PathVariable UUID projectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectRelationshipsByProjectId(projectId));
    }

    @GetMapping(value = "/byEmployee/{employeeId}", produces = "application/json")
    public ResponseEntity<List<ProjectRelationship>> getProjectRelationshipsByEmployeeId(@PathVariable UUID employeeId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectRelationshipsByEmployeeId(employeeId));
    }
}
