DROP TABLE employees CASCADE;
DROP TABLE titles;
DROP TABLE images;
DROP TABLE projects CASCADE;
DROP TABLE users;
DROP TABLE skills_employees;
DROP TABLE skills CASCADE;
DROP TABLE employment_dates;
DROP TABLE projects_employees;


CREATE TABLE IF NOT EXISTS titles
(
	id          INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT title_pk PRIMARY KEY,
	title       VARCHAR(200)    NOT NULL
);

CREATE TABLE IF NOT EXISTS images(
	id              INTEGER      GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT images_pk PRIMARY KEY,
	name            VARCHAR(100)                            NOT NULL,
	type            VARCHAR(100)                            NOT NULL,
	bytes           TEXT                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS employees(
	id             INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT employees_pk PRIMARY KEY,
	name           VARCHAR(100)                            NOT NULL,
	surname        VARCHAR(100)                            NOT NULL,
	middleName     VARCHAR(100),
	imageId        INTEGER                                    NOT NULL CONSTRAINT employees_image_id_fk REFERENCES images,
	titleId        INTEGER                                    NOT NULL CONSTRAINT employees_title_id_fk REFERENCES titles,
	status         employee_status NOT NULL,
	isManager      BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS projects
(
	id              INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT projects_pk PRIMARY KEY,
	title           VARCHAR(50)    NOT NULL,
	startDate       DATE    NOT NULL,
	endDate         DATE,
	description     TEXT    NOT NULL,
	deleted         BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS projects_employees
(
    id                          INTEGER    GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT projects_employees_pk PRIMARY KEY,
	projectId                   INTEGER    NOT NULL CONSTRAINT projects_relationships_id_fk REFERENCES projects ON DELETE CASCADE,
	employeeId                  INTEGER    NOT NULL CONSTRAINT employees_relationships_id_fk REFERENCES employees ON DELETE CASCADE,
	projectEmployeeStatus       team_member_status NOT NULL,
	projectEmployeeStartDate    DATE       NOT NULL DEFAULT CURRENT_DATE,
    projectEmployeeEndDate      DATE
);

CREATE TABLE IF NOT EXISTS users(
	id               INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT users_pk PRIMARY KEY,
	email            VARCHAR(255)                            NOT NULL,
	password         VARCHAR(255)                            NOT NULL
);

CREATE TABLE IF NOT EXISTS skills(
    id                      INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT skills_pk PRIMARY KEY,
    skillName               VARCHAR(100) NOT NULL,
    parentId                INTEGER CONSTRAINT skills_parent_id_fk REFERENCES skills,
    subItemsAreSkills       BOOLEAN DEFAULT false,
    uniqueSkillIdentifier   VARCHAR(100) DEFAULT 'default',
    isLanguage              BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS skills_employees
(
    id          INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT skills_employees_pk PRIMARY KEY,
	skillId     INTEGER     NOT NULL CONSTRAINT skills_relationships_id_fk REFERENCES skills ON DELETE CASCADE,
	skillLevel  SkillLevels NOT NULL,
	employeeId  INTEGER     NOT NULL CONSTRAINT employees_relationships_id_fk REFERENCES employees ON DELETE CASCADE,
	UNIQUE (skillId, employeeId)
);

CREATE TABLE IF NOT EXISTS employment_dates(
	id              INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT employment_dates_pk PRIMARY KEY,
	employeeId      INTEGER     CONSTRAINT employment_dates_employee_id_fk REFERENCES employees,
	hiringDate      DATE        NOT NULL,
	exitDate        DATE
);



