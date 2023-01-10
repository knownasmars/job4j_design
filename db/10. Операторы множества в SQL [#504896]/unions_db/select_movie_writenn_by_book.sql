select name from movie
intersect
select title as name from book;