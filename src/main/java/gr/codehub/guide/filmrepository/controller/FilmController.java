package gr.codehub.guide.filmrepository.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.FilmService;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller serving {@link Film} related requests.
 */
@RestController
@Slf4j
public class FilmController extends AbstractDomainController<Film> {
	/**
	 * Service providing access to {@link Film} specific business calls.
	 */
	@Autowired
	FilmService service;

	/**
	 * Declaring service implementation in order to support the execution of common actions.
	 *
	 * @return The business service.
	 */
	@Override
	public AbstractDomainService<Film> getDomainService() {
		return service;
	}

	/**
	 * Get's the {@link Film} object with the given id.
	 * <br><br>
	 * Note:<br>
	 * Under normal circumstances, we do not need to override this method. This is happening because we have
	 * hardcoded the annotation @JsonFilter to {@link Film} class and we need to always enable dynamic filtering. In
	 * case you need to change returned http status, use @ResponseStatus annotation.
	 *
	 * @param id The {@link Film} id.
	 *           * @return The {@link Film} retrieved
	 */
	@GetMapping("/films/v1/{id}")
	public MappingJacksonValue getFiltered(@PathVariable("id") final Long id) {
		final Film film = getDomainService().get(id);
		final MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(film);

		return mappingJacksonValue;
	}

	/**
	 * Get's the entire list of {@link Film films}.
	 * <br><br>
	 * Note:<br>
	 * Under normal circumstances, we do not need to override this method. This is happening because we have
	 * hardcoded the annotation @JsonFilter to {@link Film} class and we need to always enable dynamic filtering. In
	 * case you need to change returned http status, use @ResponseStatus annotation.
	 *
	 * @return The list of {@link Film} retrieved
	 */
	@GetMapping("/films/v1")
	public MappingJacksonValue findAllFiltered() {
		final List<Film> films = getDomainService().findAll();
		final MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(films);

		return mappingJacksonValue;
	}

	/**
	 * Override default method for creating an {@link Film} object in order to demonstrate URL rewriting.
	 * @param entity The entity's body without id.

	 * @return the persisted {@link Film} object.
	 */
	@Override
	@PostMapping("/films/v1")
	public ResponseEntity create(@Valid @RequestBody final Film entity) {
		final Film savedEntity = getDomainService().create(entity);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEntity.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/**
	 * Demonstration of returning key-value information regarding the number actors per {@link Film}.
	 *
	 * @return list of key-value structure.
	 */
	@GetMapping(value = "/films/v1", headers = "action=getTitles")
	public ResponseEntity<List<KeyValue<Long, String>>> getTitles() {
		return new ResponseEntity<>(service.getTitles(), HttpStatus.OK);
	}

	/**
	 * Demonstration of returning DTOs regarding the number actors per {@link Film}. Implement versioning via URI path.
	 *
	 * @return list of DTOs.
	 */
	@GetMapping(value = "/films/v1", headers = "action=getNumOfActorsPerFilm")
	public List<FilmActorPair> getNumOfActorsPerFilm() {
		log.info("called getNumOfActorsPerFilm via URI path versioning");
		return service.getNumOfActorsPerFilm();
	}

	/**
	 * Demonstration of returning DTOs regarding the number actors per {@link Film}. Implement versioning via headers.
	 *
	 * @return list of DTOs.
	 */
	@GetMapping(value = "/films", headers = {"action=getNumOfActorsPerFilm", "X-API-VERSION=1"})
	public List<FilmActorPair> getNumOfActorsPerFilmViaHeaders() {
		log.info("called getNumOfActorsPerFilm via header versioning");
		return service.getNumOfActorsPerFilm();
	}

	/**
	 * Demonstration of returning DTOs regarding the number actors per {@link Film}. Implement versioning via
	 * content type.
	 *
	 * @return list of DTOs.
	 */
	@GetMapping(value = "/films", produces = "application/vnd.acme.film-v1+json", headers =
		"action=getNumOfActorsPerFilm")
	public List<FilmActorPair> getNumOfActorsPerFilmViaContentType() {
		log.info("called getNumOfActorsPerFilm via content type versioning");
		return service.getNumOfActorsPerFilm();
	}

	/**
	 * Demonstration of dynamic filtering case no1.
	 *
	 * @return the filtered list of films based on given criteria.
	 */
	@GetMapping(value = "/films/v1", headers = "action=filterFilmsNoDependants")
	public MappingJacksonValue filterFilmsNoDependants() {
		final List<Film> films = getDomainService().findAll();
		final MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(films, "id", "title", "description",
			"release", "length", "rating");

		return mappingJacksonValue;
	}

	/**
	 * Demonstration of dynamic filtering case no2.
	 *
	 * @return the filtered list of films based on given criteria.
	 */
	@GetMapping(value = "/films/v2", headers = "action=filterFilmsBasicInfo")
	public MappingJacksonValue filterFilmsBasicInfo() {
		final List<Film> films = getDomainService().findAll();
		final MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(films, "title", "release");

		return mappingJacksonValue;
	}

	/**
	 * Defines filter and set included attributes.
	 * @param films The list of {@link Film} to be filtered.
	 * @param attributes The attributes to include in the filter.
	 *
	 * @return The list of {@link Film} filtered.
	 */
	private MappingJacksonValue getMappingJacksonValue(final Object films, final String... attributes) {
		SimpleBeanPropertyFilter filter = null;
		if (attributes.length > 0) {
			filter = SimpleBeanPropertyFilter.filterOutAllExcept(attributes);
		} else {
			filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "title", "description",
				"release", "language",  "length", "rating", "actors", "categories");
		}
		final FilterProvider filters = new SimpleFilterProvider().addFilter("basicFilter", filter);
		final MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(films);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
