package gr.codehub.guide.filmrepository.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public List<KeyValue<Long, String>> getTitles() {
		return service.getTitles();
	}

	@GetMapping(headers = "action=getNumOfActorsPerFilm")
	public List<FilmActorPair> getNumOfActorsPerFilm() {
		return service.getNumOfActorsPerFilm();
	}
}
