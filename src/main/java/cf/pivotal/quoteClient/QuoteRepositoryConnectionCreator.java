package cf.pivotal.quoteClient;

import java.net.URL;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.hateoas.hal.Jackson2HalModule;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Feign;
import feign.jackson.JacksonDecoder;

public class QuoteRepositoryConnectionCreator extends
		AbstractServiceConnectorCreator<QuoteRepository, WebServiceInfo> {
	
	@Override
	public QuoteRepository create(WebServiceInfo serviceInfo,
			ServiceConnectorConfig serviceConnectorConfig) {
		return create(serviceInfo.getUri());
	}
	
	public static QuoteRepository create(String url) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new Jackson2HalModule());

        return Feign.builder()
                .decoder(new JacksonDecoder(mapper))
                .target(QuoteRepository.class, url);
    }

    public static QuoteRepository create(URL url) {
        return create(url.toString());
    }
}
