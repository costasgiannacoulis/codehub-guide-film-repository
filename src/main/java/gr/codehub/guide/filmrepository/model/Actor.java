package gr.codehub.guide.filmrepository.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import gr.codehub.guide.filmrepository.transfer.ActorFilmsPair;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedNativeQuery(
	name = "Actor.getNumOfFilmsPerActor",
	query = "select concat(a.firstname,' ', a.lastname) as actor, count(fa.film_id) as numOfFilms from actor a, " +
		"film_actor fa where a.id=fa.actor_id group by a.id order by 2 desc",
	resultSetMapping = "FilmsPerActor")
@SqlResultSetMappings({
	@SqlResultSetMapping(
		name = "FilmsPerActor",
		classes = @ConstructorResult(
			targetClass = ActorFilmsPair.class,
			columns = {
				@ColumnResult(name = "actor", type = String.class),
				@ColumnResult(name = "numOfFilms", type = Long.class)
			}
		)
	)
})

@Entity
@Data
@NoArgsConstructor
public class Actor implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actorSequence")
	@SequenceGenerator(name = "actorSequence", sequenceName = "actor_seq", allocationSize = 1)
	private Long id;

	@Column(length = 20, nullable = false)
	private String firstName;

	@Column(length = 30, nullable = false)
	private String lastName;
}
