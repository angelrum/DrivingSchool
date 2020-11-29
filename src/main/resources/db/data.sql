DELETE FROM school_employees;
DELETE FROM schools;
DELETE FROM users;
DELETE FROM companys;

ALTER SEQUENCE global_seq RESTART WITH 10000;
ALTER SEQUENCE register_seq RESTART WITH 1000;

INSERT INTO companys (name, city, street, home, postal_code, phone)
VALUES ('ООО "Рога и копыта"', 'Москва', 'ул.Ленина', 'д.1', '111100', '+7(911)111-11-11'),
       ('ООО "Копыта и рога"', 'Москва', 'ул.Ленина', 'д.2', '111100', '+7(911)111-11-12');

INSERT INTO users (company_id, phone, password, firstname, lastname, middlename, email, score, created_by)
    -- сотрудники компании
VALUES (10000, '+7(911)111-11-11', '12345', 'Иванов', 'Иван', 'Иванович', 'test1@test.ru', 5, null),
       (10000, '+7(911)111-11-12', '123456', 'Иванов', 'Антон', 'Иванович', 'test2@test.ru', 5, 1000),
       (10001, '+7(911)111-11-13', '123456', 'Иванов', 'Сергей', 'Иванович', 'test3@test.ru', 5, null),
       -- ученики
       (null, '+7(911)111-12-11', '12345', 'Иван', 'Петров', 'Иванович', 'test4@test.ru', 0, null),
       (null, '+7(911)111-12-12', '123456', 'Антон', 'Петров', 'Иванович', 'test5@test.ru', 0, null),
       (null, '+7(911)111-12-13', '1234567', 'Сергей', 'Петров', 'Иванович', 'test6@test.ru', 0, null);

INSERT INTO user_roles (user_id, role)
VALUES (1000, 'ADMIN'),
       (1001, 'MANAGER'),
       (1002, 'ADMIN'),
       (1003, 'STUDENT'),
       (1004, 'STUDENT'),
       (1005, 'STUDENT');

INSERT INTO schools (company_id, name, city, street, home, postal_code, phone, email, created_by)
VALUES (10000, 'Школа N1', 'Москва', 'ул.Первого мая', 'д.160, 5 этаж', '111100', '8(495)111-111-11', null, 1000),
       (10000, 'Школа N2', 'Москва', 'ул.Нижняя Красносельская', 'д.40, 5 этаж', '111100', '8(495)111-111-12', null, 1000),
       (10001, 'Школа N21', 'Москва', 'ул.Муравская', 'д.1, 1 этаж', '111100', '8(495)111-111-13', null, 1002);

INSERT INTO school_employees (school_id, user_id)
VALUES (10002, 1000),
       (10002, 1001),
       (10003, 1002);

INSERT INTO school_students(school_id, user_id)
VALUES (10002, 1003),
       (10002, 1004),
       (10002, 1005);