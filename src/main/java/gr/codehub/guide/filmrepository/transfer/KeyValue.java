package gr.codehub.guide.filmrepository.transfer;

import lombok.Value;

/**
 * This Data Transfer Object is a generic key-value POJO.
 */
@Value
public class KeyValue<K, V> {
	/**
	 * The generic key.
	 */
	K key;
	/**
	 * The generic value.
	 */
	V value;
}
