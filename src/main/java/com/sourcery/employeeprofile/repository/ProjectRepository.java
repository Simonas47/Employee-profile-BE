package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.ProjectSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface ProjectRepository {
    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProject")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewProject(ProjectDto project);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectById")
    Project getProjectById(@Param("id") UUID id);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getAllProjects")
    List<Project> getAllProjects();

    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProjectRelationship")
    void createNewProjectRelationship(UUID projectId, UUID employeeId);

    @InsertProvider(type = ProjectSqlProvider.class, method = "addEmployeesToProject")
    void addEmployeesToProject(@Param("projectId") UUID projectId, @Param("employees") List<EmployeeDto> employees);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectRelationshipsByProjectId")
    List<ProjectEmployee> getProjectRelationshipsByProjectId(@Param("projectId") UUID projectId);

    @UpdateProvider(type = ProjectSqlProvider.class, method = "deleteProjectById")
    void deleteProjectById(@Param("id") UUID id);

    @UpdateProvider(type = ProjectSqlProvider.class, method = "updateProject")
    void updateProject(ProjectDto project);

    @DeleteProvider(type = ProjectSqlProvider.class, method = "removeProjectEmployees")
    void removeProjectEmployees(UUID id);


}
