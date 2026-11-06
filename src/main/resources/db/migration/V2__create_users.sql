CREATE TABLE users
(
    login    VARCHAR(16)  UNIQUE NOT NULL,
    password VARCHAR(16)  NOT NULL,
    name     VARCHAR(255) NOT NULL,
    id       INTEGER      PRIMARY KEY
);