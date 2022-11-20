
\c webapp

DROP TABLE IF EXISTS objects;
DROP TABLE IF EXISTS chunks;

CREATE TABLE chunks (
    c_id INTEGER PRIMARY KEY,
    environment TEXT,
    x INTEGER,
    y INTEGER
);

CREATE TABLE objects (
    o_id INTEGER PRIMARY KEY,
    c_id INTEGER,
    x FLOAT,
    y FLOAT,
    FOREIGN KEY(c_id)
    REFERENCES chunks(c_id)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO webapp;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO webapp;
