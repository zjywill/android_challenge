package news.agoda.com.sample.injection;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import news.agoda.com.sample.model.domain.ApiManager;

import static org.mockito.Mockito.mock;

/**
 * Created by junyi on 4/16/17.
 */
@Module
public class MockApplicationModule {
    @Provides
    @Singleton
    ApiManager provideApiManager() {
        return mock(ApiManager.class);
    }
}
