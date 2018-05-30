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

/**
 * The Film entity.
 */
@Entity
@Data
@NoArgsConstructor
// Static filtering demo case. These attributes will be ignored during JSON representation generation.
// @JsonIgnoreProperties(value = {"actors", "categories"})
@JsonFilter("basicFilter")
public class Film implements Serializable {
	/**
	 * The film's unique id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filmSequence")
	@SequenceGenerator(name = "filmSequence", sequenceName = "film_seq", allocationSize = 1)
	private Long id;

	/**
	 * The film's title.
	 */
	@Size(min = 3, message = "Film title should be at least 3 characters long")
	@Column(length = 50, nullable = false)
	private String title;

	/**
	 * The film's unique description.
	 */
	@Column(length = 255)
	private String description;

	/**
	 * The film's year of release.
	 */
	@Min(value = 1900, message = "There were no films before 1900")
	@Column(nullable = false)
	private int release;

	/**
	 * The film's language.
	 */
	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;

	/**
	 * The film's length in minutes.
	 */
	@Column(nullable = false)
	private int length;

	/**
	 * The film's rating.
	 */
	@Column(length = 15, nullable = false)
	private String rating;

	/**
	 * The film's list of actors.
	 */
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "film_actor",
		joinColumns = @JoinColumn(name = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	// StaticFiltering
	// @JsonIgnore
	private Set<Actor> actors;

	/**
	 * The film's categories.
	 */
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "film_category",
		joinColumns = @JoinColumn(name = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories;
}
