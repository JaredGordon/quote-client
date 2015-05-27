package cf.pivotal.quoteClient;

import java.util.List;

import javax.inject.Named;

import org.springframework.stereotype.Repository;

import feign.RequestLine;

@Repository
public interface QuoteRepository {
   
	
//	@RequestLine("GET /cities?page={page}&size={size}")
//    public PagedCities findAll(@Named("page") Integer page, @Named("size") Integer size);
//
//    @RequestLine("GET /cities/search/nameContains?q={name}&page={page}&size={size}")
//    public PagedCities findByNameContains(@Named("name") String name, @Named("page") Integer page, @Named("size") Integer size);


    @RequestLine("GET /findBySymbol/{symbol}")
    public Quote findBySymbol(@Named("symbol") String symbol);

    @RequestLine("GET /findById/{id}")
	public Quote findQuote(@Named("id") Integer id);
    
//    @RequestLine("GET /findById/{id}")
//	public List<Quote> findBySymbolIn(Set<String> symbols) {
//		Quote[] quotes = restTemplate.getForObject(serviceURI
//				+ "/findBySymbolIn?symbols=" + setToQueryList(symbols),
//				Quote[].class);
//		return new ArrayList<Quote>(Arrays.asList(quotes));
//
//	}

    @RequestLine("GET /findAll")
	public List<Quote> findAll();

//
//	public List<Quote> findQuoteEntries(int from, int to) {
//		Quote[] quotes = restTemplate.getForObject(serviceURI
//				+ "/findAllPaged?page=" + from + "&size=" + to, Quote[].class);
//		return new ArrayList<Quote>(Arrays.asList(quotes));
//	}
//
//	public long countAllQuotes() {
//		return restTemplate.getForObject(serviceURI + "/count", Integer.class);
//	}
//
//	public Map<String, Long> marketSummary() {
//		return restTemplate.exchange(serviceURI + "/marketSummary",
//				HttpMethod.GET, null,
//				new ParameterizedTypeReference<Map<String, Long>>() {
//				}).getBody();
//	}
//
//	private String setToQueryList(Set<String> set) {
//		String[] s = set.toArray(new String[] {});
//		String result = "";
//		for (int i = 0; i < set.size(); i++) {
//			result += s[i];
//			if (i < set.size() - 1) {
//				result += ",";
//			}
//		}
//		return result;
//	}
//



}
