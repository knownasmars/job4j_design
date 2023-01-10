select * 
from customers c
where c.id NOT IN (select customer_id from orders);

