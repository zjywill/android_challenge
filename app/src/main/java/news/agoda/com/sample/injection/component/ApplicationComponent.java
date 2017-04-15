package news.agoda.com.sample.injection.component;

import dagger.Component;
import javax.inject.Singleton;
import news.agoda.com.sample.activity.article.ArticleActivity;
import news.agoda.com.sample.activity.main.MainActivity;
import news.agoda.com.sample.fragment.article.ArticleModule;
import news.agoda.com.sample.fragment.article.ArticleSubComponent;
import news.agoda.com.sample.fragment.headline.HeadLineModule;
import news.agoda.com.sample.fragment.headline.HeadLineSubComponent;
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

    void inject(ArticleActivity activity);

    ArticleSubComponent plus(ArticleModule module);

    HeadLineSubComponent plus(HeadLineModule module);
}