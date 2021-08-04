create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('IT'), ('Sales'), ('Marketing');
insert into employees(name, department_id) values ('Andrey', 1), ('Maria', 1);
insert into employees(name, department_id) values ('Anna', null), ('Grigoriy', null);
insert into employees(name, department_id) values ('Sergey', 3), ('Daria', 3);


select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees cross join departments;

select d.name from departments d left join employees e on d.id = e.department_id
where e.department_id is null;

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on d.id = e.department_id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Artem', 'M'), ('Ruslan', 'M');
insert into teens(name, gender) values ('Alena', 'F'), ('Olga', 'F');

select t1.name||' '||t2.name as "Pair" from teens t1 cross join teens t2
where not t1.gender = t2.gender;
