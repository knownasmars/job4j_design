begin transaction isolation level serializable;
	select * from salaries;
	update salaries set amount = 50000 where name = 'Mars';
	select * from salaries;
commit;
