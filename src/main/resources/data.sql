DELETE FROM ROLES;
DELETE FROM DISHES;
DELETE FROM VOTES;
DELETE FROM MENU;
DELETE FROM USERS;
DELETE FROM ROLE;
DELETE FROM DISH;
DELETE FROM RESTAURANT;

ALTER SEQUENCE ROLES_SEQ RESTART WITH 10000;
ALTER SEQUENCE dishes_seq RESTART WITH 10000;
ALTER SEQUENCE votes_seq RESTART WITH 10000;
ALTER SEQUENCE menu_seq RESTART WITH 10000;
ALTER SEQUENCE users_seq RESTART WITH 10000;
ALTER SEQUENCE role_seq RESTART WITH 10000;
ALTER SEQUENCE dish_seq RESTART WITH 10000;
ALTER SEQUENCE restaurant_seq RESTART WITH 10000;

INSERT INTO ROLE (NAME)
VALUES ('ADMIN'),('USER');

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('User3', 'user3@yandex.ru', '{noop}password'),
       ('User4', 'user4@yandex.ru', '{noop}password'),
       ('User5', 'user5@yandex.ru', '{noop}password'),
       ('User6', 'user6@yandex.ru', '{noop}password'),
       ('User7', 'user7@yandex.ru', '{noop}password'),
       ('User8', 'user8@yandex.ru', '{noop}password'),
       ('User9', 'user9@yandex.ru', '{noop}password'),
       ('User10', 'user10@yandex.ru', '{noop}password'),
       ('User11', 'user11@yandex.ru', '{noop}password'),
       ('User12', 'user12@yandex.ru', '{noop}password'),
       ('User13', 'user13@yandex.ru', '{noop}password'),
       ('User14', 'user14@yandex.ru', '{noop}password'),
       ('User15', 'user15@yandex.ru', '{noop}password'),
       ('User16', 'user16@yandex.ru', '{noop}password'),
       ('User17', 'user17@yandex.ru', '{noop}password'),
       ('User18', 'user18@yandex.ru', '{noop}password'),
       ('User19', 'user19@yandex.ru', '{noop}password'),
       ('User20', 'user20@yandex.ru', '{noop}password');

INSERT INTO DISH (NAME)
VALUES ('Салат'),
       ('Суп'),
       ('Гарнир'),
       ('Котлета'),
       ('Булочка'),
       ('Компот'),
       ('Чай'),
       ('Кофе');

INSERT INTO RESTAURANT (NAME)
VALUES ('Радуга'),
       ('555'),
       ('Юнга'),
       ('Уральские пельмени'),
       ('Суши Hari'),
       ('Матрешка');

INSERT INTO ROLES (USER_ID, ROLE_ID)
VALUES (10000, 10000),       (10000, 10001),
       (10001, 10001),       (10002, 10001),      (10003, 10001),      (10004, 10001),
       (10005, 10001),       (10006, 10001),      (10007, 10001),      (10008, 10001),
       (10009, 10001),       (10010, 10001),      (10011, 10001),      (10012, 10001),
       (10013, 10001),       (10014, 10001),      (10015, 10001),      (10016, 10001),
       (10017, 10001),       (10018, 10001),      (10019, 10001),      (10020, 10001);

INSERT INTO MENU (DATE, RESTAURANT_ID)
VALUES  (TODAY, 10000),
        (TODAY, 10001),
        (TODAY, 10002),
        (TODAY, 10003),
        (TODAY, 10004),
--         (TODAY, 10005),

        (DATE_SUB(TODAY, 1 DAY), 10000),
        (DATE_SUB(TODAY, 1 DAY), 10001),
        (DATE_SUB(TODAY, 1 DAY), 10002),
        (DATE_SUB(TODAY, 1 DAY), 10003),
        (DATE_SUB(TODAY, 1 DAY), 10004),
        (DATE_SUB(TODAY, 1 DAY), 10005),

        (DATE_SUB(TODAY, 2 DAY), 10000),
        (DATE_SUB(TODAY, 2 DAY), 10001),
        (DATE_SUB(TODAY, 2 DAY), 10002),
        (DATE_SUB(TODAY, 2 DAY), 10003),
        (DATE_SUB(TODAY, 2 DAY), 10004),
        (DATE_SUB(TODAY, 2 DAY), 10005);

INSERT INTO DISHES (MENU_ID, DISH_ID, PRICE)
VALUES (10000, 10000, 40),
       (10000, 10001, 100),
       (10000, 10002, 50),
       (10000, 10003, 20),
       (10000, 10004, 20),
       (10000, 10005, 10),

       (10001, 10000, 50),
       (10001, 10001, 100),
       (10001, 10002, 50),
       (10001, 10003, 20),
       (10001, 10004, 20),
       (10001, 10005, 10),

       (10002, 10000, 60),
       (10002, 10001, 100),
       (10002, 10002, 50),
       (10002, 10003, 20),
       (10002, 10004, 20),
       (10002, 10005, 10),

       (10003, 10000, 70),
       (10003, 10001, 100),
       (10003, 10002, 50),
       (10003, 10003, 20),
       (10003, 10004, 20),
       (10003, 10005, 10),

       (10004, 10000, 80),
       (10004, 10001, 100),
       (10004, 10002, 50),
       (10004, 10003, 20),
       (10004, 10004, 20),
       (10004, 10005, 10);

INSERT INTO VOTES (USER_ID, MENU_ID)
VALUES (10001,10000),
       (10002,10000),
       (10003,10000),
       (10004,10000),
       (10005,10000),

       (10006,10001),
       (10007,10001),
       (10008,10001),

       (10009,10002),
       (10010,10002),

       (10001,10006),
       (10002,10006),
       (10003,10006),
       (10004,10006),
       (10005,10006),

       (10006,10007),
       (10007,10007),
       (10008,10007),

       (10009,10008),
       (10010,10008),

       (10001,10012),
       (10002,10012),
       (10003,10012),
       (10004,10012),
       (10005,10012),

       (10006,10013),
       (10007,10013),
       (10008,10013),

       (10009,10014),
       (10010,10014);