package cf.pivotal.quoteClient.cloud;

import org.springframework.cloud.service.UriBasedServiceInfo;

public class WebServiceInfo extends UriBasedServiceInfo {
    public WebServiceInfo(String id, String url) {
        super(id, url);
    }
}
