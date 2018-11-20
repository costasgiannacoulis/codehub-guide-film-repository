package gr.codehub.guide.filmrepository.bootstrap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.model.Category;
import gr.codehub.guide.filmrepository.model.Film;
import gr.codehub.guide.filmrepository.model.Language;
import gr.codehub.guide.filmrepository.service.AsyncGroupService;
import gr.codehub.guide.filmrepository.transfer.FilmActorPair;
import gr.codehub.guide.filmrepository.transfer.KeyValue;
import lombok.extern.slf4j.Slf4j;

/**
 * Show-case class hitting asynchronously underlying in-memory database.
 */
@Component
@Slf4j
public class AsyncExhibitor implements ApplicationRunner {
	/**
	 * Service containing a series of asynchronous calls to underlying database.
	 */
	@Autowired
	AsyncGroupService asyncGroupService;

	/**
	 * Asynchronous method calls execution.
	 *
	 * @param args Passing arguments
	 *
	 * @throws Exception Exception thrown in case of unexpected system failures.
	 */
	@Override
	public void run(final ApplicationArguments args) throws Exception {
		log.info("Starting asynchronous exhibition...");
		final LocalDateTime beginning = LocalDateTime.now();

		final CompletableFuture<List<Film>> films = asyncGroupService.findAllFilms();
		final CompletableFuture<List<FilmActorPair>> actorsPerFilm = asyncGroupService.getNumOfActorsPerFilm();
		final CompletableFuture<List<KeyValue<Long, String>>> filmTitles = asyncGroupService.getFilmTitles();
		final CompletableFuture<List<Actor>> actors = asyncGroupService.findAllActors();
		final CompletableFuture<List<Category>> categories = asyncGroupService.findAllCategories();
		final CompletableFuture<List<Language>> languages = asyncGroupService.findAllLanguages();
		final CompletableFuture<List<Film>> films2 = asyncGroupService.findAllFilms();
		final CompletableFuture<List<FilmActorPair>> actorsPerFilm2 = asyncGroupService.getNumOfActorsPerFilm();
		final CompletableFuture<List<KeyValue<Long, String>>> filmTitles2 = asyncGroupService.getFilmTitles();

		// Wait for all async methods to return
		//CompletableFuture.allOf(actors, categories, films, languages).join();

		log.info("Actors found: " + actors.get().size());
		log.info("Categories found: " + categories.get().size());
		log.info("Actors per films found: " + actorsPerFilm.get().size());
		log.info("Films found: " + films.get().size());
		log.info("Film titles found: " + filmTitles.get().size());
		log.info("Languages found: " + languages.get().size());
		log.info("Repeating some film related queries");
		log.info("Actors per films found: " + actorsPerFilm2.get().size());
		log.info("Films found: " + films2.get().size());
		log.info("Film titles found: " + filmTitles2.get().size());

		log.info("Finished asynchronous exhibition in {} seconds.",
			Duration.between(beginning, LocalDateTime.now()).getSeconds());
	}
}
