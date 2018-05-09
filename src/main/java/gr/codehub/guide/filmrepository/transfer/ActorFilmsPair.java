package gr.codehub.guide.filmrepository.transfer;

import lombok.Value;

@Value
public class ActorFilmsPair {
	String actor;
	Long numOfFilms;
}
