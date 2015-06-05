package cf.pivotal.quoteClient;

import java.net.URL;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class QuoteRepositoryConnectionCreator extends
		AbstractServiceConnectorCreator<QuoteRepository, WebServiceInfo> {
	
	public QuoteRepository create(WebServiceInfo serviceInfo,
			ServiceConnectorConfig serviceConnectorConfig) {
		return create(serviceInfo.getUri());
	}
	
	public QuoteRepository create(String url) {
		  QuoteRepository qr = Feign.builder()
	        		.encoder(new JacksonEncoder())
	                .decoder(new JacksonDecoder())
	                .target(QuoteRepository.class, url);
	        return qr;
    }

    public QuoteRepository create(URL url) {
        return create(url.toString());
    }
}
