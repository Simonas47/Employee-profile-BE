
CREATE TABLE IF NOT EXISTS skills(
    id          UUID            NOT NULL CONSTRAINT skills_pk PRIMARY KEY DEFAULT uuid_generate_v4(),
    skillName VARCHAR(100) NOT NULL,
    parentId UUID CONSTRAINT skills_parent_id_fk REFERENCES skills,
    subItemsAreSkills BOOLEAN DEFAULT false
);

CREATE TYPE SkillLevels AS ENUM ('Basic', 'Intermediate', 'Expert');

CREATE TABLE IF NOT EXISTS skills_employees
(
    id              UUID    NOT NULL CONSTRAINT skills_employees_pk PRIMARY KEY DEFAULT uuid_generate_v4(),
	skillId       UUID    NOT NULL CONSTRAINT skills_relationships_id_fk REFERENCES skills ON DELETE CASCADE,
	skillLevel SkillLevels NOT NULL,
	employeeId      UUID    NOT NULL CONSTRAINT employees_relationships_id_fk REFERENCES employees ON DELETE CASCADE,
	UNIQUE (skillId, employeeId)
);
