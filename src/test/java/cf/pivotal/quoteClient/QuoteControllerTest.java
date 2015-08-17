package cf.pivotal.quoteClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@WebIntegrationTest(value = "server.port=9873")
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class QuoteControllerTest {

	@Autowired
	QuoteController quoteController;

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
		assertEquals("Google Inc.", obj.getCompanyname());
		assertTrue(obj.getHigh().floatValue() > 0.0);
		assertTrue(obj.getLow().floatValue() > 0);
		assertTrue(obj.getOpen1().floatValue() > 0);
		assertTrue(obj.getPrice().floatValue() > 0);
		assertEquals("GOOG", obj.getSymbol());
		assertTrue(obj.getVolume().floatValue() > 0);
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

		s.add("BRCM");
		res = quoteController.findBySymbolIn(s);
		assertNotNull(res);
		assertTrue("Should have 1 result.", res.size() == 1);
		assertTrue("BRCM".equals(res.get(0).getSymbol()));

		s.add("EBAY");
		res = quoteController.findBySymbolIn(s);
		assertNotNull(res);
		assertTrue("Should have 2 results.", res.size() == 2);
		assertTrue("BRCM".equals(res.get(0).getSymbol())
				|| "EBAY".equals(res.get(0).getSymbol()));
		assertTrue("BRCM".equals(res.get(1).getSymbol())
				|| "EBAY".equals(res.get(1).getSymbol()));

		s.add("YHOO");
		res = quoteController.findBySymbolIn(s);
		assertNotNull(res);
		assertTrue("Should have 3 results.", res.size() == 3);
	}

	@Test
	public void testFindIndexAverage() {
		float f = quoteController.indexAverage();
		assertTrue(f > 0.0f);
	}

	@Test
	public void testFindOpenAverage() {
		float f = quoteController.openAverage();
		assertTrue(f > 0.0f);
	}

	@Test
	public void testFindVolume() {
		long l = quoteController.volume();
		assertTrue(l > 0);
	}

	@Test
	public void testFindChange() {
		try {
			quoteController.change();
		} catch (Throwable t) {
			// can be any number, positive, negative or zero, so just looking
			// for "no exception."
			fail(t.getMessage());
		}
	}

	@Test
	public void testMarketSummary() {
		Map<String, Object> m = quoteController.marketSummary();
		assertNotNull(m);
		assertTrue(m.size() == 5);
		assertNotNull(m.get("tradeStockIndexAverage"));
		assertNotNull(m.get("tradeStockIndexOpenAverage"));
		assertNotNull(m.get("tradeStockIndexVolume"));
		assertNotNull(m.get("cnt"));
		assertNotNull(m.get("change"));
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
		assertEquals("22", "" + all.size());
		Quote q = all.get(0);
		assertNotNull(q);
		assertEquals(q.getSymbol(), "AAPL");
		q = all.get(21);
		assertNotNull(q);
		assertEquals(q.getSymbol(), "YHOO");
	}
}
