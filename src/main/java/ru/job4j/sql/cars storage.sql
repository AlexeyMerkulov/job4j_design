create table body(
	id serial primary key,
	name varchar(255)
);
create table engine(
	id serial primary key,
	name varchar(255)
);
create table gearbox(
	id serial primary key,
	name varchar(255)
);
create table auto(
	id serial primary key,
	name varchar(255),
	body_id int references body(id) not null,
	engine_id int references engine(id) not null,
	gearbox_id int references gearbox(id) not null
);

insert into body(name) values ('sedan'), ('hatchback'), ('pickup');
insert into engine(name) values ('3.0L'), ('2.0L'), ('1.6L');
insert into gearbox(name) values ('manual'), ('automatic'), ('robot');

insert into auto(name, body_id, engine_id, gearbox_id) values ('BMW', 1, 1, 2);
insert into auto(name, body_id, engine_id, gearbox_id) values ('Audi', 2, 2, 3);
insert into auto(name, body_id, engine_id, gearbox_id) values ('Nissan', 1, 2, 2);

select a.name, b.name, e.name, g.name from auto a join body b on a.body_id = b.id
join engine e on a.engine_id = e.id
join gearbox g on a.gearbox_id = g.id;

select * from body b left join auto a on b.id = a.body_id
where a.body_id is null;

select * from engine e left join auto a on e.id = a.engine_id
where a.engine_id is null;

select * from gearbox g left join auto a on g.id = a.gearbox_id
where a.gearbox_id is null;