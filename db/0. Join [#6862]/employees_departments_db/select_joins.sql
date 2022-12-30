-- left & right joins
select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
select * from departments d left join employees e on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;

-- full joins
select * from departments d full join employees e on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;

-- cross joins
select * from employees e cross join departments d;
select * from departments d cross join employees e;

-- departments with no employees
select d.name as "Департамент"
from departments d 
left join employees e 
on d.id = e.department_id 
where e.name is null;

-- joins with equal result
select e.id, e.name, e.department_id, d.id, d.name 
from departments d 
right join employees e 
on e.department_id = d.id;

select * 
from employees e 
left join departments d 
on e.department_id = d.id;