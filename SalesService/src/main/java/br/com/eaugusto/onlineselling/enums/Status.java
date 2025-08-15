package br.com.eaugusto.onlineselling.enums;

/**
 * Enumeration representing the possible statuses of a sale.
 * <p>
 * A sale can be in one of the following states:
 * <ul>
 * <li>{@link #STARTED} - The sale is ongoing and can be modified.</li>
 * <li>{@link #FINISHED} - The sale is completed and cannot be changed.</li>
 * <li>{@link #CANCELED} - The sale has been canceled and cannot be
 * changed.</li>
 * </ul>
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
public enum Status {
	STARTED, FINISHED, CANCELED;
}
