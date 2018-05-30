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

import gr.codehub.guide.filmrepository.model.Language;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.LanguageService;

/**
 * Controller serving {@link Language} related requests.
 */
@RestController
@RequestMapping("/languages")
public class LanguageController extends AbstractDomainController<Language> {

	/**
	 * Service providing access to {@link Language} specific business calls.
	 */
	@Autowired
	LanguageService service;

	/**
	 * Declaring service implementation in order to support the execution of common actions.
	 *
	 * @return The business service.
	 */
	@Override
	public AbstractDomainService<Language> getDomainService() {
		return service;
	}

	/**
	 * Override default method for creating an {@link Language} object in order to demonstrate URL rewriting.
	 * @param entity The entity's body without id.

	 * @return the persisted {@link Language} object.
	 */
	@Override
	@PostMapping
	public ResponseEntity create(@Valid @RequestBody final Language entity) {
		final Language savedEntity = getDomainService().create(entity);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEntity.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
