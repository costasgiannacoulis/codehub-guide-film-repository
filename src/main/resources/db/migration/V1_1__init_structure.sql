create sequence if not exists actor_seq start with 1001 increment by 1;
create sequence if not exists category_seq start with 1001 increment by 1;
create sequence if not exists language_seq start with 1001 increment by 1;
create sequence if not exists film_seq start with 1001 increment by 1;

create table actor (
	id bigint not null primary key,
	firstname varchar(20) not null,
	lastname varchar(30) not null
);

create table category (
	id bigint not null primary key,
	name varchar(50) not null
);

create table language (
	id bigint not null primary key,
	name varchar(50) not null
);

create table film (
	id bigint not null primary key,
	title varchar(50) not null,
	description varchar(255) not null,
	release integer not null,
	language_id bigint not null,
	length smallint not null,
	rating varchar(10)
);

create table film_actor (
	film_id bigint not null,
	actor_id bigint not null
);

create table film_category (
	film_id bigint not null,
	category_id bigint not null
);
