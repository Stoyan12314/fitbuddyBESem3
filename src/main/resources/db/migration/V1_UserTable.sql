CREATE TABLE users
(
    id        int                                NOT NULL AUTO_INCREMENT,
    role      enum('CUSTOMER','TRAINER','ADMINISTRATION') NOT NULL,
    email     varchar(45)                        NOT NULL,
    password  varchar(200)                       NOT NULL,
    firstName varchar(45)                        NOT NULL,
    lastName  varchar(45)                        NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(email)
);