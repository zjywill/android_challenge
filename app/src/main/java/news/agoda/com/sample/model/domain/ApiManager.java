package news.agoda.com.sample.model.domain;

import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;
import news.agoda.com.sample.model.domain.client.ApiClient;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

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

    public Observable<String> getNews() {
        return apiClient.getNews();
    }
}
