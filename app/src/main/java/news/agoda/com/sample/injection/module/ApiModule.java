package news.agoda.com.sample.injection.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import news.agoda.com.sample.model.domain.client.ApiClient;
import news.agoda.com.sample.util.StringConverterFactory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by junyizhang on 13/04/2017.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public ApiClient provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(HttpUrl baseUrl, Converter.Factory converterFactory,
                                    CallAdapter.Factory callAdapterFactory,
                                    OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    public Converter.Factory provideStringConverterFactory() {
        return new StringConverterFactory();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
