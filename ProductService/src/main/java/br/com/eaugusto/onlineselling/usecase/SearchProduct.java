package br.com.eaugusto.onlineselling.usecase;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Product;
import br.com.eaugusto.onlineselling.dto.ProductDTO;
import br.com.eaugusto.onlineselling.exception.EntityNotFoundException;
import br.com.eaugusto.onlineselling.repository.IProductRepository;

@Service
public class SearchProduct {

	private final IProductRepository productRepository;

	public SearchProduct(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Page<ProductDTO> searchAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable).map(this::toDto);
	}

	public ProductDTO searchById(String id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id));
		return toDto(product);
	}

	public Boolean isRegistered(String id) {
		Optional<Product> product = productRepository.findById(id);
		return product.isPresent();
	}

	public ProductDTO searchByCode(String code) {
		Product product = productRepository.searchByCode(code)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "code", String.valueOf(code)));
		return toDto(product);
	}

	private ProductDTO toDto(Product product) {
		return ProductDTO.builder().id(product.getId()).code(product.getCode()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).status(product.getStatus()).build();
	}
}
