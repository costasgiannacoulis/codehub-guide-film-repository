package gr.codehub.guide.filmrepository.bootstrap;

import java.util.Arrays;
import java.util.Objects;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.model.Category;
import lombok.extern.slf4j.Slf4j;

/**
 * Show-case class using REST template.
 */
@Component
@Slf4j
public class RestExhibitor implements CommandLineRunner {
	/**
	 * Local URL to actors resource.
	 */
	private static final String ACTORS_URL = "http://localhost:8080/actors/";
	private static final String CATEGORIES_URL = "http://localhost:8080/categories/";

	/**
	 * REST endpoints calls.
	 *
	 * @param args Passing arguments
	 */
	@Override
	public void run(final String... args) {
		log.info("Starting performing some tests calling REST endpoints...");
		final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

		final String url = ACTORS_URL + "{id}";
		final ResponseEntity<Actor> returnedActor = restTemplate.getForEntity(url, Actor.class, 1);
		log.info(Objects.requireNonNull(returnedActor.getBody()).toString());

		final Actor returnedAnotherActor = restTemplate.getForObject(url, Actor.class, 2);
		log.info(Objects.requireNonNull(returnedAnotherActor).toString());

		log.info("Starting performing some tests calling REST endpoints...");

		// HttpHeaders
		final HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
		// Request to return XML format
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");

		// HttpEntity<Employee[]>: To get result as Employee[].
		final HttpEntity<Category[]> entity = new HttpEntity<>(headers);

		// RestTemplate
		final RestTemplate restTemplateUsingApacheApi =
			new RestTemplate(getClientHttpRequestFactoryAdvancedConfiguration());

		// Send request with GET method, and Headers.
		final ResponseEntity<Category[]> response =
			restTemplateUsingApacheApi.exchange(CATEGORIES_URL, HttpMethod.GET, entity, Category[].class);

		final HttpStatus statusCode = response.getStatusCode();
		if (statusCode == HttpStatus.OK) {
			final Category[] list = response.getBody();

			if (list != null) {
				for (final Category c : list) {
					log.info("Category: {} - {}.", c.getId(), c.getName());
				}
			}
		}
	}

	/**
	 * Use HttpClient for further configuration options
	 *
	 * @return the HTTP request factory
	 */
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		final int timeout = 5000;
		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
			= new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
	}

	/**
	 * Use HttpClient for further configuration options
	 *
	 * @return the HTTP request factory
	 */
	private ClientHttpRequestFactory getClientHttpRequestFactoryAdvancedConfiguration() {
		final int timeout = 5000;
		final RequestConfig config = RequestConfig.custom()
			.setConnectTimeout(timeout)
			.setConnectionRequestTimeout(timeout)
			.setSocketTimeout(timeout)
			.build();
		final CloseableHttpClient client = HttpClientBuilder
			.create()
			.setDefaultRequestConfig(config)
			.build();
		return new HttpComponentsClientHttpRequestFactory(client);
	}
}
