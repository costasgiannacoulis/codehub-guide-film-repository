package gr.codehub.guide.filmrepository.service;

import java.util.List;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.transfer.ActorFilmsPair;

public interface ActorService extends AbstractDomainService<Actor> {
	List<ActorFilmsPair> getNumOfFilmsPerActor();
}
