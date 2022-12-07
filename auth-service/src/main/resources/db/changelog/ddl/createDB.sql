--liquibase formatted sql

--changeset Siarhei:Create_tables

create table profiles
(
    id         bigserial primary key,
    first_name varchar(30),
    last_name  varchar(80),
    email      varchar(100),
    updated_at timestamp default current_timestamp
);

create table users
(
    id         bigserial primary key,
    username   varchar(30) unique,
    password   varchar(80) not null,
    profile_id bigint,
    FOREIGN KEY (profile_id) REFERENCES profiles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);


create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);
