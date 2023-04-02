package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectRelationship;
import com.sourcery.employeeprofile.repository.sqlprovider.ProjectSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface ProjectRepository {
    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProject")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewProject(Project project);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectById")
    Optional<Project> getProjectById(@Param("id") UUID id);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getAllProjects")
    List<Project> getAllProjects();

    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProjectRelationship")
    void createNewProjectRelationship(Project project, Employee employee);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectRelationshipsByProjectId")
    List<ProjectRelationship> getProjectRelationshipsByProjectId(@Param("projectId") UUID projectId);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectRelationshipsByEmployeeId")
    List<ProjectRelationship> getProjectRelationshipsByEmployeeId(@Param("employeeId") UUID employeeId);
}
