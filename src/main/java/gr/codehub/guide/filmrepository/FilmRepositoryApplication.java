package gr.codehub.guide.filmrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
