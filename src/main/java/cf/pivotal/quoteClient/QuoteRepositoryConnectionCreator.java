package cf.pivotal.quoteClient;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

public class QuoteRepositoryConnectionCreator extends
		AbstractServiceConnectorCreator<QuoteRepository, WebServiceInfo> {
	
	@Override
	public QuoteRepository create(WebServiceInfo serviceInfo,
			ServiceConnectorConfig serviceConnectorConfig) {
		return new QuoteRepositoryFactory().create(serviceInfo.getUri());
	}
}
