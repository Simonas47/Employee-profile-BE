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
    @SelectProvider(type = SkillSqlProvider.class, method = "getAll")
    List<Skill> getAll();

    @SelectProvider(type = SkillSqlProvider.class, method = "getSkillRelationshipsByEmployeeId")
    List<SkillEmployee> getSkillsByEmployeeId(@Param("employeeId") Integer employeeId);

    @DeleteProvider(type = SkillSqlProvider.class, method = "deleteSkillEmployeeRelationshipById")
    void deleteSkillEmployeeRelationshipById(@Param("employeeId") Integer employeeId, @Param("skillId") Integer skillId);

    @InsertProvider(type = SkillSqlProvider.class, method = "createNewSkillEmployeeRelationship")
    void createNewSkillEmployeeRelationship(Integer skillId, String skillLevel, Integer employeeId);

    @SelectProvider(type = SkillSqlProvider.class, method = "getBottomCategories")
    List<Skill> getBottomCategories();

    @SelectProvider(type = SkillSqlProvider.class, method = "getBottomSkills")
    List<Skill> getBottomSkills(@Param("parentId") UUID parentId);

    @SelectProvider(type = SkillSqlProvider.class, method = "getTopCategory")
    Skill getTopCategory(@Param("parentId") UUID parentId);
}
