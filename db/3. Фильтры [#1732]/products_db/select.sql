select t.name as "Тип", p.name "Вид" 
from product p
join type t
on p.type_id = t.id
where t.name like 'СЫР';

select p.name as "Название", p.expired_date as "Годен до", p.price as "Цена"
from product p
join type t
on p.type_id = t.id
where p.name like 'Мороженое';

select p.name, p.expired_date
from product p 
join type t 
on p.type_id = t.id 
where expired_date <= current_date;

select name as "Имя", price as "Цена"
from product
where price = (select max(price) from product);

select t.name as "Тип", count(t.name) as "Количество" 
from product p 
join type t 
on p.type_id = t.id
group by t.name;

select p.name, t.name, p.expired_date, p.price 
from product p
join type t
on p.type_id = t.id
where t.name = 'СЫР' OR t.name = 'МОЛОКО' 
order by t.name desc;

select t.name as "Тип", count(t.name) as "Количество" 
from product p 
join type t 
on p.type_id = t.id
group by t.name
having count(t.name) < 10;

select p.name, t.name 
from product p 
join type t 
on p.type_id = t.id
order by t.name;