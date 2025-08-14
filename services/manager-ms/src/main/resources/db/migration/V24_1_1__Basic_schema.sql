CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE SCHEMA IF NOT EXISTS user_management;

CREATE TABLE user_management.t_user
(
    id         uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    c_username varchar(50) NOT NULL CHECK (length(trim(c_username)) > 0) UNIQUE,
    c_password varchar
);

CREATE TABLE user_management.authority
(
    id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    c_authority varchar NOT NULL CHECK (length(trim(c_authority)) > 0) UNIQUE
);

CREATE TABLE user_management.t_user_authority
(
    id           serial PRIMARY KEY,
    id_user      uuid NOT NULL REFERENCES user_management.t_user (id),
    id_authority uuid NOT NULL REFERENCES user_management.authority (id),
    CONSTRAINT uk_user_authority UNIQUE (id_user, id_authority)
);