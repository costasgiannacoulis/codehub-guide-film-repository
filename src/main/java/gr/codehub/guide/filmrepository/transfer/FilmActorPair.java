package gr.codehub.guide.filmrepository.transfer;

import lombok.Value;

/**
 * This Data Transfer Object is used to carry the information regarding the number of actors per film.
 */
@Value
public class FilmActorPair {
	/**
	 * Film's title.
	 */
	String title;
	/**
	 * Number of actors per film.
	 */
	Long numOfActors;
}
