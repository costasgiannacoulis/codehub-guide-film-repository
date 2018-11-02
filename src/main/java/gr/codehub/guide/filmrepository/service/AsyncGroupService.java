package gr.codehub.guide.filmrepository.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.model.Category;
import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.model.Language;
import gr.codehub.guide.filmrepository.repository.ActorRepository;
import gr.codehub.guide.filmrepository.repository.CategoryRepository;
import gr.codehub.guide.filmrepository.repository.FilmRepository;
import gr.codehub.guide.filmrepository.repository.LanguageRepository;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;
import lombok.extern.slf4j.Slf4j;

/**
 * Service containing a series of asynchronous calls to underlying database.
 */
@Service
@Slf4j
public class AsyncGroupService {
	/**
	 * Actor specific repository.
	 */
	@Autowired
	ActorRepository actorRepository;
	/**
	 * Category specific repository.
	 */
	@Autowired
	CategoryRepository categoryRepository;
	/**
	 * Film specific repository.
	 */
	@Autowired
	FilmRepository filmRepository;
	/**
	 * Language specific repository.
	 */
	@Autowired
	LanguageRepository languageRepository;

	@Async("asyncExecutor")
	public CompletableFuture<List<Actor>> findAllActors() throws InterruptedException {
		log.info("Start finding all actors...");
		final List<Actor> actors = actorRepository.findAll();
		Thread.sleep(1000L);
		log.info("Finish finding all actors.");
		return CompletableFuture.completedFuture(actors);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Category>> findAllCategories() throws InterruptedException {
		log.info("Start finding all categories...");
		final List<Category> categories = categoryRepository.findAll();
		Thread.sleep(1000L);
		log.info("Finished finding all categories.");
		return CompletableFuture.completedFuture(categories);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Film>> findAllFilms() throws InterruptedException {
		log.info("Start finding all films...");
		final List<Film> films = filmRepository.findAll();
		Thread.sleep(1000L);
		log.info("Finished finding all films.");
		return CompletableFuture.completedFuture(films);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<FilmActorPair>> getNumOfActorsPerFilm() throws InterruptedException {
		log.info("Start finding number of actors per film...");
		final List<FilmActorPair> actorsPerFilm = filmRepository.getNumOfActorsPerFilm();
		Thread.sleep(1000L);
		log.info("Finished number of actors per film.");
		return CompletableFuture.completedFuture(actorsPerFilm);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<KeyValue<Long, String>>> getFilmTitles() throws InterruptedException {
		log.info("Start finding film titles...");
		final List<KeyValue<Long, String>> filmTitles = filmRepository.getTitles();
		Thread.sleep(1000L);
		log.info("Finished film titles.");
		return CompletableFuture.completedFuture(filmTitles);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Language>> findAllLanguages() throws InterruptedException {
		log.info("Start finding all languages...");
		final List<Language> languages = languageRepository.findAll();
		Thread.sleep(1000L);
		log.info("Finished finding all languages.");
		return CompletableFuture.completedFuture(languages);
	}
}

