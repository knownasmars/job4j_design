CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) 
values
	(424, 1),
	(5522, 2),
	(66744, 3),
	(20040, 4),
	(44400, 2),
	(2452, 1);