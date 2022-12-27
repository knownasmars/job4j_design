create table vincodes(
   	id serial primary key,
   	vincode int
);

create table cars(
    id serial primary key,
    model varchar(255),
    vincode_id int references vincodes(id) unique
);

insert into vincodes(vincode) values (111);
insert into cars(model, vincode_id) values ('McLaren', 1);

select * FROM vincodes;
select * from cars;
