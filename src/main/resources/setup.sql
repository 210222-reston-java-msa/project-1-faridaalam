-- ers.users definition

-- Drop table

-- DROP TABLE ers.users;

CREATE TABLE ers.users (
	pass varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	"role" int4 NOT NULL,
	firstname varchar(50) NOT NULL,
	lastname varchar(50) NOT NULL,
	user_id int4 NOT NULL DEFAULT nextval('ers.users_id_seq'::regclass),
	CONSTRAINT users_pk PRIMARY KEY (user_id),
	CONSTRAINT users_un UNIQUE (email)
);


-- ers.expense definition

-- Drop table

-- DROP TABLE ers.expense;

CREATE TABLE ers.expense (
	id serial NOT NULL,
	"date" timestamp(0) NULL,
	user_comment varchar(100) NOT NULL DEFAULT 'No comment'::character varying,
	image bytea NULL,
	"type" int4 NOT NULL,
	status int4 NOT NULL,
	user_id int4 NOT NULL,
	amount float8 NOT NULL DEFAULT 0.0,
	imgadded bool NOT NULL DEFAULT false,
	mgr_id int4 NULL,
	CONSTRAINT expense_pk PRIMARY KEY (id)
);


-- ers.expense foreign keys

ALTER TABLE ers.expense ADD CONSTRAINT expense_fk FOREIGN KEY (user_id) REFERENCES ers.users(user_id);
ALTER TABLE ers.expense ADD CONSTRAINT expense_fk_1 FOREIGN KEY (mgr_id) REFERENCES ers.users(user_id);
