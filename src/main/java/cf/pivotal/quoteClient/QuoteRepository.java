package cf.pivotal.quoteClient;

import java.util.List;

import org.springframework.stereotype.Repository;

import feign.Param;
import feign.RequestLine;

@Repository
public interface QuoteRepository {

	@RequestLine("GET /{symbol}")
	public Quote findBySymbol(@Param(value = "symbol") String symbol);

	@RequestLine("GET /marketSummary")
	MarketSummary marketSummary();
}
