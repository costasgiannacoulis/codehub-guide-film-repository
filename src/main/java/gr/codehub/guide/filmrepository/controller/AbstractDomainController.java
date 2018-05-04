package gr.codehub.guide.filmrepository.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDomainController<T> {
	@GetMapping("/{id}")
	public T get(@PathVariable("id") final Long id) {
		final T actor = getDomainService().get(id);
		log.debug(actor.toString());
		return actor;
	}

	public abstract AbstractDomainService<T> getDomainService();

	@GetMapping
	public List<T> findAll() {
		return getDomainService().findAll();
	}

	@PostMapping
	public void create(@Valid @RequestBody final T actor) {
		getDomainService().create(actor);
	}

	@PutMapping
	public void update(@Valid @RequestBody final T actor) {
		getDomainService().update(actor);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") final Long id) {
		getDomainService().delete(id);
	}

	@DeleteMapping
	public void delete(@Valid @RequestBody final T actor) {
		getDomainService().delete(actor);
	}

	@PatchMapping("/{id}")
	public void patch(@PathVariable("id") final Long id, @RequestBody final T actor) {
		//TODO
	}
}
