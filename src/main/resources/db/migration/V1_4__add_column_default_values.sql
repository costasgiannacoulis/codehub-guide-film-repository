alter table actor alter column id set default actor_seq.nextval;
alter table category alter column id set default category_seq.nextval;
alter table language alter column id set default language_seq.nextval;
alter table film alter column id set default film_seq.nextval;
