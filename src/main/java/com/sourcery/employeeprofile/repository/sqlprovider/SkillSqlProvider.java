package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class SkillSqlProvider {
    public static String getSkillRelationshipsByEmployeeId(@Param("employeeId") UUID employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills_employees")
                .WHERE("skills_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String deleteSkillEmployeeRelationshipById(@Param("employeeId")UUID employeeId, @Param("skillId") UUID skillId) {
        SQL sql = new SQL()
                .DELETE_FROM("skills_employees")
                .WHERE("skills_employees.skillId = #{skillId}", "skills_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String createNewSkillEmployeeRelationship() {
        SQL sql = new SQL()
                .INSERT_INTO("skills_employees")
                .VALUES("skillId", "#{skillId}")
                .VALUES("skillLevel", "CAST(#{skillLevel} AS SkillLevels)")
                .VALUES("employeeId", "#{employeeId}");
        return sql.toString();
    }
}
