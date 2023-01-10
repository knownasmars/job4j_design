select name from movie
except
(select name from movie intersect select title as name from book)
union
(select title from book
except 
select name as title from movie);
