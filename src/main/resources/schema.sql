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

CREATE TABLE customers_bicycle_lockers
(
    customers_id   VARCHAR(255) NOT NULL,
    bicycle_lockers_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (customers_id, bicycle_lockers_id)
);
