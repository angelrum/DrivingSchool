DROP TABLE IF EXISTS user_vehicles;
DROP TABLE IF EXISTS company_vehicles;
DROP TABLE IF EXISTS school_vehicles;
DROP TABLE IF EXISTS company_users;
DROP TABLE IF EXISTS school_users;
DROP TABLE IF EXISTS company_users;
DROP TABLE IF EXISTS user_roles;
-- DROP TABLE IF EXISTS status             CASCADE;
-- DROP TABLE IF EXISTS roles              CASCADE;
DROP TABLE IF EXISTS vehicles           CASCADE;
DROP TABLE IF EXISTS contracts          CASCADE;
DROP TABLE IF EXISTS schools            CASCADE;
DROP TABLE IF EXISTS companies          CASCADE;
DROP TABLE IF EXISTS users              CASCADE;
DROP TABLE IF EXISTS address            CASCADE;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ      CASCADE;
DROP SEQUENCE IF EXISTS REGISTER_SEQ    CASCADE;

CREATE SEQUENCE GLOBAL_SEQ      AS INTEGER START WITH 10000;
CREATE SEQUENCE REGISTER_SEQ    AS BIGINT START WITH 1000;

/*основные таблицы*/
CREATE TABLE address
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    country         VARCHAR(3)      DEFAULT 'RUS',
    postal_code     VARCHAR(10)                     NULL,
    region          VARCHAR(120)                    NOT NULL ,
    city            VARCHAR(120)                    NOT NULL ,
    settlement      VARCHAR(200)                    NULL ,
    street          VARCHAR(120)                    NOT NULL ,
    house           VARCHAR(10)                     NOT NULL,
    floor           INTEGER,
    office          VARCHAR(10),
    latitude        VARCHAR(30),
    longitude       VARCHAR(30),
    qc_geo          smallint      DEFAULT 5
);

CREATE TABLE users
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    firstname       VARCHAR(30)                     NOT NULL,
    lastname        VARCHAR(30)                     NOT NULL,
    middlename      VARCHAR(30)                     NULL,
    birthdate       DATE,
    email           VARCHAR(30)                     NULL,
    email_status    BOOLEAN         DEFAULT FALSE   ,
    phone           VARCHAR(20)                     NOT NULL,
    phone_status    BOOLEAN         DEFAULT FALSE   ,
    password        VARCHAR                         NOT NULL,
    avatar          VARCHAR                         NULL,
    active          BOOLEAN         DEFAULT TRUE    ,
    score           INTEGER         DEFAULT 0       ,
    description     VARCHAR(255)                    ,
    created_on      TIMESTAMP       DEFAULT now()   ,
    created_by      BIGINT                          NULL,
    changed_on      TIMESTAMP                       NULL,
    changed_by      BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL ,

    CONSTRAINT user_idx UNIQUE (phone),
    CONSTRAINT email_idx UNIQUE (email)
);

CREATE TABLE companies
(
    id                  BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    name                VARCHAR(120)                    NOT NULL ,
    short_name          VARCHAR(120)                    ,
    phone               VARCHAR(20)                     NOT NULL ,
    email               VARCHAR(30)                     ,
    website             VARCHAR(120)                    ,
    active              BOOLEAN         DEFAULT TRUE    ,
    address_legal_id    INTEGER                         ,
    address_actual_id   INTEGER                         ,
    created_on          TIMESTAMP       DEFAULT now(),
    created_by          BIGINT                          NULL,
    changed_on          TIMESTAMP                       NULL,
    changed_by          BIGINT                          NULL,

    FOREIGN KEY (created_by)        REFERENCES users (id)   ON DELETE SET NULL ,
    FOREIGN KEY (changed_by)        REFERENCES users (id)   ON DELETE SET NULL ,
    FOREIGN KEY (address_legal_id)  REFERENCES address(id)  ON DELETE SET NULL ,
    FOREIGN KEY (address_actual_id) REFERENCES address(id)  ON DELETE SET NULL ,

    CONSTRAINT company_idx UNIQUE (name)
);

CREATE TABLE schools
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    company_id  BIGINT                          NOT NULL,
    name        VARCHAR(120)                    NOT NULL,
    short_name  VARCHAR(60)                    ,
    phone       VARCHAR(20)                     NULL,
    email       VARCHAR(30)                     NULL,
    address_id  INTEGER                         ,
    active      BOOLEAN         DEFAULT TRUE    ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE SET NULL ,
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL ,

    CONSTRAINT school_idx UNIQUE (company_id, name)
);

CREATE TABLE contracts
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    number      VARCHAR(30)                     ,
    start_date  DATE                            ,
    end_date    DATE                            ,
    parent_id   INTEGER                         ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (parent_id)  REFERENCES contracts (id) ON DELETE CASCADE
);

CREATE TABLE vehicles (
    id              BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    brand           VARCHAR(30)                     ,
    model           VARCHAR(30)                     ,
    type            VARCHAR(10)                     NOT NULL ,
    transmission    VARCHAR(30)                     NOT NULL ,
    year            SMALLINT                        ,
    mileage         INTEGER                         ,
    reg_number      VARCHAR(10)                     ,
    issue_date      DATE                            ,
    owner           VARCHAR(20)                     ,
    active          BOOLEAN         DEFAULT TRUE    ,
    condition       VARCHAR(20)                     ,
    created_on      TIMESTAMP       DEFAULT now()   ,
    created_by      BIGINT                          NULL,
    changed_on      TIMESTAMP                       NULL,
    changed_by      BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL ,

    CONSTRAINT vehicle_idx UNIQUE (reg_number)
);

/*вспомогательные таблицы*/
-- CREATE TABLE roles (
--     role_id         INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
--     role            VARCHAR(30)                     NOT NULL,
--
--     CONSTRAINT role_idx UNIQUE (role)
-- );
--
-- CREATE TABLE status (
--     id              INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
--     status          VARCHAR(20)                     NOT NULL ,
--
--     CONSTRAINT status_idx UNIQUE (status)
-- );

/*таблицы связей*/
CREATE TABLE user_roles (
    user_id         BIGINT                          NOT NULL ,
    school_id       BIGINT                          NOT NULL,
    role            VARCHAR(30)                     NOT NULL,

    FOREIGN KEY (school_id)  REFERENCES schools (id)    ON DELETE CASCADE,
    FOREIGN KEY (user_id)    REFERENCES users (id)      ON DELETE CASCADE
);

CREATE TABLE company_users
(
    company_id  BIGINT                          NOT NULL,
    user_id     BIGINT                          NOT NULL,
    contract_id INTEGER                         NULL,
--     status_id   INTEGER                         NOT NULL,
    status          VARCHAR(20)                     NOT NULL ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (created_by)    REFERENCES users (id)      ON DELETE SET NULL ,
    FOREIGN KEY (changed_by)    REFERENCES users (id)      ON DELETE SET NULL,
    FOREIGN KEY (contract_id)   REFERENCES contracts(id)   ON DELETE SET NULL ,
    FOREIGN KEY (user_id)       REFERENCES users (id)      ON DELETE CASCADE,
    FOREIGN KEY (company_id)    REFERENCES companies (id)  ON DELETE CASCADE,
--     FOREIGN KEY (status_id)  REFERENCES status (id)     ON DELETE CASCADE,

    CONSTRAINT company_users_idx UNIQUE (company_id, user_id)
);

CREATE TABLE school_users
(
    school_id   BIGINT                          NOT NULL,
    user_id     BIGINT                          NOT NULL,
--     status_id   INTEGER                         NOT NULL,
    status      VARCHAR(20)                     NOT NULL ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id)   ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id)   ON DELETE SET NULL,
    FOREIGN KEY (school_id)  REFERENCES schools (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)    REFERENCES users (id)   ON DELETE CASCADE,
--     FOREIGN KEY (status_id)  REFERENCES status(id)   ON DELETE CASCADE,

    CONSTRAINT school_student_idx UNIQUE (school_id, user_id)
);


CREATE TABLE school_vehicles
(
    school_id   BIGINT                          NOT NULL ,
    vehicle_id  BIGINT                          NOT NULL ,
--     status_id   INTEGER                         ,
    status      VARCHAR(20)                     NOT NULL ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (school_id)  REFERENCES schools (id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id) ON DELETE CASCADE ,
--     FOREIGN KEY (status_id)  REFERENCES status(id) ON DELETE CASCADE,

    CONSTRAINT school_vehicles_idx UNIQUE (school_id, vehicle_id)
);

CREATE TABLE company_vehicles
(
    company_id  BIGINT                          NOT NULL ,
    vehicle_id  BIGINT                          NOT NULL ,
--     status_id   INTEGER                         ,
    status      VARCHAR(20)                     NOT NULL ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id) ON DELETE CASCADE ,
--     FOREIGN KEY (status_id)  REFERENCES status(id) ON DELETE CASCADE,
    
    CONSTRAINT company_vehicles_idx UNIQUE (company_id, vehicle_id)
);

CREATE TABLE user_vehicles 
(
    user_id     BIGINT                          NOT NULL ,
    vehicle_id  BIGINT                          NOT NULL ,
--     status_id   INTEGER                         ,
    status      VARCHAR(20)                     NOT NULL ,
    created_on  TIMESTAMP       DEFAULT now()   ,
    created_by  BIGINT                          NULL,
    changed_on  TIMESTAMP                       NULL,
    changed_by  BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id) ON DELETE CASCADE ,
--     FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE,
    
    CONSTRAINT user_vehicles_idx UNIQUE (user_id, vehicle_id)
);