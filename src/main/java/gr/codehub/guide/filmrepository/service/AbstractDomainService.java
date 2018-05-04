package gr.codehub.guide.filmrepository.service;

import java.util.List;

public interface AbstractDomainService<T> {
	void create(final T entity);

	void update(T entity);

	void delete(Long id);

	void delete(T entity);

	boolean exists(T entity);

	T get(Long id);

	List<T> findAll();
}
