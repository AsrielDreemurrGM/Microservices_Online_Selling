package br.com.eaugusto.onlineselling.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

/**
 * Exception thrown when an entity is not found in the database.
 * <p>
 * Provides a detailed message including the entity type and the parameters used
 * in the search.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1567124766010201099L;

	/**
	 * Constructs a new exception for an entity not found.
	 * 
	 * @param entityClass     the entity class
	 * @param searchParamsMap alternating key/value parameters used for searching
	 */
	public EntityNotFoundException(Class<?> entityClass, String... searchParamsMap) {
		super(EntityNotFoundException.generateMessage(entityClass.getSimpleName(),
				toMap(String.class, String.class, (Object[]) searchParamsMap)));
	}

	private static String generateMessage(String entityName, Map<String, String> searchParams) {
		return StringUtils.capitalize(entityName) + " was not found for parameters " + searchParams;
	}

	private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
		if (entries.length % 2 == 1) {
			throw new IllegalArgumentException("Invalid entries");
		}

		return IntStream.range(0, entries.length / 2).map(index -> index * 2).collect(HashMap::new,
				(map, index) -> map.put(keyType.cast(entries[index]), valueType.cast(entries[index + 1])), Map::putAll);
	}
}
