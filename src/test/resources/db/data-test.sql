-- noinspection SqlResolveForFile

DELETE FROM user_vehicles;
DELETE FROM company_vehicles;
DELETE FROM school_vehicles;
DELETE FROM company_users;
DELETE FROM school_users;
DELETE FROM company_users;
DELETE FROM user_roles;
DELETE FROM vehicles  ;
DELETE FROM contracts ;
DELETE FROM schools   ;
DELETE FROM companies ;
DELETE FROM users     ;
DELETE FROM address   ;

ALTER SEQUENCE global_seq RESTART WITH 10000;
ALTER SEQUENCE register_seq RESTART WITH 1000;

INSERT INTO address (postal_code, region, city, street, house, floor, office)
VALUES ('123456', 'Москва', 'Москва', 'ул. Первого Мая', 'д.12', 1, '105'),             -- 10_000
       ('123456', 'Москва', 'Москва', 'ул. Первого Мая', 'д.15', 1, '117'),             -- 10_001
       ('123457', 'Москва', 'Москва', 'ул. Ленина', 'д.15', 3, '305'),                  -- 10_002
       ('123458', 'Москва', 'Москва', 'ул. Нижняя Красносельская', 'д.15', 3, '343'),   -- 10_003
       ('123459', 'Москва', 'Москва', 'ул. Антонова', 'д.1', 2, '283'),                 -- 10_004
       ('123459', 'Москва', 'Москва', 'ул. Миронова', 'д.3', 2, '15');                  -- 10_005

INSERT INTO users (firstname, lastname, middlename, birthdate, email, phone, password, avatar)
VALUES
    -- Админ
    ('Иван', 'Иванов', 'Иванович', '2001-01-01', 'admin1@test.ru', '+7(911) 111-11-11', '12345', 'https://img.icons8.com/bubbles/100/000000/elvis-presley.png'), --1000
    ('Антон', 'Иванов', 'Иванович', '2002-02-01', 'admin2@test.ru', '+7(911) 111-11-12', '123456', null); -- 1001
;

INSERT INTO companies (name, short_name, phone, email, website, address_legal_id, address_actual_id, created_by, created_on)
VALUES
('ООО "Рога и копыта"', 'Рога и копыта', '+7(911)311-11-11', 'roga@mail.ru', 'roga.ru', 10000, 10000, 1000, '2021-06-01'),  --1002
('ООО "Новые рога"', 'Новые рога', '+7(911)311-11-12', 'roga_new@mail.ru', 'newroga.ru', 10001, 10002, 1001, '2021-06-01'); -- 1003

INSERT INTO schools (company_id, name, short_name, phone, email, address_id, created_by, created_on)
VALUES
(1002, 'Автошкола N1 "На Первого мая"', 'Автошкола N1', '8(495)111-111-11', 'avtonumber1@mail.ru', 10000, 1000, '2021-06-01'), --1004
(1003, 'Автошкола N2 "На Антонова"', 'Автошкола N2', '8(495)211-111-11', 'avtonumber2@mail.ru', 10003, 1001, '2021-06-01'),    --1005
(1003, 'Автошкола N1 "На Миронова"', 'Автошкола N1', '8(495)311-111-11', 'avto_mironova@mail.ru', 10004, 1001, '2021-06-01');  --1006

INSERT INTO users (firstname, lastname, middlename, birthdate, email, phone, password, avatar)
VALUES
    -- Менеджер
    ('Петр', 'Петров', 'Иванович', '2001-02-01', 'manager1@test.ru', '+7(911) 111-11-13', '123456', null), --1007
    ('Петр', 'Иванов', 'Иванович', '2001-02-01', 'manager2@test.ru', '+7(911) 111-11-14', '123456', null),
    -- Инструктор
    ('Алексей', 'Петров', 'Иванович', '1989-02-01', 'teacher@test.ru', '+7(911) 111-11-15', '123456', null), --1009
    ('Роман', 'Петров', 'Иванович', '2001-02-01', 'teacher2@test.ru', '+7(911) 111-11-16', '123456', null),
    ('Александр', 'Иванов', 'Иванович', '2001-02-01', 'teacher3@test.ru', '+7(911) 111-11-17', '123456', null);

INSERT INTO user_roles (user_id, school_id, role)
VALUES
    -- school 1004
    (1000, 1004, 'ADMIN'),
    (1007, 1004, 'MANAGER'),
    (1009, 1004, 'INSTRUCTOR'),
    -- school 1005
    (1001, 1005, 'ADMIN'),
    (1008, 1005, 'MANAGER'),
    (1010, 1005, 'INSTRUCTOR'),
    -- school 1006
    (1001, 1006, 'ADMIN'),
    (1008, 1006, 'MANAGER'),
    (1011, 1006, 'INSTRUCTOR');

INSERT INTO contracts (number, start_date, end_date, created_by, created_on)
VALUES
('NN1234', '2021-06-01', '2022-10-01', 1000, '2021-06-01'),
('NN1236', '2021-06-01', '2022-10-01', 1000, '2021-06-01'),
('NN1237', '2021-06-01', '2022-10-01', 1001, '2021-06-01'),
('NN1238', '2021-06-01', '2022-10-01', 1001, '2021-06-01'),
('NN1239', '2021-06-01', '2022-10-01', 1001, '2021-06-01');

INSERT INTO company_users (company_id, user_id, contract_id, status, created_by, created_on)
VALUES
    -- company 1002
    (1002, 1000, null, 'CONTRACTED', 1000, '2021-06-01'),
    (1002, 1007, 10006, 'CONTRACTED', 1000, '2021-06-01'),
    (1002, 1009, 10007, 'CONTRACTED', 1000, '2021-06-01'),
    -- company 1003
    (1003, 1001, null, 'CONTRACTED', 1001, '2021-06-01'),
    (1003, 1008, 10008, 'CONTRACTED', 1001, '2021-06-01'),
    (1003, 1010, 10009, 'CONTRACTED', 1001, '2021-06-01'),
    (1003, 1011, 10010, 'CONTRACTED', 1001, '2021-06-01');


INSERT INTO school_users (school_id, user_id, status, created_by, created_on)
VALUES
    -- school 1004
    (1004, 1000, 'ENABLED', 1000, '2021-06-01'),
    (1004, 1007, 'ENABLED', 1000, '2021-06-01'),
    (1004, 1009, 'ENABLED', 1000, '2021-06-01'),
    -- school 1005
    (1005, 1001, 'ENABLED', 1001, '2021-06-01'),
    (1005, 1008, 'ENABLED', 1001, '2021-06-01'),
    (1005, 1010, 'ENABLED', 1001, '2021-06-01'),
    -- school 1006
    (1006, 1001, 'ENABLED', 1001, '2021-06-01'),
    (1006, 1008, 'ENABLED', 1001, '2021-06-01'),
    (1006, 1011, 'ENABLED', 1001, '2021-06-01');