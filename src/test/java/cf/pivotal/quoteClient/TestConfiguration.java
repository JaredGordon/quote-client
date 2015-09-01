package cf.pivotal.quoteClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.gson.GsonEncoder;

@Configuration
public class TestConfiguration {

	@Bean
	public QuoteRepository quoteRepository() {
		return Feign.builder().encoder(new GsonEncoder())
				.decoder(new QuoteDecoder())
				.target(QuoteRepository.class, "http://localhost:8080/quotes");
	}
}