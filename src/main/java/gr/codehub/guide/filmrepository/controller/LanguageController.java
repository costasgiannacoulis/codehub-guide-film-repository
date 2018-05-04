package gr.codehub.guide.filmrepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
