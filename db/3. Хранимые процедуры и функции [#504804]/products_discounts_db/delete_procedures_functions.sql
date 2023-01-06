create or replace procedure delete(u_id integer, u_count integer)
language 'plpgsql'
as 
$$
    BEGIN
        if u_count > 0 THEN
			DELETE FROM products
			WHERE id = u_id;
        end if;
    END;
$$;

call delete(10, 1);

create or replace function f_delete(u_name varchar)
returns void
language 'plpgsql'
as
$$
    begin
		if u_name LIKE 'СЫР' THEN 
			delete from products
			WHERE count > 1;
		end if;
    end;
$$;

select f_delete('СЫР');
