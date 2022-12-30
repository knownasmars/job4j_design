create table teens(
	id serial primary key,
	name varchar(255),
	gender text
);

insert into teens(name, gender) 
values
	('Milohin', 'female'),
	('VladA4', 'neutral gender'),
	('Morgenshtern', 'third gender');