package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.exception.ResourceNotFoundException;
import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.repository.FilmRepository;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;

/**
 * Implementation of Film specific business calls.
 */
@Service
public class FilmServiceImpl implements FilmService {
	@Autowired
	FilmRepository filmRepository;

	/**
	 * Creates a {@link Film}.
	 *
	 * @param film the entity to create and persist.
	 *
	 * @return the entity created.
	 */
	@Override
	public Film create(final Film film) {
		return filmRepository.save(film);
	}

	/**
	 * Updates given {@link Film}.
	 *
	 * @param film the entity to update and persist.
	 */
	@Override
	public void update(final Film film) {
		filmRepository.save(film);
	}

	/**
	 * Deletes {@link Film} whose associated id is provided.
	 *
	 * @param id the id whose corresponding entity we want to delete.
	 */
	@Override
	public void delete(final Long id) {
		try {
			filmRepository.deleteById(id);
		} catch (final EmptyResultDataAccessException er) {
			throw new ResourceNotFoundException(String.format("Film with id %d was not found.", id));
		}
	}

	/**
	 * Deletes given {@link Film}.
	 */
	@Override
	public void delete(final Film film) {
		filmRepository.delete(film);
	}

	/**
	 * Check the existence of the given {@link Film}.
	 *
	 * @param film the entity to check.
	 * @return true if entity exists, false otherwise.
	 */
	@Override
	public boolean exists(final Film film) {
		return locate(film.getId()) != null;
	}

	/**
	 * Retrieve the {@link Film} associated with the given id.
	 *
	 * @param id the id whose corresponding entity we want to retrieve.
	 * @return the entity retrieved.
	 */
	@Override
	public Film get(final Long id) {
		return locate(id);
	}

	/**
	 * Find all {@link Film films}.
	 * @return the list of {@link Film films}.
	 */
	@Override
	public List<Film> findAll() {
		return filmRepository.findAll();
	}

	/**
	 * Gets and return the {@link Film} matching the given id, otherwise there's an exception thrown.
	 *
	 * @param id The id whose matching {@link Film} we are seeking for.
	 *
	 * @return the matching {@link Film}.
	 */
	private Film locate(final Long id) {
		final Optional<Film> filmOptional = filmRepository.findById(id);

		if (!filmOptional.isPresent()) {
			filmOptional
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Film with id %d was not found.", id)));
		}
		return filmOptional.get();
	}

	/**
	 * Get {@link Film} titles.
	 * @return the list of {@link KeyValue} objects containing film's id and title.
	 */
	@Override
	public List<KeyValue<Long, String>> getTitles() {
		return filmRepository.getTitles();
	}

	/**
	 * Get {@link Film} title and number of participant actors.
	 *
	 * @return the list of {@link FilmActorPair} DTOs containing film's title and number of participant actors.
	 */
	@Override
	public List<FilmActorPair> getNumOfActorsPerFilm() {
		return filmRepository.getNumOfActorsPerFilm();
	}
}
