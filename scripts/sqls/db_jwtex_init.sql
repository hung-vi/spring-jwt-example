CREATE SEQUENCE user_sequence;

CREATE TABLE "user"
(
	id         BIGINT NOT NULL DEFAULT NEXTVAL('user_sequence'),
	email      VARCHAR(128) NOT NULL UNIQUE,
	password   VARCHAR(128) NOT NULL,
	first_name VARCHAR(32)  NOT NULL,
	last_name  VARCHAR(32)  NOT NULL
);
ALTER TABLE "user" ADD CONSTRAINT user_pkey PRIMARY KEY (id);

CREATE TABLE "authority"
(
	id         BIGINT NOT NULL,
	authority  VARCHAR(32)
);
ALTER TABLE "authority" ADD CONSTRAINT authority_pkey PRIMARY KEY (id);
ALTER TABLE "authority" ADD CONSTRAINT authority_key UNIQUE (authority);

CREATE TABLE "user_authority"
(
	user_id        BIGINT NOT NULL,
	authority_id    BIGINT NOT NULL
);

ALTER TABLE "user_authority" ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "user_authority" ADD CONSTRAINT authority_id_fkey FOREIGN KEY (authority_id) REFERENCES authority(id) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO authority(id, authority) VALUES (1, 'USER');
INSERT INTO authority(id, authority) VALUES (2, 'ADMIN');
