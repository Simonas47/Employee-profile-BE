CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS titles
(
	id          UUID         DEFAULT uuid_generate_v4()   NOT NULL CONSTRAINT title_pk PRIMARY KEY,
	title       VARCHAR(200)    NOT NULL
);

CREATE TABLE IF NOT EXISTS images(
	id              UUID          NOT NULL CONSTRAINT images_pk PRIMARY KEY,
	name            VARCHAR(100)                            NOT NULL,
	type            VARCHAR(100)                            NOT NULL,
	bytes           BYTEA                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS employees(
	id              UUID         NOT NULL CONSTRAINT employees_pk PRIMARY KEY,
	name            VARCHAR(100)                            NOT NULL,
	surname         VARCHAR(100)                            NOT NULL,
	middle_name     VARCHAR(100),
	image_id        UUID                                    NOT NULL CONSTRAINT employees_image_id_fk REFERENCES images,
	hiring_date     DATE,
	exit_date       DATE,
	title_id        UUID                                    NOT NULL CONSTRAINT employees_title_id_fk REFERENCES titles
);

INSERT INTO titles (title) VALUES ('Test Engineer');
INSERT INTO titles (title) VALUES ('Software Engineer');
INSERT INTO titles (title) VALUES ('Senior Software Engineer');
INSERT INTO titles (title) VALUES ('Senior DevOps Engineer');
INSERT INTO titles (title) VALUES ('Engineering Team Manager');



