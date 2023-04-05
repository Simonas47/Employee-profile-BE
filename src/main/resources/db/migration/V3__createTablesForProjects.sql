CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS projects
(
	id          UUID            NOT NULL CONSTRAINT projects_pk PRIMARY KEY DEFAULT uuid_generate_v4(),
	title       VARCHAR(50)    NOT NULL,
	startDate       DATE    NOT NULL,
	endDate       DATE,
	description       TEXT    NOT NULL
);

CREATE TABLE IF NOT EXISTS projects_employees
(
    id              UUID    NOT NULL CONSTRAINT projects_employees_pk PRIMARY KEY DEFAULT uuid_generate_v4(),
	projectId       UUID    NOT NULL CONSTRAINT projects_relationships_id_fk REFERENCES projects ON DELETE CASCADE,
	employeeId      UUID    NOT NULL CONSTRAINT employees_relationships_id_fk REFERENCES employees ON DELETE CASCADE
);

