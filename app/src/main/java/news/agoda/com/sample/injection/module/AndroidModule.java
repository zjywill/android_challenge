package news.agoda.com.sample.injection.module;

import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import news.agoda.com.sample.MainApplication;

@Module
public class AndroidModule {
    private MainApplication application;

    public AndroidModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}
