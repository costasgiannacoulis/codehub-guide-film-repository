package gr.codehub.guide.filmrepository.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

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
