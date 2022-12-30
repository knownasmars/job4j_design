create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('IT');
insert into departments(name) values ('Finances');
insert into departments(name) values ('Operations');
insert into departments(name) values ('Logistics');

insert into employees(name, department_id) values ('Mars', 1);
insert into employees(name, department_id) values ('Natalya', 2);
insert into employees(name, department_id) values ('Viktoriya', 3);
insert into employees(name, department_id) values ('Stas', 1);
insert into employees(name, department_id) values ('Oleg', null);
insert into employees(name, department_id) values ('Andrey', 1);