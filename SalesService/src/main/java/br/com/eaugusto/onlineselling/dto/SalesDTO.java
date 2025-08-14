package br.com.eaugusto.onlineselling.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SalesDTO {

	@NotNull
	@Size(min = 2, max = 10)
	private String code;

	@NotNull
	private String clientId;

	@NotNull
	private Instant saleDate;
}
