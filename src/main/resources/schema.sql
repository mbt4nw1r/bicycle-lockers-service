CREATE TABLE bicycle_lockers
(
    id          VARCHAR(255) NOT NULL,
    readable_id VARCHAR(255) NOT NULL,
    status      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

