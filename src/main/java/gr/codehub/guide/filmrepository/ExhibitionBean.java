package gr.codehub.guide.filmrepository;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(name = "spring.output.ansi.enabled", havingValue = "always")
public class ExhibitionBean {
	@PostConstruct
	public void init() {
		log.info("Matched conditions...");
	}
}
