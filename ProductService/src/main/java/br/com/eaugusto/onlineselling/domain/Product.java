package br.com.eaugusto.onlineselling.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eaugusto.onlineselling.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domain class representing a Product entity in the system.
 * <p>
 * This class maps to the "product" collection in MongoDB and contains
 * validation constraints and Swagger documentation.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 12, 2025
 */
@Document(collection = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	/** Unique identifier. */
	@Id
	@Schema(description = "Unique Identifier")
	private String id;

	/** Unique Product Code. */
	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	@Schema(description = "Unique Product Code", nullable = false)
	private String code;

	/** Product Name */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Product Name", nullable = false)
	private String name;

	/** Product Description */
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Product Description", nullable = false)
	private String description;

	/** Product Price */
	@NotNull
	@Schema(description = "Product Price", nullable = false)
	private BigDecimal price;

	/** Product Status */
	@NotNull
	@Schema(description = "Product Status", nullable = false)
	private Status status;
}
