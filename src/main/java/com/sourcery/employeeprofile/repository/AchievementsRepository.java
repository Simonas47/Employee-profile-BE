package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Achievement;
import com.sourcery.employeeprofile.model.AchievementEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.AchievementSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface AchievementsRepository {
    @SelectProvider(type = AchievementSqlProvider.class, method = "getAll")
    List<Achievement> getAll();

    @SelectProvider(type = AchievementSqlProvider.class, method = "getAchievementRelationshipsByEmployeeId")
    List<AchievementEmployee> getAchievementsByEmployeeId(@Param("employeeId") UUID employeeId);

    @DeleteProvider(type = AchievementSqlProvider.class, method = "deleteAchievementEmployeeRelationshipById")
    void deleteAchievementEmployeeRelationshipById(@Param("employeeId")UUID employeeId, @Param("achievementId") UUID achievementId);

    @InsertProvider(type = AchievementSqlProvider.class, method = "createNewAchievementEmployeeRelationship")
    void createNewAchievementEmployeeRelationship(UUID achievementId, Date issueDate, Date expiringDate, UUID employeeId);
}
