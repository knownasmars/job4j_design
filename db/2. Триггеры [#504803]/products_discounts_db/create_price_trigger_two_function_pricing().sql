create or replace function pricing()
    returns trigger as
$$
    BEGIN
		new.price = new.price + new.price * 0.2;
        return new;
    END;
$$
language 'plpgsql';

create trigger price_trigger_two
    before insert on products
    for each row
    execute procedure pricing();