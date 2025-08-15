package br.com.eaugusto.onlineselling.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a specific quantity of a {@link Product} and its total price.
 * <p>
 * Provides methods for adding or subtracting quantities while keeping the total
 * price updated accordingly.
 * </p>
 *
 * <p>
 * Used within {@link Sales} to track the products included in a sale.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductQuantity {

	@NotNull
	private Product product;

	@NotNull
	private Integer quantity;

	private BigDecimal totalPrice;

	public ProductQuantity() {
		this.quantity = 0;
		this.totalPrice = BigDecimal.ZERO;
	}

	public void addQuantity(Integer quantity) {
		this.quantity += quantity;
		BigDecimal newQuantity = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
		BigDecimal newTotalQuantity = this.totalPrice.add(newQuantity);
		this.totalPrice = newTotalQuantity;
	}

	public void subtractQuantity(Integer quantity) {
		this.quantity -= quantity;
		BigDecimal newQuantity = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
		this.totalPrice = this.totalPrice.subtract(newQuantity);
	}
}
