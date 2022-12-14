-- -- liquibase formatted sql
--
-- changeset Siarhei:Create_tables

create table orders
(
    id          bigserial primary key,
    username    varchar(255)  not null,
    total_price numeric(8, 2) not null,
    address     varchar(255),
    phone       varchar(255),
    status      varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint        not null,
    product_title     varchar(255)  not null,
    count             int           not null,
    price_per_product numeric(8, 2) not null,
    price             numeric(8, 2) not null,
    order_id          bigint        not null references orders (id),
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);
