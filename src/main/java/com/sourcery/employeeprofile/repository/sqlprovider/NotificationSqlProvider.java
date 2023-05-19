package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class NotificationSqlProvider {
    public static String getAllByEmployeeId(@Param("employeeId") Integer employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("notifications")
                .WHERE("notifications.employeeId = #{employeeId}")
                .ORDER_BY("notificationCreatedAt DESC")
                .LIMIT(10);
        return sql.toString();
    }

    public static String setReadById(@Param("id") Integer id, @Param("read") boolean read) {
        SQL sql = new SQL()
                .UPDATE("notifications")
                .SET("read = #{read}")
                .WHERE("id = #{id}");
        return sql.toString();
    }

    public static String setReadByEmployeeId(@Param("employeeId") Integer employeeId, @Param("read") boolean read) {
        SQL sql = new SQL()
                .UPDATE("notifications")
                .SET("read = #{read}")
                .WHERE("employeeId = #{employeeId}");
        return sql.toString();
    }


    public static String createNotification() {
        SQL sql = new SQL()
                .INSERT_INTO("notifications")
                .VALUES("employeeId", "#{employeeId}")
                .VALUES("projectId", "#{projectId}")
                .VALUES("initiatorEmployeeId", "#{initiatorEmployeeId}")
                .VALUES("notificationType", "CAST(#{notificationType} AS NOTIFICATION_TYPE)");
        return sql.toString();
    }

    public static String deleteByProjectId(@Param("projectId") Integer projectId) {
        SQL sql = new SQL()
                .DELETE_FROM("notifications")
                .WHERE("projectId = #{projectId}");
        return sql.toString();
    }
}
