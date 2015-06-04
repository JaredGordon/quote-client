package cf.pivotal.quoteClient;

import java.util.List;

import javax.inject.Named;

import org.springframework.stereotype.Repository;

import feign.RequestLine;

@Repository
public interface QuoteRepository {

	@RequestLine("GET /findBySymbol/{symbol}")
	public Quote findBySymbol(@Named("symbol") String symbol);

	@RequestLine("GET /findById/{id}")
	public Quote findQuote(@Named("id") Integer id);

	@RequestLine("GET /findAll")
	public List<Quote> findAll();

}
