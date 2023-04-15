package com.sourcery.employeeprofile.repository.sqlprovider;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.TeamMemberDto;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.UUID;

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

    public static String addTeamMembersToProject(@Param("projectId") UUID projectId,
                                               @Param("teamMembers") List<TeamMemberDto> teamMembers) {
        return "<script>" +
                "INSERT INTO projects_employees" +
                "(projectId, employeeId, teamMemberStatus, teamMemberStartDate, teamMemberEndDate)" +
                "VALUES" +
                "<foreach item='teamMember' collection='teamMembers' open='(' separator='),(' close=')'>" +
                "#{projectId}, #{teamMember.id}, #{teamMember.teamMemberStatus}, #{teamMember.teamMemberStartDate}, #{teamMember.teamMemberEndDate}" +
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

    public static String removeTeamMembersFromProject(UUID projectId) {
        SQL sql = new SQL()
                .DELETE_FROM("projects_employees")
                .WHERE("projectId = #{projectId}");
        return sql.toString();
    }

    public static String getProjectById(@Param("id") UUID id) {
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
                .ORDER_BY("p.startDate ASC");
        return sql.toString();
    }

    public static String createNewProjectRelationship() {
        SQL sql = new SQL()
                .INSERT_INTO("projects_employees")
                .VALUES("projectId", "#{projectId}")
                .VALUES("employeeId", "#{employeeId}")
                .VALUES("teamMemberStatus", "#{teamMemberStatus}")
                .VALUES("teamMemberStartDate", "#{teamMemberStartDate}")
                .VALUES("teamMemberEndDate", "teamMemberEndDate");
        return sql.toString();
    }

    public static String getProjectRelationshipsByProjectId(@Param("projectId") UUID projectId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("projects_employees")
                .WHERE("projects_employees.projectId = #{projectId}");
        return sql.toString();
    }

    // This is a soft delete (the project is not removed from the database)
    public static String deleteProjectById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .UPDATE("projects")
                .SET("deleted = true")
                .WHERE("id = #{id}");
        return sql.toString();
    }
}
