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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDomainController<T> {
	public abstract AbstractDomainService<T> getDomainService();

	@GetMapping("/{id}")
	public T get(@PathVariable("id") final Long id) {
		final T entity = getDomainService().get(id);
		log.debug("Returning as REST output: " + entity.toString());
		return entity;
	}

	@GetMapping
	public List<T> findAll() {
		return getDomainService().findAll();
	}

	@PostMapping
	public ResponseEntity<T> create(@Valid @RequestBody final T entity) {
		final T savedEntity = getDomainService().create(entity);
		return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final T entity) {
		if (getDomainService().exists(entity)) {
			getDomainService().update(entity);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getDomainService().delete(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final T entity) {
		if (getDomainService().exists(entity)) {
			getDomainService().delete(entity);
		}
	}

	@PatchMapping("/{id}")
	public void patch(@PathVariable("id") final Long id, @Valid @RequestBody final T entity) {
		//TODO
	}
}
