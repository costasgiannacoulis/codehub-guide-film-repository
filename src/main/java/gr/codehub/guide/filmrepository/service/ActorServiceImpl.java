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

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	ActorRepository actorRepository;

	@Override
	public Actor create(final Actor actor) {
		return actorRepository.save(actor);
	}

	@Override
	public void update(final Actor actor) {
		actorRepository.save(actor);
	}

	@Override
	public void delete(final Long id) {
		try {
			actorRepository.deleteById(id);
		} catch (final EmptyResultDataAccessException er) {
			throw new ResourceNotFoundException(String.format("Actor with id %d was not found.", id));
		}
	}

	@Override
	public void delete(final Actor actor) {
		actorRepository.delete(actor);
	}

	@Override
	public boolean exists(final Actor actor) {
		return locate(actor.getId()) != null;
	}

	@Override
	public Actor get(final Long id) {
		return locate(id);
	}

	@Override
	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	private Actor locate(final Long id) {
		final Optional<Actor> actorOptional = actorRepository.findById(id);

		if (!actorOptional.isPresent()) {
			actorOptional
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Actor with id %d was not found.", id)));
		}
		return actorOptional.get();
	}

	@Override
	public List<ActorFilmsPair> getNumOfFilmsPerActor() {
		return actorRepository.getNumOfFilmsPerActor();
	}
}
