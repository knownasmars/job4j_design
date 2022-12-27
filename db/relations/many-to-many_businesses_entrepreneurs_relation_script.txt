create table businesses(
    id serial primary key,
    niche varchar(255)
);
 
create table entrepreneurs(
    id serial primary key,
	name varchar(255)
);
 
create table entrepreneurs_businesses(
	id serial primary key,
    entrepreneur_id int references entrepreneurs(id),
    business_id int references businesses(id)
);

insert into businesses(niche) values ('Java Courses');
insert into businesses(niche) values ('New Google Start-up');
insert into businesses(niche) values ('Snowboarding Shop');

insert into entrepreneurs(name) values ('Mars');
insert into entrepreneurs(name) values ('Petr');
insert into entrepreneurs(name) values ('Stas');

insert into entrepreneurs_businesses(entrepreneur_id, business_id) values (1, 2);
insert into entrepreneurs_businesses(entrepreneur_id, business_id) values (1, 3);
insert into entrepreneurs_businesses(entrepreneur_id, business_id) values (2, 1);
insert into entrepreneurs_businesses(entrepreneur_id, business_id) values (3, 1);
insert into entrepreneurs_businesses(entrepreneur_id, business_id) values (3, 2);
insert into entrepreneurs_businesses(entrepreneur_id, business_id) values (3, 3);

SELECT * FROM businesses;
SELECT * FROM entrepreneurs;
SELECT * FROM entrepreneurs_businesses;
