begin;
declare
	cursor_products cursor for
		select * from products;