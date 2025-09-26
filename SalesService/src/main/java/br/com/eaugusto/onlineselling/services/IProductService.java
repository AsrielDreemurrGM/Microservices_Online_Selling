package br.com.eaugusto.onlineselling.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eaugusto.onlineselling.domain.Product;

/**
 * Feign client interface for communicating with the product service. Provides a
 * method to retrieve a Product by its code.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@FeignClient(name = "product-service", url = "${application.productService.url}")
public interface IProductService {

	@GetMapping(value = "/product/code/{code}", produces = "application/json", headers = "application/json")
	Product findProductByCode(@RequestParam("code") String productCode);
}
