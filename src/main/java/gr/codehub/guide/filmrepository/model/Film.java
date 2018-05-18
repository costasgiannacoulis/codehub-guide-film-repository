package gr.codehub.guide.filmrepository.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// Static filtering demo case. These attributes will be ignored during JSON representation generation.
// @JsonIgnoreProperties(value = {"actors", "categories"})
@JsonFilter("basicFilter")
public class Film implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filmSequence")
	@SequenceGenerator(name = "filmSequence", sequenceName = "film_seq", allocationSize = 1)
	private Long id;

	@Size(min = 3, message = "Film title should be at least 3 characters long")
	@Column(length = 50, nullable = false)
	private String title;

	@Column(length = 255)
	private String description;

	@Min(value = 1900, message = "There were no films before 1900")
	@Column(nullable = false)
	private int release;

	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;

	@Column(nullable = false)
	private int length;

	@Column(length = 15, nullable = false)
	private String rating;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "film_actor",
		joinColumns = @JoinColumn(name = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	// StaticFiltering
	// @JsonIgnore
	private Set<Actor> actors;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "film_category",
		joinColumns = @JoinColumn(name = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories;
}
