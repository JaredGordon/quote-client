package cf.pivotal.quoteClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("quoteService")
public class QuoteController {

	@Autowired
	QuoteRepository quoteRepository;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Iterable<Quote> findAllQuotes() {
		return quoteRepository.findAll();
	}

	@RequestMapping(value = "/findBySymbol/{symbol}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Quote findBySymbol(@PathVariable String symbol) {
		return quoteRepository.findBySymbol(symbol);
	}
}
