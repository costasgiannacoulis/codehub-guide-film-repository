--
insert into actor (id, firstname, lastname)
select * from csvread('classpath:scripts/actor.csv', 'ID;FIRSTNAME;LASTNAME', 'fieldSeparator=;');

insert into category (id, name)
select * from csvread('classpath:scripts/category.csv', 'ID;NAME', 'fieldSeparator=;');

insert into language (id, name)
select * from csvread('classpath:scripts/language.csv', 'ID;NAME', 'fieldSeparator=;');

insert into film (id, title, description, release, language_id, length, rating)
select * from csvread('classpath:scripts/film.csv', 'ID;TITLE;DESCRIPTION;RELEASE;LANGUAGE_ID;LENGTH;RATING',
'fieldSeparator=;');

insert into film_actor (actor_id, film_id)
select * from csvread('classpath:scripts/film-actor.csv', 'ACTOR_ID;FILM_ID', 'fieldSeparator=;');

insert into film_category (film_id, category_id)
select * from csvread('classpath:scripts/film-category.csv', 'FILM_ID;CATEGORY_ID', 'fieldSeparator=;');
