DROP TABLE IF EXISTS shipments;

CREATE TABLE shipments (
                           id VARCHAR(255) NOT NULL PRIMARY KEY,
                           driver_id VARCHAR(255),
                           status VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS stored_events;

CREATE TABLE IF NOT EXISTS stored_events (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         event_id BIGINT NOT NULL UNIQUE,
                                         event_type VARCHAR(255) NOT NULL,
                                         event_body TEXT NOT NULL,
                                         occurred_on DATETIME NOT NULL
);
