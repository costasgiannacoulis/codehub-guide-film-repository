package gr.codehub.guide.filmrepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.codehub.guide.filmrepository.model.Category;
import gr.codehub.guide.filmrepository.service.AbstractDomainService;
import gr.codehub.guide.filmrepository.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractDomainController<Category> {

	@Autowired
	CategoryService service;

	@Override
	public AbstractDomainService<Category> getDomainService() {
		return service;
	}
}
