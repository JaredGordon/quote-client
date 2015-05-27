package cf.pivotal.quoteClient.localconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import cf.pivotal.quoteClient.QuoteRepository;
import cf.pivotal.quoteClient.QuoteRepositoryFactory;

@Configuration
@Profile({"default", "test", "local"})
public class DefaultConfiguration {
	
	@Bean
	public QuoteRepository quoteRepository() {
		return new QuoteRepositoryFactory()
				.create("http://localhost:8080/quoteService");
	}
	
}
