package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.exception.ResourceNotFoundException;
import gr.codehub.guide.filmrepository.model.Category;
import gr.codehub.guide.filmrepository.repository.CategoryRepository;

/**
 * Implementation of Category specific business calls.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * Creates a {@link Category}.
	 *
	 * @param category the entity to create and persist.
	 *
	 * @return the entity created.
	 */
	@Override
	public Category create(final Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * Updates given {@link categoryy}.
	 *
	 * @param category the entity to update and persist.
	 */
	@Override
	public void update(final Category category) {
		categoryRepository.save(category);
	}

	/**
	 * Deletes {@link Category} whose associated id is provided.
	 *
	 * @param id the id whose corresponding entity we want to delete.
	 */
	@Override
	public void delete(final Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (final EmptyResultDataAccessException er) {
			throw new ResourceNotFoundException(String.format("Category with id %d was not found.", id));
		}
	}

	/**
	 * Deletes given {@link Category}.
	 */
	@Override
	public void delete(final Category category) {
		categoryRepository.delete(category);
	}

	/**
	 * Check the existence of the given {@link Category}.
	 *
	 * @param category the entity to check.
	 * @return true if entity exists, false otherwise.
	 */
	@Override
	public boolean exists(final Category category) {
		return locate(category.getId()) != null;
	}

	/**
	 * Retrieve the {@link Category} associated with the given id.
	 *
	 * @param id the id whose corresponding entity we want to retrieve.
	 * @return the entity retrieved.
	 */
	@Override
	public Category get(final Long id) {
		return locate(id);
	}

	/**
	 * Find all {@link Category categories}.
	 * @return the list of {@link Category categories}.
	 */
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	/**
	 * Gets and return the {@link Category} matching the given id, otherwise there's an exception thrown.
	 *
	 * @param id The id whose matching {@link Category} we are seeking for.
	 *
	 * @return the matching {@link Category}.
	 */
	private Category locate(final Long id) {
		final Optional<Category> categoryOptional = categoryRepository.findById(id);

		if (!categoryOptional.isPresent()) {
			categoryOptional
				.orElseThrow(
					() -> new ResourceNotFoundException(String.format("Category with id %d was not found.", id)));
		}
		return categoryOptional.get();
	}
}
