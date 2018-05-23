package gr.codehub.guide.filmrepository.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;

/**
 * JPA repository implementation regarding Film's standard and custom queries to backend RDBMS.
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
	/**
	 * Custom query returning {@link Film film's} id and title.
	 *
	 * @return list of results
	 */
	@Query("select new gr.codehub.guide.filmrepository.transfer.KeyValue(f.id, f.title) from Film f order by f.id" +
		" asc")
	List<KeyValue<Long, String>> getTitles();

	/**
	 * Custom query returning {@link Film film's} title and number of actors participating in that film.
	 * @return list of results
	 */
	@Query("select new gr.codehub.guide.filmrepository.transfer.FilmActorPair(f.title, count(fa)) from Film f " +
		"inner join f.actors fa group by f order by count(fa) desc")
	List<FilmActorPair> getNumOfActorsPerFilm();
}
