package br.com.eaugusto.onlineselling.enums;

import br.com.eaugusto.onlineselling.domain.Product;
import br.com.eaugusto.onlineselling.dto.ProductDTO;

/**
 * Represents the possible status values for a product.
 * <p>
 * This enumeration is used in both the {@link Product} entity and the
 * {@link ProductDTO} to ensure compatibility during conversions between the
 * domain model and the data transfer object.
 * </p>
 *
 * <ul>
 * <li>{@link #ACTIVE} - Indicates that the product is available and
 * active.</li>
 * <li>{@link #INACTIVE} - Indicates that the product is unavailable or
 * disabled.</li>
 * </ul>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 12, 2025
 */
public enum Status {
	ACTIVE, INACTIVE;
}
