create table type(
	id serial primary key,
	name varchar(255)
);

create table products(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type(name) 
values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('ШОКОЛАД');
insert into product(name, type_id, expired_date, price)
values ('Российский', 1, date '2022-08-10', 650);
insert into product(name, type_id, expired_date, price)
values ('Гауда', 1, date '2022-08-24', 856);
insert into product(name, type_id, expired_date, price)
values ('Камамбер', 1, date '2022-08-14', 932);
insert into product(name, type_id, expired_date, price)
values ('Стародубский', 1, date '2022-08-07', 558);
insert into product(name, type_id, expired_date, price)
values ('Мороженое', 2, date '2022-08-13', 77);
insert into product(name, type_id, expired_date, price)
values ('Сметана', 2, date '2022-08-07', 97);
insert into product(name, type_id, expired_date, price)
values ('Кефир', 2, date '2022-08-12', 67);
insert into product(name, type_id, expired_date, price)
values ('Столичная', 3, date '2022-08-02', 487);
insert into product(name, type_id, expired_date, price)
values ('Докторская', 3, date '2022-08-12', 312);
insert into product(name, type_id, expired_date, price)
values ('Охотничья', 3, date '2022-08-21', 397);
insert into product(name, type_id, expired_date, price)
values ('Аленка', 4, date '2022-08-14', 75);
insert into product(name, type_id, expired_date, price)
values ('Dove', 4, date '2022-08-03', 98);
insert into product(name, type_id, expired_date, price)
values ('Milka', 4, date '2022-08-16', 82);