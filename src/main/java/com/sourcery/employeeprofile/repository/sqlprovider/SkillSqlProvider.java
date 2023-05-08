package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SkillSqlProvider {
    public static String getSkillRelationshipsByEmployeeId(@Param("employeeId") Integer employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills_employees")
                .WHERE("skills_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String deleteSkillEmployeeRelationshipById(@Param("employeeId") Integer employeeId,
                                                             @Param("skillId") Integer skillId) {
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

    public static String getBottomCategories() {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills")
                .WHERE("skills.subItemsAreSkills = true");
        return sql.toString();
    }

    public static String getBottomSkills(@Param("parentId") Integer parentId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills")
                .WHERE("skills.parentId = #{parentId}");
        return sql.toString();
    }

    public static String getTopCategory(@Param("parentId") Integer parentId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("skills")
                .WHERE("skills.id = #{parentId}");
        return sql.toString();
    }
}
