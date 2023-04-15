package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.model.SkillEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.SkillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface SkillsRepository {
    @Select("SELECT * FROM skills")
    List<Skill> getAll();

    @SelectProvider(type = SkillSqlProvider.class, method = "getSkillRelationshipsByEmployeeId")
    List<SkillEmployee> getSkillsByEmployeeId(@Param("employeeId") UUID employeeId);

    @DeleteProvider(type = SkillSqlProvider.class, method = "deleteSkillEmployeeRelationshipById")
    void deleteSkillEmployeeRelationshipById(@Param("employeeId")UUID employeeId, @Param("skillId") UUID skillId);

    @InsertProvider(type = SkillSqlProvider.class, method = "createNewSkillEmployeeRelationship")
    void createNewSkillEmployeeRelationship(UUID skillId, String skillLevel, UUID employeeId);
}
