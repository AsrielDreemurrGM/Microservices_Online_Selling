package br.com.eaugusto.onlineselling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import br.com.eaugusto.onlineselling.domain.Product;
import br.com.eaugusto.onlineselling.domain.Sales;
import br.com.eaugusto.onlineselling.dto.SalesDTO;
import br.com.eaugusto.onlineselling.enums.Status;
import br.com.eaugusto.onlineselling.resources.SalesResources;
import br.com.eaugusto.onlineselling.usecases.RegisterSale;
import br.com.eaugusto.onlineselling.usecases.SearchSale;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 15, 2025
 */
class SalesResourcesTest {

	@InjectMocks
	private SalesResources salesResources;

	@Mock
	private SearchSale searchSale;

	@Mock
	private RegisterSale registerSale;

	private Sales createSale() {
		String id = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
		String code = "S" + ThreadLocalRandom.current().nextInt(1000, 9999);
		return Sales.builder().id(id).code(code).clientId("client-" + ThreadLocalRandom.current().nextInt(1, 100))
				.status(Status.STARTED).totalPrice(BigDecimal.ZERO).saleDate(Instant.now()).build();
	}

	private Product createProduct() {
		String code = "P" + ThreadLocalRandom.current().nextInt(1000, 9999);
		return Product.builder().id(String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000))).code(code)
				.name("Product " + code).description("Description for " + code).price(BigDecimal
						.valueOf(ThreadLocalRandom.current().nextDouble(10, 1000)).setScale(2, RoundingMode.HALF_UP))
				.build();
	}

	private void assertSaleEquals(Sales expected, Sales actual) {
		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getCode(), actual.getCode());
		assertEquals(expected.getClientId(), actual.getClientId());
		assertEquals(expected.getStatus(), actual.getStatus());
		assertEquals(expected.getTotalPrice(), actual.getTotalPrice());
		assertEquals(expected.getProductsSet().size(), actual.getProductsSet().size());
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void searchAllSales() {
		Sales sale1 = createSale();
		Sales sale2 = createSale();
		List<Sales> salesList = List.of(sale1, sale2);
		Page<Sales> page = new PageImpl<>(salesList);

		when(searchSale.searchAllSales(any(PageRequest.class))).thenReturn(page);

		ResponseEntity<Page<Sales>> response = salesResources.searchAllSales(PageRequest.of(0, 10));
		Page<Sales> body = response.getBody();

		assertNotNull(body);
		assertEquals(2, body.getContent().size());
		assertSaleEquals(sale1, body.getContent().get(0));
		assertSaleEquals(sale2, body.getContent().get(1));
	}

	@Test
	void searchSaleByCode() {
		Sales sale = createSale();
		when(searchSale.searchByCode(sale.getCode())).thenReturn(sale);

		ResponseEntity<Sales> response = salesResources.searchSaleByCode(sale.getCode());
		assertSaleEquals(sale, response.getBody());
	}

	@Test
	void registerSale() {
		SalesDTO dto = SalesDTO.builder().code("S1234").clientId("client-1").saleDate(Instant.now()).build();

		Sales savedSale = createSale();
		when(registerSale.registerSale(dto)).thenReturn(savedSale);

		ResponseEntity<Sales> response = salesResources.registerSale(dto);
		assertSaleEquals(savedSale, response.getBody());
	}

	@Test
	void finishSale() {
		Sales sale = createSale();
		when(registerSale.finishSale(sale.getId())).thenReturn(sale);

		ResponseEntity<Sales> response = salesResources.finishSale(sale.getId());
		assertSaleEquals(sale, response.getBody());
	}

	@Test
	void cancelSale() {
		Sales sale = createSale();
		when(registerSale.cancelSale(sale.getId())).thenReturn(sale);

		ResponseEntity<Sales> response = salesResources.cancelSale(sale.getId());
		assertSaleEquals(sale, response.getBody());
	}

	@Test
	void addProduct() {
		Sales sale = createSale();
		Product product = createProduct();

		sale.addProduct(product, 2);

		when(registerSale.addProduct(sale.getId(), product.getCode(), 2)).thenReturn(sale);

		ResponseEntity<Sales> response = salesResources.addProduct(sale.getId(), product.getCode(), 2);
		assertSaleEquals(sale, response.getBody());
	}

	@Test
	void removeProduct() {
		Sales sale = createSale();
		Product product = createProduct();

		sale.addProduct(product, 3);
		sale.removeProduct(product, 2);

		when(registerSale.removeProduct(sale.getId(), product.getCode(), 2)).thenReturn(sale);

		ResponseEntity<Sales> response = salesResources.removeProduct(sale.getId(), product.getCode(), 2);
		assertSaleEquals(sale, response.getBody());
	}

	@Test
	void removeAllProductsClearsSale() {
		Sales sale = new Sales();
		Product product = new Product("1", "P001", "Name", "Desc", BigDecimal.TEN);
		sale.addProduct(product, 2);
		assertFalse(sale.getProductsSet().isEmpty());

		sale.removeAllProducts();

		assertTrue(sale.getProductsSet().isEmpty());
		assertEquals(BigDecimal.ZERO, sale.getTotalPrice());
	}

	@Test
	void totalProductQuantityReturnsCorrectSum() {
		Sales sale = new Sales();
		Product product1 = new Product("1", "P001", "Name1", "Desc", BigDecimal.TEN);
		Product product2 = new Product("2", "P002", "Name2", "Desc", BigDecimal.TEN);

		sale.addProduct(product1, 2);
		sale.addProduct(product2, 3);

		assertEquals(5, sale.getTotalProductQuantity());
	}

	@Test
	void cannotModifyFinishedSale() {
		Sales sale = createSale();
		sale.setStatus(Status.FINISHED);
		Product product = createProduct();

		assertThrows(UnsupportedOperationException.class, () -> sale.addProduct(product, 1));
		assertThrows(UnsupportedOperationException.class, () -> sale.removeProduct(product, 1));
	}
}
