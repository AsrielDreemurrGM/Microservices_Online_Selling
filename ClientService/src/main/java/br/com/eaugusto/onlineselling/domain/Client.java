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

	@Id
	@Schema(description = "Unique Identifier")
	private String id;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Name", minLength = 1, maxLength = 50, nullable = false)
	private String name;

	@NotNull
	@Indexed(unique = true, background = true)
	@Schema(description = "CPF", nullable = false)
	private String cpf;

	@NotNull
	@Schema(description = "Telephone Number", nullable = false)
	private String telephoneNumber;

	@NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Invalid Email")
	private String email;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Address", minLength = 1, maxLength = 50, nullable = false)
	private String address;

	@NotNull
	@Schema(description = "Address Number", nullable = false)
	private Integer addressNumber;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "City", minLength = 1, maxLength = 50, nullable = false)
	private String city;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "State", minLength = 1, maxLength = 50, nullable = false)
	private String state;
}
