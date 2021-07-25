 create table suppliers(
     id serial primary key,
     name varchar(255)
 );

 create table producers(
     id serial primary key,
     name varchar(255)
 );

 create table producers_suppliers(
     id serial primary key,
     producer_id int references producers(id),
     supplier_id int references suppliers(id)
 );