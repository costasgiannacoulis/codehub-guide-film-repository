package gr.codehub.guide.filmrepository.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

@RestController
@RequestMapping("/films")
public class FilmController extends AbstractDomainController<Film> {
	@Autowired
	FilmService service;

	@Override
	public AbstractDomainService<Film> getDomainService() {
		return service;
	}

	@Override
	@PostMapping
	public ResponseEntity create(@Valid @RequestBody final Film entity) {
		final Film savedEntity = getDomainService().create(entity);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEntity.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping(headers = "action=getTitles")
	public ResponseEntity<List<KeyValue<Long, String>>> getTitles() {
		return new ResponseEntity<>(service.getTitles(), HttpStatus.OK);
	}

	@GetMapping(headers = "action=getNumOfActorsPerFilm")
	public List<FilmActorPair> getNumOfActorsPerFilm() {
		return service.getNumOfActorsPerFilm();
	}

	/**
	 * Demonstration of dynamic filtering case no1.
	 *
	 * @return the filtered list of films based on given criteria.
	 */
	@GetMapping(headers = "action=filterFilmsNoDependants")
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
	@GetMapping(headers = "action=filterFilmsBasicInfo")
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
	private MappingJacksonValue getMappingJacksonValue(final List<Film> films, final String... attributes) {
		final SimpleBeanPropertyFilter filter =
			SimpleBeanPropertyFilter.filterOutAllExcept(attributes);
		final FilterProvider filters = new SimpleFilterProvider().addFilter("basicFilter", filter);
		final MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(films);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
