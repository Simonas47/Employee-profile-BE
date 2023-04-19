package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class AchievementSqlProvider {
    public static String getAchievementRelationshipsByEmployeeId(@Param("employeeId") UUID employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("achievements_employees")
                .WHERE("achievements_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String deleteAchievementEmployeeRelationshipById(@Param("employeeId")UUID employeeId, @Param("achievementId") UUID achievementId) {
        SQL sql = new SQL()
                .DELETE_FROM("achievements_employees")
                .WHERE("achievements_employees.achievementId = #{achievementId}", "achievements_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String createNewAchievementEmployeeRelationship() {
        SQL sql = new SQL()
                .INSERT_INTO("achievements_employees")
                .VALUES("achievementId", "#{achievementId}")
                .VALUES("achievementStartDate", "#{achievementStartDate}")
                .VALUES("achievementEndDate", "#{achievementEndDate}")
                .VALUES("employeeId", "#{employeeId}");
        return sql.toString();
    }
}
