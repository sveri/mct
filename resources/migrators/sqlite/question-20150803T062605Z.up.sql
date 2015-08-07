CREATE TABLE question (
id VARCHAR(43) CONSTRAINT PK_QUESTION PRIMARY KEY NOT NULL,
user_email VARCHAR(43) NOT NULL,
topic_id VARCHAR(43) NOT NULL,
question TEXT,
rating INTEGER DEFAULT 0 NOT NULL,
rate_count INTEGER DEFAULT 0 NOT NULL,
--question_uuid VARCHAR(43) NOT NULL,
FOREIGN KEY (user_email) REFERENCES user(email),
FOREIGN KEY (topic_id) REFERENCES topic(id));

INSERT INTO question (id, user_email, topic_id, question)
  VALUES ('e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb', 'local@local.de', '4a5c29d8-05cf-4799-9959-779965732eed', 'question 1');

INSERT INTO question (id, user_email, topic_id, question)
  VALUES ('e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb', 'local@local.de', '4a5c29d8-05cf-4799-9959-779965732eed', 'question 2');

INSERT INTO question (id, user_email, topic_id, question)
  VALUES ('e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba7cb', 'admin@localhost.de', '4a5c29d8-05cf-4799-9959-779965732eed', 'question 3');