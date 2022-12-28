create table vincode(
    id serial primary key,
    number int
);

create table cars(
    id serial primary key,
    model varchar(255),
    vincode_id int references vincode(id) unique
);
insert into vincode(number) values (5555);
insert into vincode(number) values (6666);
insert into vincode(number) values (7777);

insert into cars(model, vincode_id) values ('Ferrari', 1);
insert into cars(model, vincode_id) values ('Lambo', 2);
insert into cars(model, vincode_id) values ('McLaren', 3);
insert into cars(model) values ('ZAZ');
insert into cars(model) values ('VAZ');

select * from cars join vincode on cars.vincode_id = vincode.id;