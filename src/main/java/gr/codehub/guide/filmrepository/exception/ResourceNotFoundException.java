package gr.codehub.guide.filmrepository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom exception raised when an entity resource is not found. In order to be visible to responses returned to
 * clients, it assumes that we are not providing custom exception management by extending
 * {@link ResponseEntityExceptionHandler}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
