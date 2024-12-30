CREATE TABLE IF NOT EXISTS stored_events (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  event_id BIGINT NOT NULL UNIQUE,
  event_type VARCHAR(255) NOT NULL,
  event_body TEXT NOT NULL,
  occurred_on DATETIME NOT NULL
);