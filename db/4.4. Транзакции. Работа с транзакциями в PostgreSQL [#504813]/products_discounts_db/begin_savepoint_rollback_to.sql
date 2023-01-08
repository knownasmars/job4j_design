begin transaction;
select * from products;
insert into products(name, producer, count, price)
values('ВАНТУЗ', 'Первый Вантузный Завод', 100, 30);
savepoint savepoint_first;
delete from products where name LIKE 'ВАНТУЗ';
rollback to savepoint_first;
rollback;