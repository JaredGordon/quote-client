package cf.pivotal.quoteClient;

import java.util.List;

import org.springframework.stereotype.Repository;

import feign.Param;
import feign.RequestLine;

@Repository
public interface QuoteRepository {

	@RequestLine("GET /findBySymbol/{symbol}")
	public Quote findBySymbol(@Param(value="symbol") String symbol);

	@RequestLine("GET /findById/{id}")
	public Quote findQuote(@Param(value="id") Integer id);

	@RequestLine("GET /findAll")
	public List<Quote> findAll();

}
