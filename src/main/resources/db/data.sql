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
