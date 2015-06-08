package cf.pivotal.quoteClient;

import java.net.URL;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import cf.pivotal.quoteClient.cloud.WebServiceInfo;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
@Profile({ "cloud" })
public class QuoteRepositoryConnectionCreator extends
		AbstractServiceConnectorCreator<QuoteRepository, WebServiceInfo> {

	private WebServiceInfo serviceInfo;
	private QuoteRepository quoteRepository;

	public QuoteRepository create(WebServiceInfo serviceInfo,
			ServiceConnectorConfig serviceConnectorConfig) {
		setServiceInfo(serviceInfo);
		setQuoteRepository(initialize(getServiceInfo().getUri()));
		return getQuoteRepository();
	}

	public QuoteRepository initialize(String url) {
		QuoteRepository qr = Feign.builder().encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(QuoteRepository.class, url);
		return qr;
	}

	public QuoteRepository initialize(URL url) {
		return initialize(url.toString());
	}

	private WebServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	private void setServiceInfo(WebServiceInfo s) {
		this.serviceInfo = s;
	}

	@Bean
	public QuoteRepository getQuoteRepository() {
		return quoteRepository;
	}

	private void setQuoteRepository(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

}
