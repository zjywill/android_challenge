package news.agoda.com.sample.model.domain.client;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by junyizhang on 13/04/2017.
 */

public interface ApiClient {
    @Headers({ "Accept: application/json", "Cache-Control: no-cache" })
    @GET("bins/nl6jh")
    Observable<String> getNews();
}
