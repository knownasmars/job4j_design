INSERT INTO roles(name) VALUES ('Java Developer');
INSERT INTO roles(name) VALUES ('Java Boss');
INSERT INTO roles(name) VALUES ('Intern');

INSERT INTO users(name, role_id) VALUES ('Mars', 1);
INSERT INTO users(name, role_id) VALUES ('Petr', 2);
INSERT INTO users(name, role_id) VALUES ('Andrey', 1);

INSERT INTO rules(name) VALUES ('Extended');
INSERT INTO rules(name) VALUES ('Simple');
INSERT INTO rules(name) VALUES ('Minimal');

INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 2);
INSERT INTO roles_rules(role_id, rule_id) VALUES (2, 1);
INSERT INTO roles_rules(role_id, rule_id) VALUES (3, 3);

INSERT INTO items(name, user_id) VALUES ('item_first', 1);
INSERT INTO items(name, user_id) VALUES ('item_second', 1);
INSERT INTO items(name, user_id) VALUES ('item_third', 2);
INSERT INTO items(name, user_id) VALUES ('item_fourth', 2);
INSERT INTO items(name, user_id) VALUES ('item_fifth', 3);
INSERT INTO items(name, user_id) VALUES ('item_sixth', 3);

INSERT INTO comments(comment, item_id) VALUES('Urgent', 1);
INSERT INTO comments(comment, item_id) VALUES('Non-urgent', 2);
INSERT INTO comments(comment, item_id) VALUES('Urgent', 3);
INSERT INTO comments(comment, item_id) VALUES('Non-urgent', 4);
INSERT INTO comments(comment, item_id) VALUES('Non-urgent', 5);
INSERT INTO comments(comment, item_id) VALUES('Urgent', 6);

INSERT INTO attachs(attach, item_id) VALUES('pdf', 1);
INSERT INTO attachs(attach, item_id) VALUES('pdf', 2);
INSERT INTO attachs(attach, item_id) VALUES('pdf', 3);
INSERT INTO attachs(attach, item_id) VALUES('jpg', 4);
INSERT INTO attachs(attach, item_id) VALUES('ps', 5);
INSERT INTO attachs(attach, item_id) VALUES('jpeg', 6);

INSERT INTO categories(category) VALUES('Hard');
INSERT INTO categories(category) VALUES('Medium');
INSERT INTO categories(category) VALUES('Easy');

UPDATE items SET category_id = 1 WHERE id = 1;
UPDATE items SET category_id = 3 WHERE id = 2;
UPDATE items SET category_id = 3 WHERE id = 3;
UPDATE items SET category_id = 1 WHERE id = 4;
UPDATE items SET category_id = 2 WHERE id = 5;
UPDATE items SET category_id = 3 WHERE id = 6;

INSERT INTO states(state) VALUES('Active');
INSERT INTO states(state) VALUES('Closed');
INSERT INTO states(state) VALUES('Analyzed');

UPDATE items SET state_id = 1 WHERE id = 1;
UPDATE items SET state_id = 1 WHERE id = 2;
UPDATE items SET state_id = 1 WHERE id = 3;
UPDATE items SET state_id = 3 WHERE id = 4;
UPDATE items SET state_id = 3 WHERE id = 5;
UPDATE items SET state_id = 2 WHERE id = 6;