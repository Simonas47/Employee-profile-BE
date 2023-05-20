package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class NotificationSqlProvider {
    public static String getAllByEmployeeId(@Param("employeeId") Integer employeeId, Integer notificationLimit) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("notifications")
                .WHERE("notifications.employeeId = #{employeeId}")
                .ORDER_BY("isRead ASC, notificationCreatedAt DESC")
                .LIMIT("#{notificationLimit}");
        return sql.toString();
    }

    public static String setIsReadById(@Param("id") Integer id, @Param("isRead") boolean isRead) {
        SQL sql = new SQL()
                .UPDATE("notifications")
                .SET("isRead = #{isRead}")
                .WHERE("id = #{id}");
        return sql.toString();
    }

    public static String setIsReadByEmployeeId(@Param("employeeId") Integer employeeId,
                                               @Param("isRead") boolean isRead) {
        SQL sql = new SQL()
                .UPDATE("notifications")
                .SET("isRead = #{isRead}")
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
