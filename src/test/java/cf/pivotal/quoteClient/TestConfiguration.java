package cf.pivotal.quoteClient;

import feign.Feign;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"cf.pivotal.quoteClient"})
public class TestConfiguration {

    @Bean
    public QuoteRepository quoteRepository() {
        return Feign.builder().encoder(new GsonEncoder())
                .decoder(new QuoteDecoder())
                .target(QuoteRepository.class, "http://db-quote-service.cfapps.io/quotes");
    }
}