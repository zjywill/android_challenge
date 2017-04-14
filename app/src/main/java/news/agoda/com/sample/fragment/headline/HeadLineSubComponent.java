package news.agoda.com.sample.fragment.headline;

import dagger.Subcomponent;

/**
 * Created by junyizhang on 14/04/2017.
 */
@HeadLine
@Subcomponent(modules = { HeadLineModule.class })
public interface HeadLineSubComponent {
    void inject(HeadLineFragment fragment);
}
