package cf.pivotal.quoteClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteController implements QuoteService {

	@Autowired
	QuoteRepository quoteRepository;

	public long countAllQuotes() {
		return quoteRepository.count();
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

		return ms;
	}

	public void deleteQuote(Quote quote) {
		quoteRepository.delete(quote);
	}

	@Override
	public Quote saveQuote(Quote quote) {
		return quoteRepository.save(quote);
	}
}
