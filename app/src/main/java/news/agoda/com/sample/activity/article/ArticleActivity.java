package news.agoda.com.sample.activity.article;

import android.os.Bundle;
import news.agoda.com.sample.R;
import news.agoda.com.sample.base.BaseFragmentActivity;
import news.agoda.com.sample.fragment.article.ArticleFragment;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

public class ArticleActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Bundle bundle = getIntent().getExtras();
        NewsEntity newsEntity = null;
        if (bundle != null) {
            newsEntity = bundle.getParcelable("news");
        }
        ArticleFragment articleFragment
            = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article);
        articleFragment.displayArticle(newsEntity);
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }
}
