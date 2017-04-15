package news.agoda.com.sample.activity.main;

import java.util.List;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

/**
 * Created by junyi on 4/15/17.
 */

public interface MainActivityInput {
    void onHeadlinesLoaded(List<NewsEntity> newsEntities);

    void onEmptyData();
}
