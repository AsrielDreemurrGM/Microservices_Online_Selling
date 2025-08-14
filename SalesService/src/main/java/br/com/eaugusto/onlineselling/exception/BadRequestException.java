package br.com.eaugusto.onlineselling.exception;

/**
 * Custom exception thrown when a bad request occurs.
 * <p>
 * Typically used for validation failures or incorrect sale inputs.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3909320262784660254L;

	public BadRequestException(String message) {
		super(message);
	}
}
