DELETE
FROM roles where user_id != 0;
DELETE
FROM users where id != 0;
DELETE
FROM restaurants where id != 0;
DELETE
FROM history_votes where id != 0;
DELETE
FROM meals where id != 0;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


INSERT INTO restaurants (name)
VALUES ('Перчини Пицца & Паста'), --100002
       ('Бар & Гриль МясО'),      --100003
       ('Три оленя'); --100004

INSERT INTO meals(description, price, restaurant_id)
VALUES ('Медальоны из говядины', '140.25', '100002'),
       ('Язык по милански', '170.99', '100002'),
       ('Стейк Перчини', '170.99', '100002'),
       ('Стейк Флан', '349.00', '100003'),
       ('Филе Миньон', '429.00', '100003'),
       ('Стейк Мачете', '325.60', '100003'),
       ('Фирменный стейк «Три оленя»', '253.20', '100004'),
       ('Рибай стейк из мраморной говядины Black Angus', '356.12', '100004'),
       ('Медальоны из телячьей вырезки с бефстроганов под сморчками на картофельном пюре', '394.53', '100004');

INSERT INTO history_votes(user_id, restaurant_id, vote)
VALUES (100000, 100002, 1),
       (100000, 100003, 1),
       (100000, 100004, -1),
       (100001, 100002, -1),
       (100001, 100003, -1),
       (100001, 100004, -1);





