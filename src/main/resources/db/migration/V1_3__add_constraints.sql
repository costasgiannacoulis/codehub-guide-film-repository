alter table film add constraint film_fk1 foreign key (language_id) references language(id);

alter table film_actor add primary key (film_id, actor_id);
alter table film_actor add constraint film_actor_fk1 foreign key (film_id) references film(id);
alter table film_actor add constraint film_actor_fk2 foreign key (actor_id) references actor(id);

alter table film_category add primary key (film_id, category_id);
alter table film_category add constraint film_category_fk1 foreign key (film_id) references film(id);
alter table film_category add constraint film_category_fk2 foreign key (category_id) references category(id);
