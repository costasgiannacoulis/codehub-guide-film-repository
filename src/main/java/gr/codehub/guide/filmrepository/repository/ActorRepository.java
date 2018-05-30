package gr.codehub.guide.filmrepository.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gr.codehub.guide.filmrepository.model.Actor;

/**
 * JPA repository implementation regarding Actor's standard and custom queries to backend RDBMS.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
	/*
	DON'T return List<ActorFilmsPair>, just List. This is a bug introduced in Spring Data JPA 2.0.5
		Check this https://stackoverflow.com/questions/49056084/got-different-size-of-tuples-and-aliases-exception-after-spring-boot-2-0-0-rel?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
		and https://jira.spring.io/browse/DATAJPA-1280 for more details
	 */

	/**
	 * Custom query returning {@link Actor actor's} fullname and number of films he has participating in.
	 *
	 * @return list of results
	 */
	@Query(name = "Actor.getNumOfFilmsPerActor", nativeQuery = true)
	List getNumOfFilmsPerActor();
}
