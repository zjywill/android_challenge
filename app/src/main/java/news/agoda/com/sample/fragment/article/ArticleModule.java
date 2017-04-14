package news.agoda.com.sample.fragment.article;

import dagger.Module;
import dagger.Provides;

/**
 * Created by junyizhang on 14/04/2017.
 */
@Module
public class ArticleModule {

    @Provides
    @Article
    public ArticlePresenter providePresenter(ArticlePresenter presenter) {
        return presenter;
    }
}
