create view non_active_orders_with_2_or_more_books
	as select s.name as "Имя", count(a.name), a.name from students as s
		join orders o on s.id = o.student_id
		join books b on o.book_id = b.id
		join authors a on b.author_id = a.id
		group by (s.name, a.name, o.active) having count(a.name) >= 2 
		and o.active is false;