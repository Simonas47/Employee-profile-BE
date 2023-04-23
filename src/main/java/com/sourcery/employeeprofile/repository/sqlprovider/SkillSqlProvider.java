package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class SkillSqlProvider {
    public static String getSkillRelationshipsByEmployeeId(@Param("employeeId") int employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills_employees")
                .WHERE("skills_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String deleteSkillEmployeeRelationshipById(@Param("employeeId") int employeeId,
                                                             @Param("skillId") int skillId) {
        SQL sql = new SQL()
                .DELETE_FROM("skills_employees")
                .WHERE("skills_employees.skillId = #{skillId}").AND()
                .WHERE("skills_employees.employeeId = #{employeeId}");
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

    public static String getAll() {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills");
        return sql.toString();
    }
}
