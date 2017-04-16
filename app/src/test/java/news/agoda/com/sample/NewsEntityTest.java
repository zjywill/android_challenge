package news.agoda.com.sample;

import java.util.ArrayList;
import java.util.List;
import news.agoda.com.sample.model.domain.entity.MediaEntity;
import news.agoda.com.sample.model.domain.entity.NewsEntity;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by junyi on 4/16/17.
 */

public class NewsEntityTest {
    NewsEntity mNewsEntity;

    @Before
    public void setup() {
        mNewsEntity = makeNewsEntity();
    }

    @Test
    public void testCourses() throws Exception {
        assertThat(mNewsEntity, is(notNullValue()));
        System.out.println(mNewsEntity.getTitle());
        System.out.println(mNewsEntity.getArticleUrl());

        MediaEntity mediaEntity = mNewsEntity.getMediaEntity().get(0);
        assertThat(mediaEntity, is(notNullValue()));
        System.out.println(mediaEntity.getFormat());
        System.out.println(mediaEntity.getUrl());
    }

    public static NewsEntity makeNewsEntity() {
        NewsEntity newsEntity = new NewsEntity();

        ArrayList<MediaEntity> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(mockCourseDatum());
        }
        newsEntity.setTitle("NEWS TITLE");
        newsEntity.setSummary("NES SUMMARY");
        newsEntity.setArticleUrl(
            "http://www.nytimes.com/2015/08/19/technology/amazon-workplace-reactions-comments.html");
        newsEntity.setMediaEntityList(data);
        return newsEntity;
    }

    public static MediaEntity mockCourseDatum() {
        MediaEntity mediaEntity = mock(MediaEntity.class);
        when(mediaEntity.getFormat()).thenReturn("Standard Thumbnail");
        when(mediaEntity.getUrl()).thenReturn(
            "http://static01.nyt.com/images/2015/08/13/business/13amazon-selects-slide-AUYG/13amazon-selects-slide-AUYG-thumbStandard.jpg");
        return mediaEntity;
    }
}
