package gr.codehub.guide.filmrepository.service;

import java.util.List;

/**
 * A generic interface declaring entity's common actions.
 *
 * @param <T> The domain class whose business we need to implement.
 */
public interface AbstractDomainService<T> {
	/**
	 * Generic creation of the given entity.
	 *
	 * @param entity the entity we want to create.
	 *
	 * @return the entity created.
	 */
	T create(final T entity);

	/**
	 * Generic update of the given entity.
	 *
	 * @param entity the entity we want to update.
	 */
	void update(T entity);

	/**
	 * Generic deletion of the given entity.
	 *
	 * @param id the id whose corresponding entity we want to delete.
	 */
	void delete(Long id);

	/**
	 * Generic deletion of the given entity.
	 *
	 * @param entity the entity we want to delete.
	 */
	void delete(T entity);

	/**
	 *Check whether the given entity exists.
	 *
	 * @param entity the entity to check
	 * @return true, if the entity exists, false otherwise.
	 */
	boolean exists(T entity);

	/**
	 * Get the entity corresponding to the given id.
	 * @param id the id whose corresponding entity we want to find.
	 * @return the entity.
	 */
	T get(Long id);

	/**
	 *Find all containing entities.
	 * @return the list of all entities of the given type.
	 */
	List<T> findAll();
}
