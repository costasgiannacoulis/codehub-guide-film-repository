package gr.codehub.guide.filmrepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController extends AbstractDomainController<Film> {
	@Autowired
	FilmService service;

	@Override
	public AbstractDomainService<Film> getDomainService() {
		return service;
	}
}
