package br.com.eaugusto.onlineselling.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eaugusto.onlineselling.dto.ProductDTO;
import br.com.eaugusto.onlineselling.usecase.RegisterProduct;
import br.com.eaugusto.onlineselling.usecase.SearchProduct;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@RestController
@RequestMapping(value = "/product")
public class ProductResource {

	private final SearchProduct searchProduct;
	private final RegisterProduct registerProduct;

	public ProductResource(SearchProduct searchProduct, RegisterProduct registerProduct) {
		this.searchProduct = searchProduct;
		this.registerProduct = registerProduct;
	}

	@GetMapping
	@Operation(summary = "Searches all Products")
	public ResponseEntity<Page<ProductDTO>> searchAllProducts(Pageable pageable) {
		return ResponseEntity.ok(searchProduct.searchAllProducts(pageable));
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Searches a Product by Id")
	public ResponseEntity<ProductDTO> searchById(@PathVariable String id) {
		return ResponseEntity.ok(searchProduct.searchById(id));
	}

	@GetMapping(value = "isRegistered/{id}")
	@Operation(summary = "Checks if a Product is registered by looking for its Id")
	public ResponseEntity<Boolean> isRegistered(@PathVariable String id) {
		return ResponseEntity.ok(searchProduct.isRegistered(id));
	}

	@PostMapping
	@Operation(summary = "Registers a Product")
	public ResponseEntity<ProductDTO> register(@RequestBody @Valid ProductDTO productDto) {
		return ResponseEntity.ok(registerProduct.register(productDto));
	}

	@GetMapping(value = "/code/{code}")
	@Operation(summary = "Searches a Product by Code")
	public ResponseEntity<ProductDTO> searchByCode(@PathVariable String code) {
		return ResponseEntity.ok(searchProduct.searchByCode(code));
	}

	@PutMapping
	@Operation(summary = "Updates a Product")
	public ResponseEntity<ProductDTO> update(@RequestBody @Valid ProductDTO productDto) {
		return ResponseEntity.ok(registerProduct.update(productDto));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Removes a Product by its Id")
	public ResponseEntity<String> remove(@PathVariable String id) {
		registerProduct.remove(id);
		return ResponseEntity.ok("Removed Successfully");
	}
}
