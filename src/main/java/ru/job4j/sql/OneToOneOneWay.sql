create table addresses(
    id serial primary key,
    city varchar(255),
    street varchar(255),
    building int
);

create table companies(
    id serial primary key,
    name varchar(255),
    address_id int references addresses(id) unique
);