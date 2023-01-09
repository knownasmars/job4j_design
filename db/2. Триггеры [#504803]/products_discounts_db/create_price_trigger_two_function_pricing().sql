create or replace function pricing()
    returns trigger as
$$
    BEGIN
		new.name = new.name;
		new.price = new.price;
		new.date = new.date;
        return new;
    END;
$$
language 'plpgsql';

create trigger price_trigger_two
    before insert on products
    for each row
    execute procedure pricing();