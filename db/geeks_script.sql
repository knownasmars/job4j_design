create table geeks(
	id serial primary key,
	name varchar (255),
	age int,
	sex boolean
);
insert into geeks(name, age, sex) values('Mars', 31, true);
update geeks
set name = 'Mars Suleimanov';
select * from geeks;
delete from geeks; 
select * from geeks;