package gr.codehub.guide.filmrepository;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Just a showcase bean showing the use of {@link ConditionalOnProperty}.
 */
@Component
@Slf4j
@ConditionalOnProperty(name = "spring.output.ansi.enabled", havingValue = "always")
public class ExhibitionBean {
	/**
	 * Method called during bean initialization, just for demonstration purposes.
	 */
	@PostConstruct
	public void init() {
		log.info("Matched conditions...");
	}
}
