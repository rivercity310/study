DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
    userId      VARCHAR(12)     NOT NULL,
    password    VARCHAR(12)     NOT NULL,
    name        VARCHAR(28)     NOT NULL,
    email       VARCHAR(50),
    PRIMARY KEY(userId)
)