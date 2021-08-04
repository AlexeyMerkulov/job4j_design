select * from product p join type t on p.id = t.id
where t.name = 'СЫР';

select * from product where name like '%мороженое%';

select * from product where current_date > expired_date;

select * from product
where price = (select max(price) from product);

select t.name, count(*) as amount from product p join type t on p.id = t.id
group by t.name;

select * from product p join type t on p.id = t.id
where t.name = 'СЫР' OR 'МОЛОКО';

select t.name, count(*) as amount from product p join type t on p.id = t.id
group by t.name having count(*) < 10;

select p.name, p.expired_date, p.price, t.name from product p join type t on p.id = t.id;