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

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.ActorService;
import gr.codehub.guide.filmrepository.transfer.ActorFilmsPair;

@RestController
@RequestMapping("/actors")
public class ActorController extends AbstractDomainController<Actor> {

	@Autowired
	ActorService service;

	@Override
	public AbstractDomainService<Actor> getDomainService() {
		return service;
	}

	@Override
	@PostMapping
	public ResponseEntity create(@Valid @RequestBody final Actor entity) {
		final Actor savedEntity = getDomainService().create(entity);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEntity.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping(headers = "action=getNumOfFilmsPerActor")
	public List<ActorFilmsPair> getNumOfFilmsPerActor() {
		return service.getNumOfFilmsPerActor();
	}
}
