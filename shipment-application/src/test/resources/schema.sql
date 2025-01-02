DROP TABLE IF EXISTS shipments;

CREATE TABLE shipments (
                           id VARCHAR(255) NOT NULL PRIMARY KEY,
                           driver_id VARCHAR(255),
                           status VARCHAR(255)
);
