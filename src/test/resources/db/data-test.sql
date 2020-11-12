DELETE FROM schools;
DELETE FROM users;
DELETE FROM employee_roles;
DELETE FROM employees;
DELETE FROM companys;

ALTER SEQUENCE global_seq RESTART WITH 10000;
ALTER SEQUENCE register_seq RESTART WITH 1000;

INSERT INTO companys (name, city, street, home, postal_code, phone)
VALUES ('ООО "Рога и копыта"', 'Москва', 'ул.Ленина', 'д.1', '111100', '+7(911)111-11-11'),
       ('ООО "Копыта и рога"', 'Москва', 'ул.Ленина', 'д.2', '111100', '+7(911)111-11-12');

INSERT INTO employees (company_id, phone, password, avatar, firstname, lastname, middlename, email, score, created_by)
VALUES (10000, '+7(911)111-11-11', '12345', null, 'Иванов', 'Иван', 'Иванович', 'test1@test.ru', 5, null),
       (10000, '+7(911)111-11-12', '123456', null, 'Иванов', 'Антон', 'Иванович', 'test2@test.ru', 5, 1000);

INSERT INTO employee_roles (employee_id, role)
VALUES (1000, 'ADMIN'),
       (1001, 'MANAGER');

INSERT INTO schools (company_id, name, city, street, home, postal_code, phone, email, created_by)
VALUES (10000, 'Школа N1', 'Москва', 'ул.Первого мая', 'д.160, 5 этаж', '111100', '8(495)111-111-11', null, 1000),
       (10000, 'Школа N2', 'Москва', 'ул.Нижняя Красносельская', 'д.40, 5 этаж', '111100', '8(495)111-111-12', null, 1000);

INSERT INTO school_employees (school_id, employee_id)
VALUES (10002, 1000),
       (10003, 1000)
