package gr.codehub.guide.filmrepository.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.codehub.guide.filmrepository.model.Category;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.CategoryService;

/**
 * Controller serving {@link Category} related requests.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractDomainController<Category> {

	/**
	 * Service providing access to {@link Category} specific business calls.
	 */
	@Autowired
	CategoryService service;

	/**
	 * Declaring service implementation in order to support the execution of common actions.
	 *
	 * @return The business service.
	 */
	@Override
	public AbstractDomainService<Category> getDomainService() {
		return service;
	}

	/**
	 * Override default method for creating an {@link Category} object in order to demonstrate URL rewriting.
	 * @param entity The entity's body without id.

	 * @return the persisted {@link Category} object.
	 */
	@Override
	@PostMapping
	public ResponseEntity create(@Valid @RequestBody final Category entity) {
		final Category savedEntity = getDomainService().create(entity);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEntity.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
