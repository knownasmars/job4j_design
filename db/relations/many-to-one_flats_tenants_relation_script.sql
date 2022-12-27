create table flats(
    id serial primary key,
    familyName varchar(255)
);

create table tenants(
	id serial primary key,
    name varchar(255),
	flat_id int references flats(id)
);

insert into flats(familyName) values ('Suleymanov');
insert into tenants(name, flat_id) VALUES ('Mars', 1);

select * from tenants;
select * from flats where id in (select flat_id from tenants);