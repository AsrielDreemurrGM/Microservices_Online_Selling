package br.com.eaugusto.onlineselling.usecase;

import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Product;
import br.com.eaugusto.onlineselling.dto.ProductDTO;
import br.com.eaugusto.onlineselling.repository.IProductRepository;
import jakarta.validation.Valid;

/**
 * Use case service for registering, updating, and removing products.
 * <p>
 * Handles mapping between {@link ProductDTO} and {@link Product} entities and
 * delegates persistence operations to {@link IProductRepository}.
 * </p>
 *
 * <p>
 * Primary responsibilities:
 * </p>
 * <ul>
 * <li>Insert new products into the database.</li>
 * <li>Update existing product records.</li>
 * <li>Delete products by their ID.</li>
 * </ul>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Service
public class RegisterProduct {

	private final IProductRepository productRepository;

	public RegisterProduct(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductDTO register(@Valid ProductDTO productDto) {
		Product product = toEntity(productDto);
		Product saved = productRepository.insert(product);
		return toDto(saved);
	}

	public ProductDTO update(@Valid ProductDTO productDto) {
		Product product = toEntity(productDto);
		Product updated = productRepository.save(product);
		return toDto(updated);
	}

	public void remove(String id) {
		productRepository.deleteById(id);
	}

	private Product toEntity(ProductDTO dto) {
		return Product.builder().id(dto.getId()).code(dto.getCode()).name(dto.getName())
				.description(dto.getDescription()).price(dto.getPrice()).status(dto.getStatus()).build();
	}

	private ProductDTO toDto(Product product) {
		return ProductDTO.builder().id(product.getId()).code(product.getCode()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).status(product.getStatus()).build();
	}
}
