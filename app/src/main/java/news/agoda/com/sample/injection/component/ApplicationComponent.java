package news.agoda.com.sample.injection.component;

import dagger.Component;
import javax.inject.Singleton;
import news.agoda.com.sample.MainActivity;
import news.agoda.com.sample.injection.module.AndroidModule;
import news.agoda.com.sample.injection.module.ApplicationModule;

@Singleton
@Component(modules = {
    AndroidModule.class, ApplicationModule.class
})
public interface ApplicationComponent {

    void inject(MainActivity activity);
}