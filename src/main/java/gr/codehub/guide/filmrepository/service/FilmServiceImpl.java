package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {
	@Autowired
	FilmRepository filmRepository;

	@Override
	public void create(final Film film) {
		filmRepository.save(film);
	}

	@Override
	public void update(final Film film) {
		filmRepository.save(film);
	}

	@Override
	public void delete(final Long id) {
		filmRepository.deleteById(id);
	}

	@Override
	public void delete(final Film film) {
		filmRepository.delete(film);
	}

	@Override
	public boolean exists(final Film film) {
		return locate(film.getId()) != null;
	}

	@Override
	public Film get(final Long id) {
		return locate(id);
	}

	@Override
	public List<Film> findAll() {
		return filmRepository.findAll();
	}

	private Film locate(final Long id) {
		final Optional<Film> filmOptional = filmRepository.findById(id);

		if (!filmOptional.isPresent()) {
			filmOptional
				.orElseThrow(() -> new NullPointerException(String.format("Film with id %d was not found.", id)));
		}
		return filmOptional.get();
	}
}
