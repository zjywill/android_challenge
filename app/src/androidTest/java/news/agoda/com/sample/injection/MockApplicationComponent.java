package news.agoda.com.sample.injection;

import dagger.Component;
import javax.inject.Singleton;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.injection.module.AndroidModule;
import news.agoda.com.sample.injection.module.ApiModule;
import news.agoda.com.sample.injection.module.ApplicationModule;
import news.agoda.com.sample.injection.module.ClientModule;
import news.agoda.com.sample.model.domain.ApiManager;

@Singleton
@Component(modules = {
    AndroidModule.class, ApplicationModule.class, ApiModule.class, ClientModule.class,
    MockApplicationModule.class,
})
public interface MockApplicationComponent extends ApplicationComponent {

    ApiManager apiManager();
}