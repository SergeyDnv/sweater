DELETE
FROM user_role;
DELETE
FROM users;

INSERT INTO users(id, active, password, username)
VALUES (1, true, '$2a$08$Bx6w5S1ebPQWn.RZUdYYQ.envx7bj/aHFZ0l2owgGhL2eeNt3aFSy', 'admin'),
       (2, true, '$2a$08$D5nSIjzmVEOdx8exUVD5huVA982PPH3UL0sWVWe3fhsmuBQz7EC', 'user');

INSERT INTO user_role(user_id, roles)
VALUES (1, 'USER'),
       (1, 'ADMIN'),
       (2, 'USER');
