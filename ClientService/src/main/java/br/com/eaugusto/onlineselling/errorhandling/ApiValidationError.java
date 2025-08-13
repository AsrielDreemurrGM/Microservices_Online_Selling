package br.com.eaugusto.onlineselling.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a validation sub-error in the API response.
 * <p>
 * Contains the object name, optional field, rejected value, and a message
 * describing the validation failure.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError implements ApiSubError {
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	/**
	 * Constructor for object-level validation errors.
	 * 
	 * @param object  the object name
	 * @param message the validation error message
	 */
	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
}
