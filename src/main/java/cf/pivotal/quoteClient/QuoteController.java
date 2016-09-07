package cf.pivotal.quoteClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class QuoteController implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public Quote findBySymbol(String symbol) {
        return quoteRepository.findBySymbol(symbol);
    }

    public MarketSummary marketSummary() {
        return quoteRepository.marketSummary();
    }
}
