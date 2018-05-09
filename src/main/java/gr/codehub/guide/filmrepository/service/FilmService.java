package gr.codehub.guide.filmrepository.service;

import java.util.List;

import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;

public interface FilmService extends AbstractDomainService<Film> {
	List<KeyValue<Long, String>> getTitles();

	List<FilmActorPair> getNumOfActorsPerFilm();
}
