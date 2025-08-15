package br.com.eaugusto.onlineselling.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Sales;
import br.com.eaugusto.onlineselling.exception.EntityNotFoundException;
import br.com.eaugusto.onlineselling.repository.ISalesRepository;

/**
 * Service responsible for searching sales.
 * <p>
 * Provides operations to find sales by code and to list all sales in a
 * paginated format.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Service
public class SearchSale {

	private ISalesRepository salesRepository;

	public SearchSale(ISalesRepository salesRepository) {
		this.salesRepository = salesRepository;
	}

	/**
	 * Retrieves all sales with pagination.
	 *
	 * @param pageable pagination information
	 * @return a page of sales
	 */
	public Page<Sales> searchAllSales(Pageable pageable) {
		return salesRepository.findAll(pageable);
	}

	/**
	 * Finds a sale by its unique code.
	 *
	 * @param salesCode the sales code
	 * @return the matching sale
	 * @throws EntityNotFoundException if no sale is found
	 */
	public Sales searchByCode(String salesCode) {
		return salesRepository.searchByCode(salesCode)
				.orElseThrow(() -> new EntityNotFoundException(Sales.class, "code", salesCode));
	}
}
