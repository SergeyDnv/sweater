DELETE
FROM message;

INSERT INTO message(id, text, tag, user_id)
VALUES (1, 'text1', 'tag1', 1),
       (2, 'text2', 'tag2', 1),
       (3, 'text3', 'tag3', 1),
       (4, 'text4', 'tag4', 1);

ALTER SEQUENCE hibernate_sequence RESTART WITH 10;
