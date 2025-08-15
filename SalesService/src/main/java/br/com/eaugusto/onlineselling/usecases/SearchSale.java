package br.com.eaugusto.onlineselling.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Sales;
import br.com.eaugusto.onlineselling.exception.EntityNotFoundException;
import br.com.eaugusto.onlineselling.repository.ISalesRepository;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Service
public class SearchSale {

	private ISalesRepository salesRepository;

	public SearchSale(ISalesRepository salesRepository) {
		this.salesRepository = salesRepository;
	}

	public Page<Sales> searchAllSales(Pageable pageable) {
		return salesRepository.findAll(pageable);
	}

	public Sales searchByCode(String salesCode) {
		return salesRepository.searchByCode(salesCode)
				.orElseThrow(() -> new EntityNotFoundException(Sales.class, "code", salesCode));
	}
}
