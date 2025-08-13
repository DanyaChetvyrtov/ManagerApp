CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE SCHEMA IF NOT EXISTS catalogue;

CREATE TABLE catalogue.t_product
(
    id        uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    c_title   varchar(50) NOT NULL CHECK (length(trim(c_title)) >= 3),
    c_details varchar(1000)
);