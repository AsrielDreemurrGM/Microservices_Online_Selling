package br.com.eaugusto.onlineselling.exception;

/**
 * Custom exception thrown when a bad request occurs.
 * <p>
 * Typically used for validation failures or incorrect client input.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7339546357706827674L;

	public BadRequestException(String message) {
		super(message);
	}
}
