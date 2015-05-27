package cf.pivotal.quoteClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quoteService")
public class QuoteController {

	@Autowired
	QuoteRepository quoteRepository;


	@RequestMapping("/findAll")
	public Iterable<Quote> findAllQuotes() {
		return quoteRepository.findAll();
	}

	@RequestMapping("/findBySymbol/{symbol}")
	public Quote findBySymbol(@PathVariable String symbol) {
		return quoteRepository.findBySymbol(symbol);
	}
}
