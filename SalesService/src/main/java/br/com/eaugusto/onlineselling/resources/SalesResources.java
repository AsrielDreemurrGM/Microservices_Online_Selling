package br.com.eaugusto.onlineselling.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eaugusto.onlineselling.domain.Sales;
import br.com.eaugusto.onlineselling.dto.SalesDTO;
import br.com.eaugusto.onlineselling.usecases.RegisterSale;
import br.com.eaugusto.onlineselling.usecases.SearchSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@RestController
@RequestMapping("/sales")
public class SalesResources {

	private final SearchSale searchSale;
	private final RegisterSale registerSale;

	public SalesResources(SearchSale searchSale, RegisterSale registerSale) {
		this.searchSale = searchSale;
		this.registerSale = registerSale;
	}

	@GetMapping
	@Operation(summary = "Lists all registered Sales")
	public ResponseEntity<Page<Sales>> searchAllSales(Pageable pageable) {
		return ResponseEntity.ok(searchSale.searchAllSales(pageable));
	}

	@PostMapping
	@Operation(summary = "Registers a new Sale", description = "Registers a Sale. Example: {\"code\":\"S001\", \"clientId\":\"1234\", \"saleDate\":\"2025-08-14T10:00:00Z\"}")
	@ApiResponse(responseCode = "200", description = "Sale registered successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "{\"id\":\"abc123\", \"code\":\"S001\", \"clientId\":\"1234\", \"status\":\"STARTED\", \"totalPrice\":0.0}")))
	@ApiResponse(responseCode = "400", description = "Invalid request data")
	@ApiResponse(responseCode = "404", description = "Client not found")
	public ResponseEntity<Sales> registerSale(@RequestBody @Valid SalesDTO saleDTO) {
		return ResponseEntity.ok(registerSale.registerSale(saleDTO));
	}

	@PutMapping("/{saleId}/finish")
	@Operation(summary = "Finish a Sale")
	public ResponseEntity<Sales> finishSale(@PathVariable String saleId) {
		return ResponseEntity.ok(registerSale.finishSale(saleId));
	}

	@PutMapping("/{saleId}/cancel")
	@Operation(summary = "Cancel a Sale")
	public ResponseEntity<Sales> cancelSale(@PathVariable String saleId) {
		return ResponseEntity.ok(registerSale.cancelSale(saleId));
	}

	@PutMapping("/{saleId}/products/{productCode}/add/{quantity}")
	@Operation(summary = "Add a Product to a Sale")
	public ResponseEntity<Sales> addProduct(@PathVariable String saleId, @PathVariable String productCode,
			@PathVariable Integer quantity) {
		return ResponseEntity.ok(registerSale.addProduct(saleId, productCode, quantity));
	}

	@PutMapping("/{saleId}/products/{productCode}/remove/{quantity}")
	@Operation(summary = "Remove a Product from a Sale")
	public ResponseEntity<Sales> removeProduct(@PathVariable String saleId, @PathVariable String productCode,
			@PathVariable Integer quantity) {
		return ResponseEntity.ok(registerSale.removeProduct(saleId, productCode, quantity));
	}
}
