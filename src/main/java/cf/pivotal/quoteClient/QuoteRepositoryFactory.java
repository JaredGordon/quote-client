package cf.pivotal.quoteClient;

import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.hal.Jackson2HalModule;

import feign.Feign;
import feign.jackson.JacksonDecoder;

public class QuoteRepositoryFactory {
    public QuoteRepository create(String url) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new Jackson2HalModule());

        return Feign.builder()
                .decoder(new JacksonDecoder(mapper))
                .target(QuoteRepository.class, url);
    }

    public QuoteRepository create(URL url) {
        return create(url.toString());
    }
}
