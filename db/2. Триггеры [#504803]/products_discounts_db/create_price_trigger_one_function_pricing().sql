create or replace function pricing()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';
create trigger price_trigger_one
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure pricing();