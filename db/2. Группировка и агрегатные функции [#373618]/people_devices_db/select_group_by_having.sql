create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values
	('Notebook', 50000.0),
	('Pen', 25.5),
	('Lamp', 1000.0),
	('Iphone', 99999.9);
insert into people(name) values
	('Mars'),
	('Andrey'),
	('Stas');
insert into devices_people(device_id, people_id) values
	(1, 1),
	(3, 1),
	(1, 2),
	(2, 2),
	(1, 3),
	(4, 3);

select avg(price) from devices;

select p.name, avg(t.price) 
from (select people_id, device_id, price from devices_people dp join devices d on dp.device_id = d.id) as t
join people p on p.id = t.people_id
group by p.name;

select p.name, avg(t.price) 
from (select people_id, device_id, price from devices_people dp join devices d on dp.device_id = d.id) as t
join people p on p.id = t.people_id
group by p.name
having avg(t.price) > 5000.0;



