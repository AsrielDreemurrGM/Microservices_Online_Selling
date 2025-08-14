package br.com.eaugusto.onlineselling.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eaugusto.onlineselling.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Document(collection = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sales {

	@Id
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String code;

	@NotNull
	private String clientId;

	@Builder.Default
	private Set<ProductQuantity> productsSet = new HashSet<>();

	private BigDecimal totalPrice;

	@NotNull
	private Instant saleDate;

	@NotNull
	private Status status;

	public void addProduct(Product productToBeAdded, Integer quantity) {
		validateStatus();
		Optional<ProductQuantity> existingProductQuantity = findProductQuantityByCode(productToBeAdded.getCode());
		if (existingProductQuantity.isPresent()) {
			ProductQuantity productQuantity = existingProductQuantity.get();
			productQuantity.addQuantity(quantity);
		} else {
			ProductQuantity product = ProductQuantity.builder().product(productToBeAdded).totalPrice(BigDecimal.ZERO)
					.quantity(0).build();
			product.addQuantity(quantity);
			productsSet.add(product);
		}
		recalculateTotalSalesPrice();
	}

	public void validateStatus() {
		if (this.status == Status.FINISHED || this.status == Status.CANCELED) {
			throw new UnsupportedOperationException("Impossible to modify a FINISHED or CANCELED sale");
		}
	}

	public void removeProduct(Product productToBeRemoved, Integer quantity) {
		validateStatus();
		Optional<ProductQuantity> existingProductQuantity = findProductQuantityByCode(productToBeRemoved.getCode());

		if (existingProductQuantity.isPresent()) {
			ProductQuantity productQuantity = existingProductQuantity.get();
			if (productQuantity.getQuantity() > quantity) {
				productQuantity.subtractQuantity(quantity);
				recalculateTotalSalesPrice();
			} else {
				productsSet.remove(existingProductQuantity.get());
				recalculateTotalSalesPrice();
			}
		}
	}

	public void removeAllProducts() {
		validateStatus();
		productsSet.clear();
		totalPrice = BigDecimal.ZERO;
	}

	public Integer getTotalProductQuantity() {
		return productsSet.stream().reduce(0,
				(partialCountResult, product) -> partialCountResult + product.getQuantity(), Integer::sum);
	}

	public void recalculateTotalSalesPrice() {
		BigDecimal recalculatedTotalPrice = BigDecimal.ZERO;
		for (ProductQuantity eachProduct : this.productsSet) {
			recalculatedTotalPrice = recalculatedTotalPrice.add(eachProduct.getTotalPrice());
		}
		this.totalPrice = recalculatedTotalPrice;
	}

	private Optional<ProductQuantity> findProductQuantityByCode(String code) {
		return productsSet.stream().filter(productQuantity -> productQuantity.getProduct().getCode().equals(code))
				.findAny();
	}
}
