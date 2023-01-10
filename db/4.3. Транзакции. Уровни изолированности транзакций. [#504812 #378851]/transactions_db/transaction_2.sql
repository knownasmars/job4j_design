begin transaction isolation level serializable;

select * from salaries;

update salaries set amount = 30000 where name = 'Andrey';
commit;
