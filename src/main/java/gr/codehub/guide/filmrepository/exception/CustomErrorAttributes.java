package gr.codehub.guide.filmrepository.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class extends {@link DefaultErrorAttributes} which is the de-facto class whose attributes are returned
 * automatically as response, in case of exception.
 * <br><br>
 * In order to see it in action, make sure that you are not extending {@link ResponseEntityExceptionHandler} by
 * creating a ControllerAdvice annotated controller.
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
	/**
	 * Override this method in order to modify the attributes returned as JSON object, in case an exception has
	 * occurred.
	 *
	 * @param webRequest        Generic interface for a web request
	 * @param includeStackTrace whether to include stack traces in the generated responses.
	 *
	 * @return the updated list of attributes
	 */
	@Override
	public Map<String, Object> getErrorAttributes(final WebRequest webRequest,
		final boolean includeStackTrace) {
		final Map<String, Object> errorAttributes =
			super.getErrorAttributes(webRequest, includeStackTrace);

		final Throwable throwable = getError(webRequest);
		final Throwable cause = throwable.getCause();
		if (cause != null) {
			final Map<String, Object> causeErrorAttributes = new HashMap<>();
			causeErrorAttributes.put("exception", cause.getClass().getName());
			causeErrorAttributes.put("message", cause.getLocalizedMessage());
			errorAttributes.put("cause", causeErrorAttributes);
		}
		return errorAttributes;
	}
}
