package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Achievement;
import com.sourcery.employeeprofile.model.AchievementEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.AchievementSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface AchievementsRepository {
    @SelectProvider(type = AchievementSqlProvider.class, method = "getAll")
    List<Achievement> getAll();

    @SelectProvider(type = AchievementSqlProvider.class, method = "getAchievementRelationshipsByEmployeeId")
    List<AchievementEmployee> getAchievementsByEmployeeId(@Param("employeeId") Integer employeeId);

    @DeleteProvider(type = AchievementSqlProvider.class, method = "deleteAchievementEmployeeRelationshipById")
    void deleteAchievementEmployeeRelationshipById(@Param("employeeId") Integer employeeId,
                                                   @Param("achievementId") Integer achievementId);

    @InsertProvider(type = AchievementSqlProvider.class, method = "createNewAchievementEmployeeRelationship")
    void createNewAchievementEmployeeRelationship(Integer achievementId,
                                                  Date issueDate,
                                                  Date expiringDate,
                                                  Integer employeeId);
}
