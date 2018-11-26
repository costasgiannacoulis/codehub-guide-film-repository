package gr.codehub.guide.filmrepository.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.ActorService;
import gr.codehub.guide.filmrepository.transfer.ActorFilmsPair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller serving {@link Actor} related requests.
 */
@RestController
@RequestMapping("/actors")
@Api()
public class ActorController extends AbstractDomainController<Actor> {
	/**
	 * Object giving access to resource bundles.
	 */
	@Autowired
	MessageSource messageSource;

	/**
	 * Service providing access to {@link Actor} specific business calls.
	 */
	@Autowired
	ActorService service;

	/**
	 * Declaring service implementation in order to support the execution of common actions.
	 *
	 * @return The business service.
	 */
	@Override
	public AbstractDomainService<Actor> getDomainService() {
		return service;
	}

	/**
	 * Override default method for creating an {@link Actor} object in order to demonstrate URL rewriting.
	 * @param entity The entity's body without id.

	 * @return the persisted {@link Actor} object.
	 */
	@Override
	@PostMapping
	@ApiOperation(value = "Create an Actor", notes = "Creates a new Actor entity based on a validated input JSON " +
		"document")
	public ResponseEntity create(@Valid @RequestBody final Actor entity) {
		final Actor savedEntity = getDomainService().create(entity);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEntity.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/**
	 * Return the {@link Actor actors} along with the number of films they have participated in.

	 * @return the list of {@link ActorFilmsPair} DTOs containing actor's fullname and total number of films.
	 */
	@GetMapping(headers = "action=getNumOfFilmsPerActor")
	public List<ActorFilmsPair> getNumOfFilmsPerActor() {
		return service.getNumOfFilmsPerActor();
	}

	/**
	 * Method demonstrating locale definition during content retrieval.

	 * @param locale the client's {@link Locale}.
	 * @return requested content respecting client's {@link Locale} or default {@link Locale} if specific locale is
	 * missing from implementation.
	 */
	@GetMapping(headers = "action=getLocalizedContentWithHeaders")
	public String getLocalizedContentWithHeaders(@RequestHeader(name = "Accept-Language", required = false) final
	Locale locale) {
		return messageSource.getMessage("film.goodmorning", null, locale);
	}

	/**
	 * Method demonstrating locale definition during content retrieval in conjunction with the use of
	 * {@link AcceptHeaderLocaleResolver}.

	 * @return requested content respecting client's {@link Locale} or default {@link Locale} if specific locale is
	 * missing from implementation.
	 */
	@GetMapping(headers = "action=geLocalizedContent")
	public String getLocalizedContent() {
		return messageSource.getMessage("film.goodmorning", null, LocaleContextHolder.getLocale());
	}
}
