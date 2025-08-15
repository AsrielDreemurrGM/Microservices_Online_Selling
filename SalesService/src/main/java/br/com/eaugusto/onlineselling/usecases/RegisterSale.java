package br.com.eaugusto.onlineselling.usecases;

import java.math.BigDecimal;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Product;
import br.com.eaugusto.onlineselling.domain.Sales;
import br.com.eaugusto.onlineselling.dto.SalesDTO;
import br.com.eaugusto.onlineselling.enums.Status;
import br.com.eaugusto.onlineselling.exception.EntityNotFoundException;
import br.com.eaugusto.onlineselling.repository.ISalesRepository;
import br.com.eaugusto.onlineselling.services.ClientService;
import br.com.eaugusto.onlineselling.services.IProductService;
import jakarta.validation.Valid;

/**
 * Service responsible for registering and updating sales.
 * <p>
 * This class provides business logic for creating, modifying, finishing,
 * canceling, and managing products within sales.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Service
public class RegisterSale {

	private ISalesRepository salesRepository;

	private IProductService productService;

	private ClientService clientService;

	public RegisterSale(ISalesRepository salesRepository, IProductService productService, ClientService clientService) {
		this.salesRepository = salesRepository;
		this.productService = productService;
		this.clientService = clientService;
	}

	/**
	 * Registers a new sale.
	 *
	 * @param salesDTO the sale data transfer object
	 * @return the created sale
	 */
	public Sales registerSale(@Valid SalesDTO salesDTO) {
		Sales sale = convertToDomain(salesDTO, Status.STARTED);
		validateIfClientIsRegistered(sale.getClientId());
		sale.recalculateTotalSalesPrice();
		return this.salesRepository.insert(sale);
	}

	private void validateIfClientIsRegistered(String clientId) {
		boolean isRegistered = this.clientService.isClientRegistered(clientId);
		if (!isRegistered) {
			throw new EntityNotFoundException(Sales.class, "clientId", clientId);
		}
	}

	private Sales convertToDomain(@Valid SalesDTO salesDTO, Status status) {
		return Sales.builder().clientId(salesDTO.getClientId()).code(salesDTO.getCode())
				.saleDate(salesDTO.getSaleDate()).status(status).totalPrice(BigDecimal.ZERO)
				.productsSet(new HashSet<>()).build();
	}

	/**
	 * Updates an existing sale.
	 *
	 * @param sale the sale entity
	 * @return the updated sale
	 */
	public Sales updateSale(@Valid Sales sale) {
		return this.salesRepository.save(sale);
	}

	/**
	 * Marks a sale as finished.
	 *
	 * @param id the sale ID
	 * @return the updated sale
	 */
	public Sales finishSale(String id) {
		Sales sale = findSaleById(id);
		sale.validateSaleStatus();
		sale.setStatus(Status.FINISHED);
		return this.salesRepository.save(sale);
	}

	/**
	 * Cancels a sale.
	 *
	 * @param saleId the sale ID
	 * @return the updated sale
	 */
	public Sales cancelSale(String saleId) {
		Sales sale = findSaleById(saleId);
		sale.validateSaleStatus();
		sale.setStatus(Status.CANCELED);
		return this.salesRepository.save(sale);
	}

	/**
	 * Adds a product to a sale.
	 *
	 * @param saleId      the sale ID
	 * @param productCode the product code
	 * @param quantity    the quantity to add
	 * @return the updated sale
	 */
	public Sales addProduct(String saleId, String productCode, Integer quantity) {
		Sales sale = findSaleById(saleId);
		Product product = findProductByCode(productCode);
		sale.validateSaleStatus();
		sale.addProduct(product, quantity);
		return this.salesRepository.save(sale);
	}

	/**
	 * Removes a product from a sale.
	 *
	 * @param saleId      the sale ID
	 * @param productCode the product code
	 * @param quantity    the quantity to remove
	 * @return the updated sale
	 */
	public Sales removeProduct(String saleId, String productCode, Integer quantity) {
		Sales sale = findSaleById(saleId);
		Product product = findProductByCode(productCode);
		sale.validateSaleStatus();
		sale.removeProduct(product, quantity);
		return this.salesRepository.save(sale);
	}

	private Sales findSaleById(String saleId) {
		return salesRepository.findById(saleId)
				.orElseThrow(() -> new EntityNotFoundException(Sales.class, "id", saleId));
	}

	private Product findProductByCode(String productCode) {
		Product product = productService.findProductByCode(productCode);
		if (product == null) {
			throw new EntityNotFoundException(Product.class, "code", productCode);
		}
		return product;
	}
}
