create table classes(
    id serial primary key,
    name varchar(255)
);

create table students(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    age int,
    class_id int references classes(id)
);

insert into classes(name) values ('1-A');
insert into classes(name) values ('3-B');
insert into classes(name) values ('7-C');

insert into students(name, surname, age, class_id) values ('Andrey', 'Belov', 6, 1);
insert into students(name, surname, age, class_id) values ('Petr', 'Sidorov', 8, 2);
insert into students(name, surname, age, class_id) values ('Egor', 'Mishin', 6, 1);
insert into students(name, surname, age, class_id) values ('Anton', 'Bokov', 13, 3);
insert into students(name, surname, age, class_id) values ('Masha', 'Ivanova', 7, 1);

select * from students s join classes c on s.class_id = c.id;
select s.name, s.surname, s.age, c.name from students s join classes c on s.class_id = c.id;
select s.name as Имя, s.surname as Фамилия, s.age as Возраст, c.name as Класс
from students s join classes c on s.class_id = c.id;