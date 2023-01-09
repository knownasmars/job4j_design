begin;
declare cursor_2 scroll cursor for
	select * from products;
	fetch last from cursor_2;
	move backward 4 from cursor_2;
	fetch prior from cursor_2;
	move backward 7 from cursor_2;
	fetch prior from cursor_2;
	move backward 4 from cursor_2;
	fetch first from cursor_2;
close cursor_2;
commit;