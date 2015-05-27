package cf.pivotal.quoteClient.cloud;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import cf.pivotal.quoteClient.QuoteRepository;

@Configuration
@Profile({ "cloud" })
public class CloudConfiguration extends AbstractCloudConfig {

	@Bean
	public QuoteRepository quoteRepository() {
		return connectionFactory().service(QuoteRepository.class);
	}

}
