DROP TABLE IF EXISTS categories cascade ;
DROP TABLE IF EXISTS products cascade;

create table if not exists categories
(
    id    bigserial primary key,
    title varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table if not exists products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    count       int,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into categories (title)
values ('Meat'),
       ('Fish'),
       ('Vegetable'),
       ('Fruit');

insert into products(title, price, count, category_id)
values ('Peach', 1, 10, 4),
       ('Orange', 2, 10, 4),
       ('Cucumber', 4, 10, 3),
       ('Tomato', 3, 10, 3),
       ('Pear', 6, 10, 4),
       ('Beef', 2, 10, 1),
       ('Ham', 4, 10, 1),
       ('Cod', 3, 10, 2),
       ('Salmon', 6, 10, 2),
       ('Broccoli', 2, 10, 3),
       ('Carrot', 4, 10, 3),
       ('Garlic', 3, 10, 3),
       ('Onion', 6, 10, 3),
       ('Pea', 2, 10, 3),
       ('Apple', 4, 10, 4),
       ('Banana', 3, 10, 4),
       ('Lemon', 6, 10, 4),
       ('Spinach', 4, 10, 3),
       ('Pike', 3, 10, 2),
       ('Turkey', 6, 10, 1),
       ('Chicken', 10, 10, 1);