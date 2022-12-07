--liquibase formatted sql

--changeset Siarhei:Insert_data

insert into categories (title)
values ('Meat'),
       ('Fish'),
       ('Vegetable'),
       ('Fruit');

insert into products(title, price, count, category_id)
values ('Peach', 1.00, 10, 4),
       ('Orange', 2.00, 10, 4),
       ('Cucumber', 4.00, 10, 3),
       ('Tomato', 3.00, 10, 3),
       ('Pear', 6.00, 10, 4),
       ('Beef', 2.00, 10, 1),
       ('Ham', 4.00, 10, 1),
       ('Cod', 3.00, 10, 2),
       ('Salmon', 6.00, 10, 2),
       ('Broccoli', 2.00, 10, 3),
       ('Carrot', 4.00, 10, 3),
       ('Garlic', 3.00, 10, 3),
       ('Onion', 6.00, 10, 3),
       ('Pea', 2.00, 10, 3),
       ('Apple', 4.00, 10, 4),
       ('Banana', 3.00, 10, 4),
       ('Lemon', 6.00, 10, 4),
       ('Spinach', 4.00, 10, 3),
       ('Pike', 3.00, 10, 2),
       ('Turkey', 6.00, 10, 1),
       ('Chicken', 10.00, 10, 1);