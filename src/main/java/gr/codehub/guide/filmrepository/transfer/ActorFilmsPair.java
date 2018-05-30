package gr.codehub.guide.filmrepository.transfer;

import lombok.Value;

/**
 * This Data Transfer Object is used to carry the information regarding the number of films and actor has
 * participated in.
 */
@Value
public class ActorFilmsPair {
	/**
	 * Actor's name.
	 */
	String actor;
	/**
	 * Number of films he/she has participated in.
	 */
	Long numOfFilms;
}
