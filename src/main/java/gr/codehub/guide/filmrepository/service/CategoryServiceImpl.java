package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.exception.ResourceNotFoundException;
import gr.codehub.guide.filmrepository.model.Category;
import gr.codehub.guide.filmrepository.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void create(final Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void update(final Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void delete(final Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(final Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public boolean exists(final Category category) {
		return locate(category.getId()) != null;
	}

	@Override
	public Category get(final Long id) {
		return locate(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	private Category locate(final Long id) {
		final Optional<Category> categoryOptional = categoryRepository.findById(id);

		if (!categoryOptional.isPresent()) {
			categoryOptional
				.orElseThrow(
					() -> new ResourceNotFoundException(String.format("Category with id %d was not found.", id)));
		}
		return categoryOptional.get();
	}
}
