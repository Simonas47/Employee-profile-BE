package com.sourcery.employeeprofile.repository.sqlprovider;

import com.sourcery.employeeprofile.dto.ProjectEmployeeDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class ProjectSqlProvider implements ProviderMethodResolver {
    public static String createNewProject() {
        SQL sql = new SQL()
                .INSERT_INTO("projects")
                .VALUES("title", "#{title}")
                .VALUES("startDate", "#{startDate}")
                .VALUES("endDate", "#{endDate}")
                .VALUES("description", "#{description}");
        return sql.toString();
    }

    public static String addEmployeesToProject(@Param("projectId") Integer projectId,
                                               @Param("projectEmployees") List<ProjectEmployeeDto> projectEmployees) {
        return "<script>" +
                "INSERT INTO projects_employees" +
                "(projectId, employeeId, projectEmployeeStartDate, projectEmployeeEndDate)" +
                "VALUES" +
                "<foreach item='projectEmployee' collection='projectEmployees' open='(' separator='),(' close=')'>" +
                "#{projectId}, #{projectEmployee.id}, " +
                "#{projectEmployee.projectEmployeeStartDate}, #{projectEmployee.projectEmployeeEndDate}" +
                "</foreach>" +
                "</script>";
    }

    public static String updateProject() {
        SQL sql = new SQL()
                .UPDATE("projects")
                .SET("title = #{title}")
                .SET("description = #{description}")
                .SET("startDate = #{startDate}")
                .SET("endDate = #{endDate}")
                .WHERE("id = #{id}");
        return sql.toString();
    }

    public static String removeEmployeesFromProject(Integer projectId) {
        SQL sql = new SQL()
                .DELETE_FROM("projects_employees")
                .WHERE("projectId = #{projectId}");
        return sql.toString();
    }

    public static String updateMyProject(@Param("projectId") Integer projectId,
                                         @Param("employeeId") Integer employeeId,
                                         @Param("responsibilities") String responsibilities
    ) {
        SQL sql = new SQL()
                .UPDATE("projects_employees")
                .SET("responsibilities = #{responsibilities}")
                .WHERE("employeeId = #{employeeId}")
                .AND()
                .WHERE("projectId = #{projectId}");
        return sql.toString();
    }

    public static String getProjectById(@Param("id") Integer id) {
        SQL sql = new SQL()
                .SELECT("p.id", "p.title", "p.startDate", "p.endDate", "p.description")
                .FROM("projects p")
                .WHERE("p.id = #{id}");
        return sql.toString();
    }

    public static String getAllProjects() {
        SQL sql = new SQL()
                .SELECT("p.id", "p.title", "p.startDate", "p.endDate", "p.description")
                .FROM("projects p")
                .WHERE("p.deleted = false")
                .ORDER_BY("p.startDate DESC");
        return sql.toString();
    }

    public static String getMyProjectsByEmployeeId(@Param("id") Integer id) {
        SQL sql = new SQL()
                .SELECT("p.id", "p.title", "p.description", "p.startDate", "p.endDate",
                        "pe.projectId", "pe.projectEmployeeStartDate", "pe.projectEmployeeEndDate",
                        "pe.responsibilities")
                .FROM("projects p")
                .LEFT_OUTER_JOIN("projects_employees pe ON p.id = pe.projectId")
                .WHERE("pe.employeeId = #{id}")
                .AND()
                .WHERE("p.deleted = false")
                .ORDER_BY("pe.projectEmployeeStartDate DESC");
        return sql.toString();
    }

    // This is a soft delete (the project is not removed from the database)
    public static String deleteProjectById(@Param("id") Integer id) {
        SQL sql = new SQL()
                .UPDATE("projects")
                .SET("deleted = true")
                .WHERE("id = #{id}");
        return sql.toString();
    }
}
