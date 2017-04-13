package news.agoda.com.sample.injection.component;

import dagger.Component;
import javax.inject.Singleton;
import news.agoda.com.sample.MainActivity;
import news.agoda.com.sample.injection.module.AndroidModule;
import news.agoda.com.sample.injection.module.ApiModule;
import news.agoda.com.sample.injection.module.ApplicationModule;
import news.agoda.com.sample.injection.module.ClientModule;

@Singleton
@Component(modules = {
    AndroidModule.class, ApplicationModule.class, ApiModule.class, ClientModule.class
})
public interface ApplicationComponent {

    void inject(MainActivity activity);
}