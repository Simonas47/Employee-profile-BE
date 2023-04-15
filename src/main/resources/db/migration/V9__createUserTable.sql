create TABLE IF NOT EXISTS users(
	id              UUID         DEFAULT uuid_generate_v4() NOT NULL CONSTRAINT users_pk PRIMARY KEY ,
	email            VARCHAR(255)                            NOT NULL,
	password         VARCHAR(255)                            NOT NULL
);

insert into users (email, password) values ('admin@admin.com', 'admin');