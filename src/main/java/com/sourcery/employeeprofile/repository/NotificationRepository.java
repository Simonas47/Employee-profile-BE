package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Notification;
import com.sourcery.employeeprofile.repository.sqlprovider.NotificationSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotificationRepository {

    @InsertProvider(type = NotificationSqlProvider.class, method = "createNotification")
    void createNotification(Notification notification);
    @SelectProvider(type = NotificationSqlProvider.class, method = "getAllByEmployeeId")
    List<Notification> getAllByEmployeeId(@Param("employeeId") Integer employeeId);

    @UpdateProvider(type = NotificationSqlProvider.class, method = "setIsReadById")
    void setIsReadById(@Param("id") Integer id, @Param("isRead") boolean isRead);

    @UpdateProvider(type = NotificationSqlProvider.class, method = "setIsReadByEmployeeId")
    void setIsReadByEmployeeId(@Param("employeeId") Integer employeeId, @Param("isRead") boolean isRead);

    @DeleteProvider(type = NotificationSqlProvider.class, method = "deleteByProjectId")
    void deleteByProjectId(@Param("projectId") Integer projectId);
}
