DROP TABLE IF EXISTS schools CASCADE ;
DROP TABLE IF EXISTS school_employees CASCADE ;
DROP TABLE IF EXISTS school_users;
DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS employee_roles;
DROP TABLE IF EXISTS employees CASCADE ;
DROP TABLE IF EXISTS companys;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;
DROP SEQUENCE IF EXISTS REGISTER_SEQ;

CREATE SEQUENCE GLOBAL_SEQ      AS INTEGER START WITH 10000;
CREATE SEQUENCE REGISTER_SEQ    AS BIGINT START WITH 1000;

CREATE TABLE companys
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    name        VARCHAR(120)                    NOT NULL,
    country     VARCHAR(3)      DEFAULT 'RUS',
    city        VARCHAR(30)                     NULL,
    street      VARCHAR(120)                    NULL,
    home        VARCHAR(10)                     NULL,
    postal_code VARCHAR(10)                     NULL,
    phone       VARCHAR(20)                     NOT NULL,
    email       VARCHAR(30)                     NULL,
    enabled     BOOLEAN         DEFAULT TRUE    NOT NULL,
    created_on  TIMESTAMP       DEFAULT now()   NOT NULL,

    CONSTRAINT company_idx UNIQUE (name)
);

CREATE TABLE employees
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    company_id  INTEGER                         NULL,
    phone       VARCHAR(20)                     NOT NULL,
    password    VARCHAR                         NOT NULL,
    avatar      VARCHAR                         NULL,
    firstname   VARCHAR(30)                     NOT NULL,
    lastname    VARCHAR(30)                     NOT NULL,
    middlename  VARCHAR(30)                     NULL,
    email       VARCHAR(30)                     NULL,
    enabled     BOOLEAN         DEFAULT TRUE    NOT NULL,
    score       INTEGER         DEFAULT 5       NOT NULL,
    created_on  TIMESTAMP       DEFAULT now()   NOT NULL,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (company_id) REFERENCES companys (id) ON DELETE CASCADE,
--     FOREIGN KEY (created_by) REFERENCES employees (id),
--     FOREIGN KEY (changed_by) REFERENCES employees (id),

    CONSTRAINT employee_phone_idx UNIQUE (company_id, phone)
);

CREATE TABLE employee_roles
(
    employee_id BIGINT                          NOT NULL,
    role        VARCHAR                         NOT NULL,

    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,

    CONSTRAINT employee_roles_idx UNIQUE (employee_id, role)
);

CREATE TABLE schools
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    company_id  INTEGER                         NOT NULL,
    name        VARCHAR(120)                    NOT NULL,
    city        VARCHAR(30)                     NULL,
    street      VARCHAR(120)                    NULL,
    home        VARCHAR(30)                     NULL,
    postal_code VARCHAR(10)                     NULL,
    phone       VARCHAR(20)                     NULL,
    email       VARCHAR(30)                     NULL,
    enabled     BOOLEAN         DEFAULT TRUE    NOT NULL,
    created_on  TIMESTAMP       DEFAULT now()   NOT NULL,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (company_id) REFERENCES companys (id) ON DELETE CASCADE,
--     FOREIGN KEY (created_by) REFERENCES employees (id),
--     FOREIGN KEY (changed_by) REFERENCES employees (id),

    CONSTRAINT school_idx UNIQUE (company_id, name)
);

CREATE TABLE school_employees
(
    school_id   INTEGER                         NOT NULL,
    employee_id BIGINT                          NOT NULL,
    enable      BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (school_id) REFERENCES schools (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,

    CONSTRAINT school_employee_idx UNIQUE (school_id, employee_id)
);

CREATE TABLE users
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    phone       VARCHAR(20)                     NOT NULL,
    password    VARCHAR                         NOT NULL,
    avatar      VARCHAR                         NULL,
    firstname   VARCHAR(30)                     NOT NULL,
    lastname    VARCHAR(30)                     NOT NULL,
    middlename  VARCHAR(30)                     NULL,
    email       VARCHAR(30)                     NULL,
    enabled     BOOLEAN         DEFAULT TRUE    NOT NULL,
    created_on  TIMESTAMP       DEFAULT now()   NOT NULL,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES employees (id),
    FOREIGN KEY (changed_by) REFERENCES employees (id),

    CONSTRAINT user_idx UNIQUE (phone)
);

CREATE TABLE school_users
(
    school_id   INTEGER                         NOT NULL,
    user_id     BIGINT                          NOT NULL,
    enable      BOOLEAN DEFAULT TRUE,
    start_date  date                            NULL ,
    end_date    date                            NULL ,
    contract    VARCHAR(30),

    FOREIGN KEY (school_id) REFERENCES schools (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)   REFERENCES users (id)   ON DELETE CASCADE,

    CONSTRAINT school_user_idx UNIQUE (school_id, user_id)
)