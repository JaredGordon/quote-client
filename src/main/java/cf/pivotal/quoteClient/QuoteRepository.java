package cf.pivotal.quoteClient;

import java.util.List;

import org.springframework.stereotype.Repository;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Repository
public interface QuoteRepository {

	@RequestLine("GET /{symbol}")
	public Quote findBySymbol(@Param(value = "symbol") String symbol);

	@RequestLine("GET /")
	public List<Quote> findAll();

	@RequestLine("GET /marketSummary")
	MarketSummary marketSummary();

	@RequestLine("GET /topGainers")
	List<Quote> topGainers();

	@RequestLine("GET /topLosers")
	List<Quote> topLosers();

	@RequestLine("GET /count")
	long count();

	@RequestLine("POST /save")
	@Headers("Content-Type: application/json")
	public Quote save(Quote quote);

	@RequestLine("POST /delete")
	@Headers("Content-Type: application/json")
	public void delete(Quote quote);
}
