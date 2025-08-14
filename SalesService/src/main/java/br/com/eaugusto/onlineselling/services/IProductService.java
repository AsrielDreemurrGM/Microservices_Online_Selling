package br.com.eaugusto.onlineselling.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eaugusto.onlineselling.domain.Product;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@FeignClient(name = "product", url = "${application.productService.searchProductEndpoint}")
public interface IProductService {

	@GetMapping(value = "/{code}", produces = "application/json", headers = "application/json")
	Product findProduct(@RequestParam("code") String productCode);
}
