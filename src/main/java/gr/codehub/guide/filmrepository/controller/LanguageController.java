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

@RestController
@RequestMapping("/languages")
public class LanguageController extends AbstractDomainController<Language> {

	@Autowired
	LanguageService service;

	@Override
	public AbstractDomainService<Language> getDomainService() {
		return service;
	}

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
