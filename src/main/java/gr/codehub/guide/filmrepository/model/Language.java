package gr.codehub.guide.filmrepository.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Language implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "languageSequence")
	@SequenceGenerator(name = "languageSequence", sequenceName = "language_seq", allocationSize = 1)
	private Long id;

	@Size(min = 3, message = "Language title should be at least 3 characters long")
	@Column(length = 50, nullable = false)
	private String name;
}
