-- CREATE TABLES FOR RELATIONS BASE STRUCTURE 
-- users - role = many-to-one 
CREATE TABLE roles(
	id serial primary key,
	name text
);

CREATE TABLE users(
	id serial primary key,
	name text,
	role_id int references roles(id)
);

-- role - rules = many-to-many
CREATE TABLE rules(
	id serial primary key,
	name text
);

CREATE TABLE roles_rules(
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

-- item - users = many-to-one
CREATE TABLE items(
	id serial primary key,
	name text,
	user_id int references users(id)
);

-- item - comments = one-to-many
CREATE TABLE comments(
	id serial primary key, 
	comment text,
	item_id int references items(id)
);

-- item - attachs = one-to-many
CREATE TABLE attachs(
	id serial primary key,
	attach text,
	item_id int references items(id)
);

-- item - category = many-to-one
CREATE TABLE categories(
	id serial primary key,
	category text
);
ALTER TABLE items
ADD COLUMN category_id int references categories(id);

-- item - state = many-to-one
CREATE TABLE states(
	id serial primary key,
	state text
);
ALTER TABLE items
ADD COLUMN state_id int references states(id);