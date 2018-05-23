package gr.codehub.guide.filmrepository.service;

import java.util.List;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.transfer.ActorFilmsPair;

/**
 * Interface extending common actions by adding Actor specific business calls.
 */
public interface ActorService extends AbstractDomainService<Actor> {
	/**
	 * Gets the number of films per actor.
	 *
	 * @return a list of {@link ActorFilmsPair} DTO's.
	 */
	List<ActorFilmsPair> getNumOfFilmsPerActor();
}
