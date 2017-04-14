package news.agoda.com.sample.fragment.headline;

import dagger.Module;
import dagger.Provides;

/**
 * Created by junyizhang on 14/04/2017.
 */

@Module
public class HeadLineModule {

    @Provides
    @HeadLine
    public HeadLinePresenter providePresenter(HeadLinePresenter presenter) {
        return presenter;
    }
}
