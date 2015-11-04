package cf.pivotal.quoteClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteController implements QuoteService {

	private static final Logger LOG = Logger.getLogger(QuoteController.class);

	@Autowired
	QuoteRepository quoteRepository;

	public long countAllQuotes() {
		return findAllQuotes().size();
	}

	public List<Quote> findAllQuotes() {
		return quoteRepository.findAll();
	}

	public List<Quote> topGainers() {
		return quoteRepository.topGainers();
	}

	public List<Quote> topLosers() {
		return quoteRepository.topLosers();
	}

	public Quote findBySymbol(String symbol) {
		return quoteRepository.findBySymbol(symbol);
	}

	public List<Quote> findBySymbolIn(Set<String> symbols) {
		ArrayList<Quote> ret = new ArrayList<Quote>();

		if (symbols == null || symbols.size() < 1) {
			return ret;
		}

		List<Quote> all = findAllQuotes();
		for (Quote q : all) {
			if (symbols.contains(q.getSymbol())) {
				ret.add(q);
			}
		}

		return ret;
	}

	public MarketSummary marketSummary() {
		MarketSummary ms = quoteRepository.marketSummary();
		ms.setTopGainers(topGainers());
		ms.setTopLosers(topLosers());
		ms.setSummaryDate(new Date());
		return ms;
	}

	public void deleteQuote(Quote quote) {
		LOG.info("delete not supported for " + getClass());
	}

	@Override
	public Quote saveQuote(Quote quote) {
		LOG.info("save not supported for " + getClass());
		return quote;
	}
}
