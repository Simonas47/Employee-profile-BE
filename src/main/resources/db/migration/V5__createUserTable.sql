CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users(
	id              UUID         DEFAULT uuid_generate_v4() NOT NULL CONSTRAINT users_pk PRIMARY KEY ,
	email            VARCHAR(255)                            NOT NULL,
	password         VARCHAR(255)                            NOT NULL
);

INSERT INTO users (email, password) VALUES ('admin@admin.com', 'admin');