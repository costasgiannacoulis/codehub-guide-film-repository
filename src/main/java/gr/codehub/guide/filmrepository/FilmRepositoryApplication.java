package gr.codehub.guide.filmrepository;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Application launcher class.
 */
@SpringBootApplication
public class FilmRepositoryApplication {
	/**
	 * Application launcher method
	 *
	 * @param args the command line parameters passed
	 */
	public static void main(final String[] args) {
		SpringApplication.run(FilmRepositoryApplication.class, args);
	}

	/**
	 * Instantiates a session locale resolver with US locale being the default locale.
	 *
	 * @return the instantiated resolver.
	 */
	//@Bean
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);

		return localeResolver;
	}

	/**
	 * Instantiates an accept header locale resolver with US locale being the default locale.
	 *
	 * @return the instantiated resolver.
	 */
	@Bean
	public LocaleResolver acceptHeaderLocaleResolver() {
		final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);

		return localeResolver;
	}

	/**
	 * Instantiates a message source bean defaulting to messages.properties
	 *
	 * @return The resource bundle.
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
