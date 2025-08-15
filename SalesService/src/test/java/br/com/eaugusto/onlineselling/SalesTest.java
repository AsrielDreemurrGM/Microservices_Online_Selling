package br.com.eaugusto.onlineselling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.onlineselling.domain.Product;
import br.com.eaugusto.onlineselling.domain.ProductQuantity;
import br.com.eaugusto.onlineselling.domain.Sales;
import br.com.eaugusto.onlineselling.enums.Status;

/**
 * Unit tests for the {@link Sales} and {@link ProductQuantity} domain classes.
 * <p>
 * Ensures correct calculation of quantities and total prices when adding or
 * removing products, as well as enforcing sale modification rules for finished
 * or canceled sales.
 * <p>
 * Also verifies behavior of:
 * <ul>
 * <li>Incrementing and decrementing product quantities</li>
 * <li>Clearing all products from a sale</li>
 * <li>Default state of {@link ProductQuantity}</li>
 * </ul>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 15, 2025
 */
class SalesTest {

	private Product createProduct(String code, BigDecimal price) {
		return Product.builder().id("1").code(code).name("Product " + code).description("Description").price(price)
				.build();
	}

	@Test
	void addProductNewAndExisting() {
		Sales sale = Sales.builder().code("S001").clientId("C001").status(Status.STARTED).saleDate(Instant.now())
				.build();

		Product product = createProduct("P001", BigDecimal.TEN);

		sale.addProduct(product, 2);
		assertEquals(1, sale.getProductsSet().size());
		assertEquals(2, sale.getTotalProductQuantity());
		assertEquals(BigDecimal.valueOf(20), sale.getTotalPrice());

		sale.addProduct(product, 3);
		assertEquals(1, sale.getProductsSet().size());
		assertEquals(5, sale.getTotalProductQuantity());
		assertEquals(BigDecimal.valueOf(50), sale.getTotalPrice());
	}

	@Test
	void removeProductQuantityGreaterAndLess() {
		Sales sale = Sales.builder().code("S002").clientId("C002").status(Status.STARTED).saleDate(Instant.now())
				.build();

		Product product = createProduct("P001", BigDecimal.TEN);
		sale.addProduct(product, 5);

		sale.removeProduct(product, 2);
		assertEquals(3, sale.getTotalProductQuantity());
		assertEquals(BigDecimal.valueOf(30), sale.getTotalPrice());

		sale.removeProduct(product, 3);
		assertEquals(0, sale.getTotalProductQuantity());
		assertEquals(BigDecimal.ZERO, sale.getTotalPrice());
		assertTrue(sale.getProductsSet().isEmpty());
	}

	@Test
	void removeAllProductsClearsSale() {
		Sales sale = Sales.builder().code("S003").clientId("C003").status(Status.STARTED).saleDate(Instant.now())
				.build();

		Product product1 = createProduct("P001", BigDecimal.TEN);
		Product product2 = createProduct("P002", BigDecimal.valueOf(20));

		sale.addProduct(product1, 1);
		sale.addProduct(product2, 2);

		assertFalse(sale.getProductsSet().isEmpty());

		sale.removeAllProducts();
		assertTrue(sale.getProductsSet().isEmpty());
		assertEquals(BigDecimal.ZERO, sale.getTotalPrice());
	}

	@Test
	void totalProductQuantityCalculatesCorrectly() {
		Sales sale = Sales.builder().code("S004").clientId("C004").status(Status.STARTED).saleDate(Instant.now())
				.build();

		Product product1 = createProduct("P001", BigDecimal.TEN);
		Product product2 = createProduct("P002", BigDecimal.valueOf(20));

		sale.addProduct(product1, 2);
		sale.addProduct(product2, 3);

		assertEquals(5, sale.getTotalProductQuantity());
	}

	@Test
	void cannotModifyFinishedOrCanceledSale() {
		Sales sale = Sales.builder().code("S005").clientId("C005").status(Status.FINISHED).saleDate(Instant.now())
				.build();

		Product product = createProduct("P001", BigDecimal.TEN);

		assertThrows(UnsupportedOperationException.class, () -> sale.addProduct(product, 1));
		assertThrows(UnsupportedOperationException.class, () -> sale.removeProduct(product, 1));

		sale.setStatus(Status.CANCELED);
		assertThrows(UnsupportedOperationException.class, () -> sale.addProduct(product, 1));
	}

	@Test
	void productQuantityAddAndSubtract() {
		Product product = createProduct("P001", BigDecimal.TEN);
		ProductQuantity productQuantity = new ProductQuantity(product, 0, BigDecimal.ZERO);

		productQuantity.addQuantity(3);
		assertEquals(3, productQuantity.getQuantity());
		assertEquals(BigDecimal.valueOf(30), productQuantity.getTotalPrice());

		productQuantity.subtractQuantity(1);
		assertEquals(2, productQuantity.getQuantity());
		assertEquals(BigDecimal.valueOf(20), productQuantity.getTotalPrice());
	}

	@Test
	void defaultConstructorProductQuantity() {
		ProductQuantity productQuantity = new ProductQuantity();
		assertEquals(0, productQuantity.getQuantity());
		assertEquals(BigDecimal.ZERO, productQuantity.getTotalPrice());
	}
}
