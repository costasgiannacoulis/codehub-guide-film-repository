package gr.codehub.guide.filmrepository.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import gr.codehub.guide.filmrepository.model.Actor;
import gr.codehub.guide.filmrepository.model.Address;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ActorTests {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void loadEmployees() {
		final Actor actor1 = new Actor();
		actor1.setFirstName("Costas");
		actor1.setLastName("Giannacoulis");

		final Actor actor2 = new Actor();
		actor2.setFirstName("Vasilis");
		actor2.setLastName("Giannacoulis");

		final Address a1 = new Address();
		a1.setStreetName("Thessalias");
		a1.setNumber("57");
		a1.setPostalCode("16673");
		a1.setType("Home");
		a1.setActor(actor1);

		final Address a2 = new Address();
		a2.setStreetName("Thessalias");
		a2.setNumber("57A");
		a2.setPostalCode("16673");
		a2.setType("Home");
		a2.setActor(actor2);

		actor1.getAddressList().add(a1);
		actor2.getAddressList().add(a2);

		actorRepository.saveAll(Arrays.asList(actor1, actor2));

		log.info("Actors found: {}", actorRepository.findAll().size());
		final List<Actor> actors = actorRepository.findAll();
		final Actor actor = actors.get(actors.size() - 1);
		log.info("Before merge, actor's no of address: {}", actor.getAddressList().size());
		//Exhibiting @OneToMany(... fetch = FetchType.LAZY)
		entityManager.merge(actor);
		log.info("After merge, actor's no of address: {}", actor.getAddressList().size());
	}
}
