create table salaries(
	id serial primary key,
	name varchar(255),
	amount integer
);

insert into salaries(name, amount) values ('Mars', 2500);
insert into salaries(name, amount) values ('Andrey', 5000);
insert into salaries(name, amount) values ('Stas', 12000);