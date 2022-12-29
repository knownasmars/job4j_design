create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into 
	fauna(name, avg_age, discovery_date) 
values
	('Lion', 25, '1900-02-01'),
	('Bat', 13, '2000-03-01'),
	('Owl', 3000, '1400-10-01'),
	('Fish', 12, null),
	('Snake', 15000, '1000-03-03');

select * from fauna where name LIKE '%Fish%';
select * from fauna where avg_age >= 10000 OR avg_age <= 21000;
select * from fauna where discovery_date ISNULL;
select * from fauna where discovery_date < '1950-01-01';



