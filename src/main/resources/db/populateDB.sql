DELETE
FROM roles;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM history_votes;
DELETE
FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


INSERT INTO restaurants (name)
VALUES ('Перчини Пицца & Паста'), --100004
       ('Бар & Гриль МясО'), --100005
       ('Три оленя'); --100006

INSERT INTO meals(description, price, restaurant_id)
VALUES ('Медальоны из говядины', '140.25', '100004'),
       ('Язык по милански', '170.99', '100004'),
       ('Стейк Перчини', '170.99', '100004'),
       ('Стейк Флан', '349.00', '100005'),
       ('Филе Миньон', '429.00', '100005'),
       ('Стейк Мачете', '325.60', '100005'),
       ('Фирменный стейк «Три оленя»', '253.20', '100006'),
       ('Рибай стейк из мраморной говядины Black Angus', '356.12', '100006'),
       ('Медальоны из телячьей вырезки с бефстроганов под сморчками на картофельном пюре', '394.53', '100006');




