select t.gender, t2.gender 
from teens t 
cross join teens t2
where t.gender != t2.gender;