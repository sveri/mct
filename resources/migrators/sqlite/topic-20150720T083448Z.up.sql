CREATE TABLE topic (
id VARCHAR(43) CONSTRAINT PK_TOPIC PRIMARY KEY NOT NULL,
topic_id VARCHAR(43),
name VARCHAR(255) UNIQUE NOT NULL,
--topic_uuid VARCHAR(43) NOT NULL,
FOREIGN KEY (topic_id) REFERENCES topic(id));

INSERT INTO topic (id, name) VALUES ('e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb', 'Gesundheit');
INSERT INTO topic (id, name, topic_id)
  VALUES ('4a5c29d8-05cf-4799-9959-779965732eed', 'Heilpraktiker', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb');
