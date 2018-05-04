package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.model.Language;
import gr.codehub.guide.filmrepository.repository.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	LanguageRepository languageRepository;

	@Override
	public void create(final Language language) {
		languageRepository.save(language);
	}

	@Override
	public void update(final Language language) {
		languageRepository.save(language);
	}

	@Override
	public void delete(final Long id) {
		languageRepository.deleteById(id);
	}

	@Override
	public void delete(final Language language) {
		languageRepository.delete(language);
	}

	@Override
	public boolean exists(final Language language) {
		return locate(language.getId()) != null;
	}

	@Override
	public Language get(final Long id) {
		return locate(id);
	}

	@Override
	public List<Language> findAll() {
		return languageRepository.findAll();
	}

	private Language locate(final Long id) {
		final Optional<Language> languageOptional = languageRepository.findById(id);

		if (!languageOptional.isPresent()) {
			languageOptional
				.orElseThrow(() -> new NullPointerException(String.format("Language with id %d was not found.", id)));
		}
		return languageOptional.get();
	}
}
