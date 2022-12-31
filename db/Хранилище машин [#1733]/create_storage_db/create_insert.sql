create table car_bodies(
	id serial primary key,
	name varchar(255)
);
create table car_engines(
	id serial primary key,
	name varchar(255)
);
create table car_transmissions(
	id serial primary key,
	name varchar(255)
);
create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Sedan'); 
insert into car_bodies(name) values('Coupe'); 
insert into car_bodies(name) values('Convertible'); 
insert into car_bodies(name) values('Minivan'); 
insert into car_bodies(name) values('SUV'); 
insert into car_bodies(name) values('Van'); 
insert into car_bodies(name) values('Hatchback');
insert into car_bodies(name) values('Wagon'); 

insert into car_engines(name) values('Internal combustion');
insert into car_engines(name) values('Hybrid');
insert into car_engines(name) values('Electric');
insert into car_engines(name) values('Kartonnaya');

insert into car_transmissions(name) values('Manual');
insert into car_transmissions(name) values('Automatic');
insert into car_transmissions(name) values('CVT');
insert into car_transmissions(name) values('Domestic');

insert into cars(name, body_id, engine_id, transmission_id)
values('Ferrari', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values('Lambo', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values('Toyota', 1, 2, 3);
insert into cars(name, body_id, engine_id, transmission_id)
values('VAZ', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) 
values('Jeep', null, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id)
values('ZAZ', null, null, null);
insert into cars(name, body_id, engine_id, transmission_id)
values('Marusya', 5, null, 3);