DELETE FROM school_users;
DELETE FROM schools;
DELETE FROM companys;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM address;

ALTER SEQUENCE global_seq RESTART WITH 10000;
ALTER SEQUENCE register_seq RESTART WITH 1000;

INSERT INTO address (country, region, city, zip, street, building, home, floor, office)
VALUES ('RUS','Москва', 'Москва', '123456', 'ул. Первого Мая', 'стр.1', 'д.12', 1, 105),
       ('RUS','Москва', 'Москва', '123456', 'ул. Первого Мая', 'стр.1', 'д.15', 1, 117),
       ('RUS','Москва', 'Москва', '123457', 'ул. Ленина', null, 'д.12', 3, 305),
       ('RUS','Москва', 'Москва', '123458', 'ул. Нижняя Красносельская', null, 'д.15', 3, 343),
       ('RUS','Москва', 'Москва', '123459', 'ул. Антонова', 'корп.8', 'д.1', 2, 283),
       ('RUS','Москва', 'Москва', '123459', 'ул. Миронова', null, 'д.3', 2, 15);

INSERT INTO users (avatar, phone, phone_status, password, firstname, lastname, middlename, email, email_status, active, score, created_by)
    -- сотрудники компании
VALUES ('https://img.icons8.com/bubbles/100/000000/elvis-presley.png', '+7(911) 111-11-11', true, '12345', 'Иван', 'Иванов', 'Иванович', 'admin1@test.ru', true, true, 0, null), -- админ ООО "Рога и копыта"
       (null, '+7(911) 111-11-12', true, '123456', 'Антон', 'Иванов', 'Иванович', 'admin2@test.ru', true, true, 0, null), -- админ ООО "Новые рога"
       -- учитель в ООО "Рога и копыта"
       (null, '+7(911) 111-11-13', true, '123457', 'Сергей', 'Петров', 'Иванович', 'teacher1@test.ru', true, true, 5, 1000),
       (null, '+7(911) 111-11-14', true, '123458', 'Петр', 'Петров', 'Иванович', 'teacher2@test.ru', true, true, 5, 1000),
       -- учитель в ООО "Новые рога"
       (null, '+7(911) 111-11-15', true, '123459', 'Виктор', 'Иванов', 'Иванович', 'teacher1@test1.ru', true, true, 5, 1001),
       -- ученики в ООО "Рога и копыта"
       (null, '+7(911) 211-11-11', true, '12345', 'Артем', 'Антонов', 'Иванович', 'student1@test1.ru', true, true, 5, 1000),
       (null, '+7(911) 211-11-12', true, '123456', 'Иван', 'Антонов', 'Иванович', 'student2@test1.ru', true, true, 5, 1000),
       (null, '+7(911) 211-11-13', true, '1234567', 'Иван', 'Антонов', 'Иванович', 'student3@test1.ru', true, true, 5, 1000),
       -- ученики в ООО "Новые рога"
       (null, '+7(911) 211-11-14', true, '12345678', 'Сергей', 'Антонов', 'Иванович', 'student4@test1.ru', true, true, 5, 1001);


INSERT INTO companys (name, short_name, phone, email, website, address_legal_id, address_actual_id, created_by)
VALUES ('ООО "Рога и копыта"', 'Рога и копыта', '+7(911)311-11-11', 'roga@mail.ru', 'roga.ru', 10000, 10001, 1000),
       ('ООО "Новые рога"', 'Новые рога', '+7(911)311-11-12', 'roga_new@mail.ru', 'newroga.ru', 10002, 10002, 1001);

INSERT INTO user_roles (user_id, role)
VALUES (1000, 'ADMIN'),
       (1001, 'ADMIN'),
       (1002, 'TEACHER'),
       (1003, 'TEACHER'),
       (1004, 'TEACHER'),
       (1005, 'STUDENT'),
       (1006, 'STUDENT'),
       (1007, 'STUDENT'),
       (1008, 'STUDENT');

INSERT INTO schools(company_id, name, short_name, phone, email, address_id, created_by)
VALUES (10006, 'Автошкола N1 "На Первого мая', 'Автошкола N1', '8(495)111-111-11', 'avtonumber1@mail.ru', 10003, 1000),
       (10006, 'Автошкола N2 "На Антонова', 'Автошкола N2', '8(495)211-111-11', 'avtonumber2@mail.ru', 10004, 1000),
       (10007, 'Автошкола N1 "На Миронова', 'Автошкола N1', '8(495)311-111-11', 'avto_mironova@mail.ru', 10005, 1001);

INSERT INTO school_users(school_id, user_id, start_date, end_date, contract, status)
VALUES (10008, 1000, null, null, null, 'ENABLED'),
       (10008, 1002, null, null, null, 'ENABLED'),
       (10008, 1003, null, null, null, 'ENABLED'),
       (10008, 1005, null, null, null, 'ENABLED'),
       (10008, 1006, null, null, null, 'ENABLED'),
       (10008, 1007, null, null, null, 'PENDING'),
       (10009, 1000, null, null, null, 'ENABLED'),
       (10010, 1001, null, null, null, 'ENABLED'),
       (10010, 1004, null, null, null, 'ENABLED'),
       (10010, 1008, null, null, null, 'ENABLED');