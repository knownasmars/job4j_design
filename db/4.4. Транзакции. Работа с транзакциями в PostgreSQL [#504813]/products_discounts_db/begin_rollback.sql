begin transaction;
select * from products;
drop table products;
rollback transaction;
