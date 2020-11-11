DELETE FROM users;



ALTER SEQUENCE global_seq RESTART WITH 10000;
ALTER SEQUENCE register_seq RESTART WITH 1000;

INSERT INTO users (phone, password, avatar, firstname, lastname, middlename, email, enabled, created_on, created_by, changed_on, changed_by)
VALUES ("8(911)111-11-11", "test_pass", "ne znayu", "Иван", "Иванов", "Ивановоич", "ivan@ivan.ru",1, "2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","CTAC")
VALUES ("8(911)111-11-12", "test_pass1", "ne znayu", "Сергей", "Иванов", "Ивановоич", "serg@ivan.ru",1, "2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","CTAC")
VALUES ("8(911)111-11-13", "test_pass1", "ne znayu", "Евлампий", "Блябляс", "Цырендоржиевич", "qwe@ivan.ru",1, "2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","2020-11-11 20:00:00.000","CTAC")

