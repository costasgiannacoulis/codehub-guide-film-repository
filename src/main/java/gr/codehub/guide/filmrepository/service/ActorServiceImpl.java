package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.exception.ResourceNotFoundException;
import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.repository.ActorRepository;
import gr.codehub.guide.filmrepository.transfer.ActorFilmsPair;

/**
 * Implementation of Actor specific business calls.
 */
@Service
public class ActorServiceImpl implements ActorService {
	/**
	 * Actor specific repository.
	 */
	@Autowired
	ActorRepository actorRepository;

	/**
	 * Creates a {@link Actor}.
	 *
	 * @param actor the entity to create and persist.
	 *
	 * @return the entity created.
	 */
	@Override
	public Actor create(final Actor actor) {
		return actorRepository.save(actor);
	}

	/**
	 * Updates given {@link Actor}.
	 *
	 * @param actor the entity to update and persist.
	 */
	@Override
	public void update(final Actor actor) {
		actorRepository.save(actor);
	}

	/**
	 * Deletes {@link Actor} whose associated id is provided.
	 *
	 * @param id the id whose corresponding entity we want to delete.
	 */
	@Override
	public void delete(final Long id) {
		try {
			actorRepository.deleteById(id);
		} catch (final EmptyResultDataAccessException er) {
			throw new ResourceNotFoundException(String.format("Actor with id %d was not found.", id));
		}
	}

	/**
	 * Deletes given {@link Actor}.
	 */
	@Override
	public void delete(final Actor actor) {
		actorRepository.delete(actor);
	}

	/**
	 * Check the existence of the given {@link Actor}.
	 *
	 * @param actor the entity to check.
	 * @return true if entity exists, false otherwise.
	 */
	@Override
	public boolean exists(final Actor actor) {
		return locate(actor.getId()) != null;
	}

	/**
	 * Retrieve the {@link Actor} associated with the given id.
	 *
	 * @param id the id whose corresponding entity we want to retrieve.
	 * @return the entity retrieved.
	 */
	@Override
	public Actor get(final Long id) {
		return locate(id);
	}

	/**
	 * Find all {@link Actor actors}.
	 * @return the list of {@link Actor actors}.
	 */
	@Override
	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	/**
	 * Gets and return the {@link Actor} matching the given id, otherwise there's an exception thrown.

	 * @param id The id whose matching {@link Actor} we are seeking for.
	 * @return the matching {@link Actor}.
	 */
	private Actor locate(final Long id) {
		final Optional<Actor> actorOptional = actorRepository.findById(id);

		if (!actorOptional.isPresent()) {
			actorOptional
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Actor with id %d was not found.", id)));
		}
		return actorOptional.get();
	}

	/**
	 * Gets the {@link Actor actor's} fullname and number of films he/she has participated in.
	 * @return the list of {@link ActorFilmsPair} DTOs containing actor's fullname and number of film participations.
	 */
	@Override
	public List<ActorFilmsPair> getNumOfFilmsPerActor() {
		return actorRepository.getNumOfFilmsPerActor();
	}
}
