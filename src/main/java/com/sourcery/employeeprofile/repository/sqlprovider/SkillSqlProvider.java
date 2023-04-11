package com.sourcery.employeeprofile.repository.sqlprovider;

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
}
