DROP TABLE IF EXISTS schools CASCADE ;
DROP TABLE IF EXISTS school_users CASCADE ;
DROP TABLE IF EXISTS school_employees CASCADE ;
DROP TABLE IF EXISTS school_students CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS companys;
DROP TABLE IF EXISTS address;

DROP SEQUENCE IF EXISTS GLOBAL_SEQ;
DROP SEQUENCE IF EXISTS REGISTER_SEQ;

CREATE SEQUENCE GLOBAL_SEQ      AS INTEGER START WITH 10000;
CREATE SEQUENCE REGISTER_SEQ    AS BIGINT START WITH 1000;

CREATE TABLE address
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    country     VARCHAR(3)      DEFAULT 'RUS',
    region      VARCHAR(120)                    NOT NULL ,
    city        VARCHAR(120)                    NOT NULL ,
    zip         VARCHAR(10)                     NULL,
    street      VARCHAR(120)                    NULL,
    building    VARCHAR(10),
    home        VARCHAR(10)                     NULL,
    floor       INTEGER,
    office      INTEGER,
    latitude    VARCHAR(30),
    longitude   VARCHAR(30)
);

CREATE TABLE users
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('REGISTER_SEQ'),
    phone           VARCHAR(20)                     NOT NULL,
    phone_status    BOOLEAN         DEFAULT FALSE   ,
    password        VARCHAR                         NOT NULL,
    avatar          VARCHAR                         NULL,
    firstname       VARCHAR(30)                     NOT NULL,
    lastname        VARCHAR(30)                     NOT NULL,
    middlename      VARCHAR(30)                     NULL,
    email           VARCHAR(30)                     NULL,
    email_status    BOOLEAN         DEFAULT FALSE   ,
    active          BOOLEAN         DEFAULT TRUE    ,
    score           INTEGER                         ,
    created_on      TIMESTAMP       DEFAULT now()   ,
    created_by      BIGINT                          NULL,
    changed_on      TIMESTAMP                       NULL,
    changed_by      BIGINT                          NULL,

    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL ,

    CONSTRAINT user_idx UNIQUE (phone)
);

CREATE TABLE companys
(
    id                  INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    name                VARCHAR(120)                    NOT NULL ,
    short_name          VARCHAR(120)                    ,
    phone               VARCHAR(20)                     NOT NULL ,
    email               VARCHAR(30)                     NULL ,
    website             VARCHAR(120)                    NULL ,
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

CREATE TABLE user_roles
(
    user_id         BIGINT                          NOT NULL,
    role            VARCHAR                         NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,

    CONSTRAINT user_roles_idx UNIQUE (user_id, role)
);

CREATE TABLE schools
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    company_id  INTEGER                         NOT NULL,
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

    FOREIGN KEY (company_id) REFERENCES companys (id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ,
    FOREIGN KEY (changed_by) REFERENCES users (id) ON DELETE SET NULL ,

    CONSTRAINT school_idx UNIQUE (company_id, name)
);

CREATE TABLE school_users
(
    school_id   INTEGER                         NOT NULL,
    user_id     BIGINT                          NOT NULL,
    start_date  DATE                            NULL,
    end_date    DATE                            NULL,
    contract    VARCHAR(120),
    status      VARCHAR(30)                     NOT NULL ,

    FOREIGN KEY (school_id) REFERENCES schools (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)   REFERENCES users (id)   ON DELETE CASCADE,

    CONSTRAINT school_student_idx UNIQUE (school_id, user_id)
)