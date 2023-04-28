
CREATE TABLE IF NOT EXISTS achievements(
    id          UUID            NOT NULL CONSTRAINT achievements_pk PRIMARY KEY DEFAULT uuid_generate_v4(),
    achievementName VARCHAR(100) NOT NULL,
    parentId UUID CONSTRAINT achievements_parent_id_fk REFERENCES achievements,
    subItemsAreAchievements BOOLEAN DEFAULT false,
    uniqueAchievementIdentifier VARCHAR(100) DEFAULT 'default'
);

CREATE TABLE IF NOT EXISTS achievements_employees
(
    id              UUID    NOT NULL CONSTRAINT achievements_employees_pk PRIMARY KEY DEFAULT uuid_generate_v4(),
    achievementId       UUID    NOT NULL CONSTRAINT achievements_relationships_id_fk REFERENCES achievements ON DELETE CASCADE,
    issueDate       DATE,
    expiringDate       DATE,
    employeeId      UUID    NOT NULL CONSTRAINT employees_relationships_id_fk REFERENCES employees ON DELETE CASCADE,
    UNIQUE (achievementId, employeeId)
);
