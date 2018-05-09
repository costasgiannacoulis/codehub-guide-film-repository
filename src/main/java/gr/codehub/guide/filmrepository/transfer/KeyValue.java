package gr.codehub.guide.filmrepository.transfer;

import lombok.Value;

@Value
public class KeyValue<K, V> {
	K key;
	V value;
}
