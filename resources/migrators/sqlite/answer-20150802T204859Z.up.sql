CREATE TABLE answer (
id VARCHAR(43) CONSTRAINT PK_ANSWER PRIMARY KEY NOT NULL,
question_id VARCHAR(43) NOT NULL,
correct BOOLEAN NOT NULL,
answer TEXT NOT NULL,
--answer_uuid VARCHAR(43) NOT NULL,
FOREIGN KEY (question_id) REFERENCES question(id));

INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d3cf-4ce2-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb', 1, 'q1 answer 1');
INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d4cf-4ce2-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb', 1, 'q1 answer 2');
INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d5cf-4ce2-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb', 0, 'q1 answer 3');
INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d6cf-4ce2-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb', 1, 'q1 answer 4');

INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d6cf-4ce3-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb', 1, 'q2 answer 1');
INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d6cf-4ce4-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb', 1, 'q2 answer 2');
INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d6cf-4ce5-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb', 0, 'q2 answer 3');
INSERT INTO answer (id, question_id, correct, answer)
  VALUES ('e4c7cfc0-d6cf-4ce6-b0a8-f7fb10bba5cb', 'e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb', 0, 'q2 answer 4');