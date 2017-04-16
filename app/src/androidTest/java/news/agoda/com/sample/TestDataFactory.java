package news.agoda.com.sample;

import java.util.ArrayList;
import java.util.List;
import news.agoda.com.sample.model.domain.entity.MediaEntity;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

/**
 * Created by junyi on 4/16/17.
 */

public class TestDataFactory {

    public static List<NewsEntity> makeListNewsEntity(int number) {
        List<NewsEntity> newsEntityList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            newsEntityList.add(makeNewsEntity(i));
        }
        return newsEntityList;
    }

    public static NewsEntity makeNewsEntity(int uniqueSuffix) {
        NewsEntity newsEntity = new NewsEntity();

        ArrayList<MediaEntity> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(mockCourseDatum());
        }
        newsEntity.setTitle("NEWS TITLE: " + uniqueSuffix);
        newsEntity.setSummary("NES SUMMARY");
        newsEntity.setArticleUrl(
            "http://www.nytimes.com/2015/08/19/technology/amazon-workplace-reactions-comments.html");
        newsEntity.setMediaEntityList(data);
        return newsEntity;
    }

    public static MediaEntity mockCourseDatum() {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setFormat("Standard Thumbnail");
        mediaEntity.setUrl(
            "http://static01.nyt.com/images/2015/08/13/business/13amazon-selects-slide-AUYG/13amazon-selects-slide-AUYG-thumbStandard.jpg");
        return mediaEntity;
    }
}
