package cf.pivotal.quoteClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "test" })
public class TestConfiguration {

	@Bean
	public QuoteRepository quoteRepository() {
		return new QuoteRepositoryConnectionCreator().initialize("http://localhost:8080/quoteService");
	}
}
