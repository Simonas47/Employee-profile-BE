package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.model.SkillEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.SkillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SkillsRepository {
    @SelectProvider(type = SkillSqlProvider.class, method = "getAll")
    List<Skill> getAll();

    @SelectProvider(type = SkillSqlProvider.class, method = "getSkillRelationshipsByEmployeeId")
    List<SkillEmployee> getSkillsByEmployeeId(@Param("employeeId") int employeeId);

    @DeleteProvider(type = SkillSqlProvider.class, method = "deleteSkillEmployeeRelationshipById")
    void deleteSkillEmployeeRelationshipById(@Param("employeeId") int employeeId, @Param("skillId") int skillId);

    @InsertProvider(type = SkillSqlProvider.class, method = "createNewSkillEmployeeRelationship")
    void createNewSkillEmployeeRelationship(int skillId, String skillLevel, int employeeId);
}
