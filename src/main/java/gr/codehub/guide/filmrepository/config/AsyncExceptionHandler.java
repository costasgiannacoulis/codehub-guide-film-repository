package gr.codehub.guide.filmrepository.config;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception handler for asynchronous method executions.
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	@Override
	public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
		log.error("Exception Cause: {}", throwable.getMessage());
		log.error("Method name {}", method.getName());
		for (final Object param : obj) {
			log.error("Parameter value: {}", param);
		}
	}
}
