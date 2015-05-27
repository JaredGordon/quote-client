package cf.pivotal.quoteClient.localconfig;

import org.springframework.cloud.localconfig.LocalConfigServiceInfoCreator;
import org.springframework.cloud.service.UriBasedServiceData;

import cf.pivotal.quoteClient.WebServiceInfo;

public class QuoteWebServiceInfoCreator extends
		LocalConfigServiceInfoCreator<WebServiceInfo> {
	public static final String QUOTES_TAG = "quoteService";

	public QuoteWebServiceInfoCreator() {
		super("http");
	}

	@Override
	public boolean accept(UriBasedServiceData serviceData) {
		return super.accept(serviceData)
				&& QUOTES_TAG.equals(serviceData.getKey());
	}

	@Override
	public WebServiceInfo createServiceInfo(String id, String uri) {
		return new WebServiceInfo(id, uri);
	}
}
