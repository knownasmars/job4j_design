select avg(price) from devices;

select p.name, avg(d.price) 
from devices_people dp 
join devices d 
on dp.device_id = d.id 
join people p 
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) 
from devices_people dp 
join devices d 
on dp.device_id = d.id 
join people p 
on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000.0;