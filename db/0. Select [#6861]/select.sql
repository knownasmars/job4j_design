create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into 
	fauna(name, avg_age, discovery_date) 
values
	('Lion', 25, '01.02.1900'),
	('Bat', 13, '01.03.2000'),
	('Owl', 3000, '01.10.1400'),
	('Fish', 12, null),
	('Snake', 15000, '03.03.1000');

select * from fauna where name LIKE '%Fish%';
select * from fauna where avg_age >= 10000 OR avg_age <= 21000;
select * from fauna where discovery_date ISNULL;
select * from fauna where discovery_date < '01.01.1950';



