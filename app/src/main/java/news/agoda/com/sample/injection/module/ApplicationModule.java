package news.agoda.com.sample.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Named;
import javax.inject.Singleton;
import news.agoda.com.sample.BuildConfig;
import news.agoda.com.sample.model.domain.Domains;
import news.agoda.com.sample.util.AppSchedulerProvider;
import news.agoda.com.sample.util.Constants;
import news.agoda.com.sample.util.SchedulerProvider;
import news.agoda.com.sample.util.Utils;
import okhttp3.HttpUrl;

@Module
public class ApplicationModule {
    @Provides
    @Singleton
    @Named("isDebug")
    boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(Domains.NEWS_URL);
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    int provideNetworkTimeoutInSeconds() {
        return Constants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    long provideCacheSize() {
        return Constants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    int provideCacheMaxAgeMinutes() {
        return Constants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    int provideCacheMaxStaleDays() {
        return Constants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    File provideCacheDir(Context context) {
        return context.getCacheDir();
    }

    @Provides
    @Named("retryCount")
    int provideRetryCount() {
        return Constants.NETWORK_RETRY_COUNT;
    }

    @Provides
    @Named("isConnect")
    boolean provideIsConnect(Context context) {
        return Utils.isConnected(context);
    }
}
