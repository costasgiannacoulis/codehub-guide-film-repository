package gr.codehub.guide.filmrepository.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import gr.codehub.guide.filmrepository.service.AbstractDomainService;

/**
 * Abstract controller class declaring basic actions for all underlying entities.
 *
 * @param <T> The entity who's calls to serve.
 */
public abstract class AbstractDomainController<T> {
	/**
	 * Return the actual business service corresponding to the given entity.
	 *
	 * @return The business service.
	 */
	public abstract AbstractDomainService<T> getDomainService();

	/**
	 * Gets the entity matching the given id.

	 * @param id The id who's entity to look for.
	 * @return the entity.
	 */
	@GetMapping("/{id}")
	public T get(@PathVariable("id") final Long id) {
		return getDomainService().get(id);
	}

	/**
	 * Gets the entire list of entities.

	 * @return the list of entities.
	 */
	@GetMapping
	public List<T> findAll() {
		return getDomainService().findAll();
	}

	/**
	 * Creates a new entity.
	 * @param entity The entity's body without id.

	 * @return the newly created entity.
	 */
	@PostMapping
	public ResponseEntity<T> create(@Valid @RequestBody final T entity) {
		final T savedEntity = getDomainService().create(entity);
		return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
	}

	/**
	 * Updates given entity ntity.
	 * @param entity The entity to update

	 * @return the updated entity.
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final T entity) {
		if (getDomainService().exists(entity)) {
			getDomainService().update(entity);
		}
	}

	/**
	 * Deletes the entity associate with the given id.
	 *
	 * @param id the id who's corresponding entity we want to delete.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getDomainService().delete(id);
	}

	/**
	 * Deletes the entity.
	 *
	 * @param entity The entity to delete.
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final T entity) {
		if (getDomainService().exists(entity)) {
			getDomainService().delete(entity);
		}
	}

	/**
	 * Patches the entity.
	 *
	 * @param entity the entity to patch.
	 */
	@PatchMapping("/{id}")
	public void patch(@Valid @RequestBody final T entity) {
		//TODO
	}
}
