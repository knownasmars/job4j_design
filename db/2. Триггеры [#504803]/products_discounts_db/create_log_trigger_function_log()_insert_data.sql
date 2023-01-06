insert into products(name, producer, count, price)
values('МОЛОКО','Домик В Деревне', 2, 55);
select * from history_of_price; 

create or replace function log()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date)
		values(new.name, new.price, now());
		return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger log_trigger
    after insert
    on products
    for each row
    execute procedure log();
*/