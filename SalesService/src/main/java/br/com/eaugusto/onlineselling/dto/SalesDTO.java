package br.com.eaugusto.onlineselling.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object representing a sales record.
 * <p>
 * This class is used to transfer sales data between layers, typically from API
 * requests to the service layer.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SalesDTO {

	/**
	 * Unique code identifying the sale.
	 */
	@NotNull
	@Size(min = 2, max = 10)
	private String code;

	/**
	 * ID of the client associated with the sale.
	 */
	@NotNull
	private String clientId;

	/**
	 * Date and time the sale occurred.
	 */
	@NotNull
	private Instant saleDate;
}
