package cf.pivotal.quoteClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.Feign;
import feign.jackson.JacksonDecoder;

@RestController
@RequestMapping("/quoteService")
public class QuoteController {

	@Autowired
	DiscoveryClient discoveryClient;

	@HystrixCommand(fallbackMethod = "fallBackAllQuotes")
	@RequestMapping("/findAll")
	public Iterable<Quote> findAllQuotes() {
		return quoteRepository().findAll();
	}

	@HystrixCommand(fallbackMethod = "fallBackBySymbol")
	@RequestMapping("/findBySymbol/{symbol}")
	public Quote findBySymbol(@PathVariable String symbol) {
		return quoteRepository().findBySymbol(symbol);
	}

	public Quote fallBackBySymbol(String symbol) {
		return fakeQuote(0);
	}

	public List<Quote> fallBackAllQuotes() {
		return fakeQuotes(42);
	}

	private Quote fakeQuote(Integer id) {
		Quote q = new Quote();
		q.setQuoteid(id);
		q.setChange1(new BigDecimal("0"));
		q.setCompanyname("Foo" + id);
		q.setHigh(new BigDecimal("0"));
		q.setLow(new BigDecimal("0"));
		q.setOpen1(new BigDecimal("0"));
		q.setPrice(new BigDecimal("0"));
		q.setSymbol(q.getCompanyname());
		q.setVolume(new BigDecimal("0"));

		return q;
	}

	private List<Quote> fakeQuotes(long size) {
		List<Quote> q = new ArrayList<Quote>();
		for (int i = 0; i < size; i++) {
			q.add(fakeQuote(i));
		}
		return q;
	}

	private QuoteRepository quoteRepository() {
		InstanceInfo i = discoveryClient.getNextServerFromEureka(
				"quote-service", false);
		return createRepository(i.getHomePageUrl() + "quoteService");
	}

	QuoteRepository createRepository(String url) {
		ObjectMapper mapper = new ObjectMapper().configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new Jackson2HalModule());

		return Feign.builder().decoder(new JacksonDecoder(mapper))
				.target(QuoteRepository.class, url);
	}
}
