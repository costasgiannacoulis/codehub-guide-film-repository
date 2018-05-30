package gr.codehub.guide.filmrepository.service;

import java.util.List;

import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;

/**
 * Interface extending common actions by adding Film specific business calls.
 */
public interface FilmService extends AbstractDomainService<Film> {
	/**
	 * Gets a list of {@link KeyValue} DTO's containing {@link Film film's} id and title.
	 *
	 * @return the list of {@link KeyValue} DTO's.
	 */
	List<KeyValue<Long, String>> getTitles();

	/**
	 * Gets a list of {@link FilmActorPair} DTO's containing the number of actors per film.
	 *
	 * @return the list of {@link FilmActorPair} DTO's.
	 */
	List<FilmActorPair> getNumOfActorsPerFilm();
}
