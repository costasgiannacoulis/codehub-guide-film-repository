package gr.codehub.guide.filmrepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.codehub.guide.filmrepository.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
