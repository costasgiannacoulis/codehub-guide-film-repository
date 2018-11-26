package gr.codehub.guide.filmrepository.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Address entity.
 */
@Entity
@Data
@NoArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = -7889312197790905891L;
	/**
	 * Address's unique id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
	@SequenceGenerator(name = "addressSequence", sequenceName = "address_seq", allocationSize = 1)
	private Long id;

	/**
	 * Address's street name.
	 */
	@Column(name = "street_name", length = 50, nullable = false)
	private String streetName;

	/**
	 * Address's street number.
	 */
	@Column(name = "street_number", length = 10, nullable = false)
	private String number;

	/**
	 * Address's postal code.
	 */
	@Column(name = "postal_code", length = 5, nullable = true, columnDefinition = "char")
	private String postalCode;

	/**
	 * Address's type (e.g. business, personal).
	 */
	@Column(name = "type", length = 20, nullable = true)
	private String type;

	@ManyToOne(optional = false)
	@JoinColumn(name = "actor_id", nullable = false)
	private Actor actor;
}
