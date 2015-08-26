CREATE TABLE response (
id VARCHAR(43) NOT NULL,
user_id INTEGER NOT NULL,
question_id VARCHAR(43) NOT NULL,
answer_id VARCHAR(43) NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(email),
FOREIGN KEY (question_id) REFERENCES user(id),
FOREIGN KEY (answer_id) REFERENCES user(id)
);