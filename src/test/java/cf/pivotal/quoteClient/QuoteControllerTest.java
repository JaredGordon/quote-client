package cf.pivotal.quoteClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@WebIntegrationTest(value = "server.port=9873")
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class QuoteControllerTest {

    @Autowired
    private QuoteController quoteController;

    @Test
    public void testFindBySymbol() {
        Quote obj = quoteController.findBySymbol("GOOG");
        assertNotNull("Should find a result.", obj);

        // change can be positive, negative or zero, so just make sure nothing
        // is thrown
        try {
            obj.getChange1();
        } catch (Throwable t) {
            fail(t.getMessage());
        }
        assertEquals("Alphabet Inc.", obj.getCompanyname());
        assertNotNull(obj.getHigh());
        assertNotNull(obj.getLow());
        assertNotNull(obj.getOpen1());
        assertNotNull(obj.getPrice());
        assertEquals("GOOG", obj.getSymbol());
        assertNotNull(obj.getVolume());
    }

    @Test
    public void testMarketSummary() {
        MarketSummary m = quoteController.marketSummary();
        assertNotNull(m);
        assertNotNull(m.getChange());
        assertNotNull(m.getSummaryDate());
        assertNotNull(m.getTradeStockIndexAverage());
        assertNotNull(m.getTradeStockIndexOpenAverage());
        assertNotNull(m.getTradeStockIndexVolume());
    }
}
