package news.agoda.com.sample.fragment.article;

import dagger.Subcomponent;

/**
 * Created by junyizhang on 14/04/2017.
 */
@Article
@Subcomponent(modules = { ArticleModule.class })
public interface ArticleSubComponent {
    void inject(ArticleFragment fragment);
}
