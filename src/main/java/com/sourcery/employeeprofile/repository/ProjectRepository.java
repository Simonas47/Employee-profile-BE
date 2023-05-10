package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.dto.ProjectEmployeeDto;
import com.sourcery.employeeprofile.dto.MyProjectDto;
import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.ProjectSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ProjectRepository {
    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProject")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewProject(ProjectDto project);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectById")
    Project getProjectById(@Param("id") Integer id);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getAllProjects")
    List<Project> getAllProjects();

    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProjectRelationship")
    void createNewProjectRelationship(
            Integer projectId,
            Integer employeeId,
            Date projectEmployeeStartDate,
            Date projectEmployeeEndDate
    );

    @InsertProvider(type = ProjectSqlProvider.class, method = "addEmployeesToProject")
    void addEmployeesToProject(@Param("projectId") Integer projectId,
                               @Param("projectEmployees") List<ProjectEmployeeDto> projectEmployees);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectRelationshipsByProjectId")
    List<ProjectEmployee> getProjectRelationshipsByProjectId(@Param("projectId") Integer projectId);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectRelationshipsByEmployeeId")
    List<ProjectEmployee> getProjectRelationshipsByEmployeeId(@Param("employeeId") Integer employeeId);

    @UpdateProvider(type = ProjectSqlProvider.class, method = "deleteProjectById")
    void deleteProjectById(@Param("id") Integer id);

    @UpdateProvider(type = ProjectSqlProvider.class, method = "updateProject")
    void updateProject(ProjectDto project);

    @DeleteProvider(type = ProjectSqlProvider.class, method = "removeEmployeesFromProject")
    void removeEmployeesFromProject(Integer id);

    @UpdateProvider(type = ProjectSqlProvider.class, method = "setMyProjectEmployeeResponsibilities")
    int setMyProjectEmployeeResponsibilities(@Param("projectId") Integer projectId, @Param("employeeId") Integer employeeId, @Param("responsibilities") String responsibilities);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getMyProjectsByEmployeeId")
    List<MyProjectDto> getMyProjectsByEmployeeId(@Param("id") Integer id);

}
