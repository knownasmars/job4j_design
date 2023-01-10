CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country)
values
	('Tony', 'Fat', 42, 'Mexica'),
	('Bob', 'Fletcher', 25, 'USA'),
	('Andrew', 'Birgham', 44, 'Great Britain'),
	('Alexa', 'Tirren', 21, 'Mayorka');