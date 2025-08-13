package br.com.eaugusto.onlineselling.dto;

import java.math.BigDecimal;

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
 * Data Transfer Object (DTO) for Product.
 * <p>
 * This class mirrors the fields of the
 * {@link br.com.eaugusto.onlineselling.domain.Product} but is used for
 * transferring data between API layers, ensuring validation and avoiding direct
 * exposure of the domain entity.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 12, 2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ProductDTO", description = "Data Transfer Object for Product")
public class ProductDTO {

	/** Unique identifier. */
	@Schema(description = "Unique Identifier")
	private String id;

	/** Unique Product Code. */
	@NotNull
	@Size(min = 2, max = 10)
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
