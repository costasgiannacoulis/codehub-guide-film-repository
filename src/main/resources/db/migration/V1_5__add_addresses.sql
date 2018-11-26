create sequence if not exists address_seq start with 1001 increment by 1;

create table address (
	id bigint not null primary key,
	street_name varchar(50) not null,
	street_number varchar(10) not null,
	postal_code char(5),
	type varchar(20),
	actor_id bigint not null
);
