package br.com.eaugusto.onlineselling.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
 * Domain class representing a Client entity in the system.
 * <p>
 * This class maps to the "client" collection in MongoDB and contains validation
 * constraints and Swagger documentation.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Document(collection = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Client", description = "Client Domain Class")
public class Client {

	/** Unique identifier. */
	@Id
	@Schema(description = "Unique Identifier")
	private String id;

	/** Full name of the client. */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Name", minLength = 1, maxLength = 50, nullable = false)
	private String name;

	/** CPF of the client (Brazilian ID). */
	@NotNull
	@Indexed(unique = true, background = true)
	@Schema(description = "CPF", nullable = false)
	private String cpf;

	/** Client's telephone number. */
	@NotNull
	@Schema(description = "Telephone Number", nullable = false)
	private String telephoneNumber;

	/** Email address. */
	@NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Invalid Email")
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
