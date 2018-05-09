package gr.codehub.guide.filmrepository.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(headers = "action=getNumOfFilmsPerActor")
	public List<ActorFilmsPair> getNumOfFilmsPerActor() {
		return service.getNumOfFilmsPerActor();
	}
}
