package gr.codehub.guide.filmrepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.codehub.guide.filmrepository.model.Language;

/**
 * JPA repository implementation regarding Language's standard and custom queries to backend RDBMS.
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
