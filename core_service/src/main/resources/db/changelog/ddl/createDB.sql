--liquibase formatted sql

--changeset Siarhei:Create_tables

create table categories
(
    id    bigserial primary key,
    title varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8,2),
    count       int,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
