package news.agoda.com.sample.model.domain;

import javax.inject.Inject;
import javax.inject.Singleton;
import news.agoda.com.sample.model.domain.client.ApiClient;

/**
 * Created by junyizhang on 13/04/2017.
 */
@Singleton
public class ApiManager {

    private ApiClient apiClient;

    @Inject
    public ApiManager(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
