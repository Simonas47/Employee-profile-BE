package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class AchievementSqlProvider {
    public static String getAchievementRelationshipsByEmployeeId(@Param("employeeId") Integer employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("achievements_employees")
                .WHERE("achievements_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String deleteAchievementEmployeeRelationshipById(@Param("employeeId") Integer employeeId,
                                                                   @Param("achievementId") Integer achievementId) {
        SQL sql = new SQL()
                .DELETE_FROM("achievements_employees")
                .WHERE("achievements_employees.achievementId = #{achievementId}").AND()
                .WHERE("achievements_employees.employeeId = #{employeeId}");
        return sql.toString();
    }

    public static String createNewAchievementEmployeeRelationship() {
        SQL sql = new SQL()
                .INSERT_INTO("achievements_employees")
                .VALUES("achievementId", "#{achievementId}")
                .VALUES("issueDate", "#{issueDate}")
                .VALUES("expiringDate", "#{expiringDate}")
                .VALUES("employeeId", "#{employeeId}");
        return sql.toString();
    }

    public static String getAll() {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("achievements");
        return sql.toString();
    }
}
