package cf.pivotal.quoteClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteController {

	private static final Logger LOG = Logger.getLogger(QuoteController.class);

	@Autowired
	QuoteRepository quoteRepository;

	public long countAllQuotes() {
		return quoteRepository.count();
	}

	public Quote findQuote(String id) {
		return quoteRepository.findQuote(id);
	}

	public List<Quote> findAllQuotes() {
		return quoteRepository.findAll();
	}

	public List<Quote> findQuoteEntries(int firstResult, int maxResults) {
		long allQuotesSize = countAllQuotes();

		if (firstResult < 0) {
			throw new IllegalArgumentException(
					"firstResult must be greater than -1");
		}

		if (firstResult >= allQuotesSize) {
			throw new IllegalArgumentException(
					"firstResult must be less than the size of all quotes");
		}

		if (maxResults < 1) {
			throw new IllegalArgumentException(
					"maxResults must be greater than 0");
		}

		if (maxResults >= allQuotesSize) {
			throw new IllegalArgumentException(
					"maxResults can't be greater than the size of all quotes");
		}

		if (maxResults - firstResult < 1) {
			throw new IllegalArgumentException(
					"maxResults must be greater than firstResult");
		}

		// enough already....
		List<Quote> all = quoteRepository.findAll();
		return all.subList(firstResult, maxResults);
	}

	public List<Quote> topGainers() {
		return quoteRepository.topGainers();
	}

	public List<Quote> topLosers() {
		return quoteRepository.topLosers();
	}

	public float indexAverage() {
		return quoteRepository.indexAverage();
	}

	public float openAverage() {
		return quoteRepository.openAverage();
	}

	public long volume() {
		return quoteRepository.volume();
	}

	public float change() {
		return quoteRepository.change();
	}

	public Quote findBySymbol(String symbol) {
		return quoteRepository.findBySymbol(symbol);
	}

	public List<Quote> findBySymbolIn(Set<String> symbols) {
		if (symbols == null || symbols.size() < 1) {
			return new ArrayList<Quote>();
		}

		if (symbols.size() == 1) {
			List<Quote> ret = new ArrayList<Quote>();
			ret.add(findBySymbol(symbols.toArray()[0].toString()));
			return ret;
		}

		return quoteRepository.findBySymbolIn(QuoteDecoder
				.formatSymbols(symbols));
	}

	public List<Quote> findRandomQuotes(Integer count) {
		return findAllQuotes().subList(0, count.intValue());
	}

	public Quote saveQuote(Quote quote) {
		LOG.info("save not supported for " + getClass());
		return quote;
	}

	public Map<String, Float> marketSummary() {
		return quoteRepository.marketSummary();
	}

	public void deleteQuote(Quote quote) {
		LOG.info("delete not supported for " + getClass());
	}
}
