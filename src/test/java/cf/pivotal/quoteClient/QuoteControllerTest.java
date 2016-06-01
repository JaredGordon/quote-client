package cf.pivotal.quoteClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
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
    public void testFindBySymbolIn() {
        List<Quote> res = quoteController.findBySymbolIn(null);
        assertNotNull(res);
        assertTrue("Should have no results.", res.size() == 0);

        Set<String> s = new HashSet<String>();
        res = quoteController.findBySymbolIn(s);
        assertNotNull(res);
        assertTrue("Should have no results.", res.size() == 0);

        s.add("AAPL");
        res = quoteController.findBySymbolIn(s);
        assertNotNull(res);
        assertTrue("Should have 1 result.", res.size() == 1);
        assertTrue("AAPL".equals(res.get(0).getSymbol()));

        s.add("EBAY");
        res = quoteController.findBySymbolIn(s);
        assertNotNull(res);
        assertTrue("Should have 2 results.", res.size() == 2);
        assertTrue("AAPL".equals(res.get(0).getSymbol())
                || "EBAY".equals(res.get(0).getSymbol()));
        assertTrue("AAPL".equals(res.get(1).getSymbol())
                || "EBAY".equals(res.get(1).getSymbol()));

        s.add("VMW");
        res = quoteController.findBySymbolIn(s);
        assertNotNull(res);
        assertTrue("Should have 3 results.", res.size() == 3);
    }

    @Test
    public void testMarketSummary() {
        MarketSummary m = quoteController.marketSummary();
        assertNotNull(m);
        assertNotNull(m.getChange());
        assertNotNull(m.getPercentGain());
        assertNotNull(m.getSummaryDate());
        assertNotNull(m.getTradeStockIndexAverage());
        assertNotNull(m.getTradeStockIndexOpenAverage());
        assertNotNull(m.getTradeStockIndexVolume());
        assertNotNull(m.getTopGainers());
        assertEquals(3, m.getTopGainers().size());
        assertNotNull(m.getTopLosers());
        assertEquals(3, m.getTopLosers().size());
    }

    @Test
    public void testGainers() {
        List<Quote> qs = quoteController.topGainers();
        assertNotNull(qs);
        assertEquals(3, qs.size());
        for (Quote q : qs) {
            assertNotNull(q);
            assertNotNull(q.getSymbol());
            assertNotNull(q.getChange1());
        }
    }

    @Test
    public void testLosers() {
        List<Quote> qs = quoteController.topLosers();
        assertNotNull(qs);
        assertEquals(3, qs.size());
        for (Quote q : qs) {
            assertNotNull(q);
            assertNotNull(q.getSymbol());
            assertNotNull(q.getChange1());
        }
    }

    @Test
    public void testCountAllQuotes() {
        assertEquals("22", "" + quoteController.countAllQuotes());
    }

    @Test
    public void testFindAll() {
        List<Quote> all = quoteController.findAllQuotes();
        assertEquals("20", "" + all.size());
    }

    @Test
    //@Ignore
    public void testSave() {
        Quote q = new Quote();
        q.setSymbol("foo");
        q.setChange1(new BigDecimal(1.23));
        q.setCompanyname("Foo, Inc.");
        q.setHigh(new BigDecimal(123.45));
        q.setLow(new BigDecimal(12.3));
        q.setOpen1(new BigDecimal(119.78));
        q.setPrice(new BigDecimal(121.55));
        q.setVolume(new BigDecimal(45678));
        Quote q2 = quoteController.saveQuote(q);
        assertNotNull(q2);

        quoteController.deleteQuote(q2);
    }
}
