DELETE FROM schools;
DELETE FROM users;
DELETE FROM employee_roles;
DELETE FROM employees;
DELETE FROM companys;

ALTER SEQUENCE global_seq RESTART WITH 10000;
ALTER SEQUENCE register_seq RESTART WITH 1000;

INSERT INTO users (phone, password, avatar, firstname, lastname, middlename, email, enabled, created_on, created_by, changed_on, changed_by)
VALUES ("8(911)111-11-11", "test_pass", "ne znayu", "Иван", "Иванов", "Ивановоич", "ivan@ivan.ru",1, "2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","CTAC")
VALUES ("8(911)111-11-12", "test_pass1", "ne znayu", "Сергей", "Иванов", "Ивановоич", "serg@ivan.ru",1, "2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","CTAC")
VALUES ("8(911)111-11-13", "test_pass1", "ne znayu", "Евлампий", "Блябляс", "Цырендоржиевич", "qwe@ivan.ru",1, "2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","CTAC")

INSERT INTO companys (name, city, street, home, postal_code, phone)
VALUES ('ООО "Рога и копыта"', 'Москва', 'ул.Ленина', 'д.1', '111100', '+7(911)111-11-11'),
       ('ООО "Копыта и рога"', 'Москва', 'ул.Ленина', 'д.2', '111100', '+7(911)111-11-12');

INSERT INTO employees (company_id, phone, password, avatar, firstname, lastname, middlename, email, created_by)
VALUES (10000, '+7(911)111-11-11', '12345', null, 'Иванов', 'Иван', 'Иванович', 'test1@test.ru', null),
       (10000, '+7(911)111-11-12', '123456', null, 'Иванов', 'Антон', 'Иванович', 'test2@test.ru', 1000);

INSERT INTO employee_roles (employee_id, role)
VALUES (1000, 'ADMIN'),
       (1001, 'MANAGER');


