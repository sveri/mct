CREATE TABLE users_questions (
users_is );

INSERT INTO user ("first_name", "last_name", "email", "role", "is_active", "pass", "uuid") VALUES
('admin', 'admin', 'admin@localhost.de', 'admin', 1,
'bcrypt+sha512$d6d175aaa9c525174d817a74$12$24326124313224314d345444356149457a67516150447967517a67472e717a2e777047565a7071495330625441704f46686a556b5535376849743575',
'b4f18236-2a14-49f6-837e-5e23def53124');

INSERT INTO user ("email", "role", "is_active", "pass", "uuid") VALUES
('local@local.de', 'none', 1,
'bcrypt+sha512$6f0e8b5dfc121df4a723cf12$12$24326124313224374a4d324b484e684d5143475733626d6f5965584f65422f69453569727357466332364f5563502f39415a734b6c6d5a615059312e',
'8fc9175a-9bc7-4c7b-8eaf-735bdbd240b0');

