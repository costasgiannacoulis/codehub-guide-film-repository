package gr.codehub.guide.filmrepository.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration class containing custom {@link Executor} and exception handler
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
	/**
	 * Create a custom {@link Executor} used in demonstration asynchronous calls.
	 *
	 * @return The custom {@link Executor}
	 */
	@Override
	@Bean(name = "asyncExecutor")
	public Executor getAsyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(15);
		executor.setQueueCapacity(10);
		executor.setThreadNamePrefix("Async-");
		executor.initialize();
		return executor;
	}

	/**
	 * Create a custom exception handler.
	 *
	 * @return The custom exception handler
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler();
	}
}
