package cf.pivotal.quoteClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cf.pivotal.quoteClient.localconfig.DefaultConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@SpringApplicationConfiguration(classes = DefaultConfiguration.class)
public class QuoteFacadeTest {

	@Autowired
	QuoteRepository quoteRepository;

	@Test
	public void testIt() {
		Quote quote = quoteRepository.findBySymbol("GOOG");
		assertNotNull(quote);
		assertEquals(new Integer(46), quote.getQuoteid());
		assertEquals("GOOG", quote.getSymbol());
	}

}
