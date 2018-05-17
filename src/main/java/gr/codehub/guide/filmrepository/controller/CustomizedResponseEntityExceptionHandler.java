package gr.codehub.guide.filmrepository.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import gr.codehub.guide.filmrepository.exception.ApiError;
import gr.codehub.guide.filmrepository.exception.ResourceNotFoundException;

/**
 * Custom exception handling and response object construction.
 */
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	/**
	 * Generic exception handling.
	 *
	 * @param ex      the exception thrown.
	 * @param request the http request whose flow threw the exception.
	 *
	 * @return an {@link ApiError} bean containing the details for this exception.
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiError> handleAllExceptions(final Exception ex, final WebRequest request) {
		return new ResponseEntity<>(getApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request), HttpStatus
			.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Missing resource exception. It used when a resource (domain class) was not found.
	 *
	 * @param ex      the exception thrown.
	 * @param request the http request whose flow threw the exception.
	 *
	 * @return an {@link ApiError} bean containing the details for this exception.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ApiError> handleResourceNotFoundException(final ResourceNotFoundException ex,
		final WebRequest request) {
		return new ResponseEntity<>(getApiError(ex, HttpStatus.NOT_FOUND, request), HttpStatus.NOT_FOUND);
	}

	/**
	 * Exception handling missing request parameters.
	 *
	 * @param ex      the exception thrown.
	 * @param request the http request whose flow threw the exception.
	 *
	 * @return an {@link ApiError} bean containing the details for this exception.
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ApiError> handleMissingServletRequestParameter(
		final MissingServletRequestParameterException ex, final WebRequest request) {
		return new ResponseEntity<>(getApiError(ex, HttpStatus.BAD_REQUEST, request), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handling validation errors that occurred in controller layer when passing arguments do not
	 * match validation rules set on the server side.
	 *
	 * @param ex      the exception thrown.
	 * @param request the http request whose flow threw the exception.
	 *
	 * @return an {@link ApiError} bean containing the details for this exception.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiError> andleMethodArgumentNotValid(
		final MethodArgumentNotValidException ex, final WebRequest request) {
		return new ResponseEntity<>(getApiError(ex, HttpStatus.BAD_REQUEST, request), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handling type mismatch errors that occurred in controller layer when passing arguments do not
	 * match declared type. E.g. expect Long and a String parameter was passed.
	 *
	 * @param ex      the exception thrown.
	 * @param request the http request whose flow threw the exception.
	 *
	 * @return an {@link ApiError} bean containing the details for this exception.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(
		final MethodArgumentTypeMismatchException ex, final WebRequest request) {
		return new ResponseEntity<>(getApiError(ex, HttpStatus.BAD_REQUEST, request), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method assembling {@link ApiError} objects.
	 *
	 * @param ex      The exception thrown.
	 * @param status  The {@link HttpStatus} to return to calling side.
	 * @param request The request that triggered the flow.
	 *
	 * @return generated {@link ApiError} object.
	 */
	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), ex.getMessage(), path);
	}
}
