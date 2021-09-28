CREATE EXTENSION IF NOT EXISTS pgcrypto;

UPDATE users
SET password = crypt(password, gen_salt('bf', 8))