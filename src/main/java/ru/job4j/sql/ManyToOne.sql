create table material(
    id serial primary key,
    name varchar(255)
);

create table furniture(
    id serial primary key,
    name varchar(255),
    material_id int references material(id)
);