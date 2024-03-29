create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Iphone', 15000), ('Samsung', 10000), ('Huawei', 5000), ('Xiaomi', 4000);
insert into people(name) values ('Andrey'), ('Maria'), ('Mikhail');
insert into devices_people(device_id, people_id) values (1, 1), (2, 1);
insert into devices_people(device_id, people_id) values (1, 2), (4, 2);
insert into devices_people(device_id, people_id) values (3, 3), (4, 3);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people dp join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) from devices_people dp join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name having avg(d.price) > 5000;