create table addresses(
    id serial primary key,
    name varchar(255),
);

create table companies(
    id serial primary key,
    name varchar(255)
);

create table companies_addresses(
    id serial primary key,
    address_id int references addresses(id) unique,
    company_id int references companies(id) unique
);