package news.agoda.com.sample.model.domain;

import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import news.agoda.com.sample.model.domain.client.ApiClient;
import news.agoda.com.sample.model.domain.entity.NewsEntity;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by junyizhang on 13/04/2017.
 */
@Singleton
public class ApiManager {

    private final ApiClient apiClient;

    @Inject
    public ApiManager(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Observable<List<NewsEntity>> getNews() {
        return apiClient.getNews().flatMap(newsString -> {
            List<NewsEntity> newsItemList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(newsString);
            JSONArray resultArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject newsObject = resultArray.getJSONObject(i);
                NewsEntity newsEntity = new NewsEntity(newsObject);
                newsItemList.add(newsEntity);
            }
            return Observable.just(newsItemList);
        });
    }
}
