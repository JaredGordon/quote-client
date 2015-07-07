package cf.pivotal.quoteClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
public class QuoteFacadeTest {

	@Autowired
	QuoteController quoteController;

	@Test
	public void testFallBack() {
		Quote quote = quoteController.findBySymbol("GOOG");
		assertNotNull(quote);
		assertNotNull(quote.getQuoteid());
		assertTrue(quote.getQuoteid().intValue() >= 0);
		assertEquals("GOOG", quote.getSymbol());
	}
}
