package br.com.eaugusto.onlineselling.exception;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7339546357706827674L;

	public BadRequestException(String message) {
		super(message);
	}
}
