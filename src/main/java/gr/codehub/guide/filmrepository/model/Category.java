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
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
	@SequenceGenerator(name = "categorySequence", sequenceName = "category_seq", allocationSize = 1)
	private Long id;

	@Size(min = 3, message = "Category title should be at least 3 characters long")
	@Column(length = 50, nullable = false)
	private String name;
}
