package br.com.eaugusto.onlineselling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Client.
 * <p>
 * This class mirrors the fields of the
 * {@link br.com.eaugusto.onlineselling.domain.Client} but is used for
 * transferring data between API layers, ensuring validation and avoiding direct
 * exposure of the domain entity.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ClientDTO", description = "Data Transfer Object for Client")
public class ClientDTO {

	/** Unique identifier. */
	@Schema(description = "Unique Identifier")
	private String id;

	/** Full name of the client. */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Name", minLength = 1, maxLength = 50, nullable = false)
	private String name;

	/** CPF of the client (Brazilian ID). */
	@NotNull
	@Schema(description = "CPF", nullable = false)
	private String cpf;

	/** Client's telephone number. */
	@NotNull
	@Schema(description = "Telephone Number", nullable = false)
	private String telephoneNumber;

	/** Email address. */
	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = ".+@.+\\..+", message = "Invalid Email")
	@Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
	private String email;

	/** Address (street name). */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Address", minLength = 1, maxLength = 50, nullable = false)
	private String address;

	/** Address number. */
	@NotNull
	@Schema(description = "Address Number", nullable = false)
	private Integer addressNumber;

	/** City of residence. */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "City", minLength = 1, maxLength = 50, nullable = false)
	private String city;

	/** State of residence. */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "State", minLength = 1, maxLength = 50, nullable = false)
	private String state;
}
