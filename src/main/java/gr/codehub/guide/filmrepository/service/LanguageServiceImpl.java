package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.exception.ResourceNotFoundException;
import gr.codehub.guide.filmrepository.model.Language;
import gr.codehub.guide.filmrepository.repository.LanguageRepository;

/**
 * Implementation of Language specific business calls.
 */
@Service
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	LanguageRepository languageRepository;

	/**
	 * Creates a {@link Language}.
	 *
	 * @param language the entity to create and persist.
	 *
	 * @return the entity created.
	 */
	@Override
	public Language create(final Language language) {
		return languageRepository.save(language);
	}

	/**
	 * Updates given {@link Language}.
	 *
	 * @param language the entity to update and persist.
	 */
	@Override
	public void update(final Language language) {
		languageRepository.save(language);
	}

	/**
	 * Deletes {@link Language} whose associated id is provided.
	 *
	 * @param id the id whose corresponding entity we want to delete.
	 */
	@Override
	public void delete(final Long id) {
		try {
			languageRepository.deleteById(id);
		} catch (final EmptyResultDataAccessException er) {
			throw new ResourceNotFoundException(String.format("Language with id %d was not found.", id));
		}
	}

	/**
	 * Deletes given {@link Language}.
	 */
	@Override
	public void delete(final Language language) {
		languageRepository.delete(language);
	}

	/**
	 * Check the existence of the given {@link Language}.
	 *
	 * @param language the entity to check.
	 * @return true if entity exists, false otherwise.
	 */
	@Override
	public boolean exists(final Language language) {
		return locate(language.getId()) != null;
	}

	/**
	 * Retrieve the {@link Language} associated with the given id.
	 *
	 * @param id the id whose corresponding entity we want to retrieve.
	 * @return the entity retrieved.
	 */
	@Override
	public Language get(final Long id) {
		return locate(id);
	}

	/**
	 * Find all {@link Language languages}.
	 * @return the list of {@link Language languages}.
	 */
	@Override
	public List<Language> findAll() {
		return languageRepository.findAll();
	}

	/**
	 * Gets and return the {@link Language} matching the given id, otherwise there's an exception thrown.
	 *
	 * @param id The id whose matching {@link Language} we are seeking for.
	 *
	 * @return the matching {@link Language}.
	 */
	private Language locate(final Long id) {
		final Optional<Language> languageOptional = languageRepository.findById(id);

		if (!languageOptional.isPresent()) {
			languageOptional
				.orElseThrow(
					() -> new ResourceNotFoundException(String.format("Language with id %d was not found.", id)));
		}
		return languageOptional.get();
	}
}
