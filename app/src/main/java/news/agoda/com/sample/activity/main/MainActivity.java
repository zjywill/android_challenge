package news.agoda.com.sample.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import news.agoda.com.sample.R;
import news.agoda.com.sample.activity.article.ArticleActivity;
import news.agoda.com.sample.base.BaseFragmentActivity;
import news.agoda.com.sample.fragment.article.ArticleFragment;
import news.agoda.com.sample.fragment.headline.HeadLineFragment;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

public class MainActivity extends BaseFragmentActivity
    implements MainActivityInput, HeadLineFragment.OnHeadlineSelectedListener {

    @Inject MainActivityPresenter mMainActivityPresenter;
    private boolean mIsDualPane = false;
    private HeadLineFragment mHeadLineFragment;
    private ArticleFragment mArticleFragment;
    private int mHeadlineIndex = 0;
    private List<NewsEntity> mNewsEntities = new ArrayList<>();
    private int mTopPadding = 0;
    private int mResumeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mHeadLineFragment = (HeadLineFragment) getSupportFragmentManager().findFragmentById(
            R.id.headlines);
        mArticleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(
            R.id.article);

        View articleView = findViewById(R.id.article);
        mIsDualPane = articleView != null && articleView.getVisibility() == View.VISIBLE;

        mHeadLineFragment.setOnHeadlineSelectedListener(this);
        mHeadLineFragment.setSelectable(mIsDualPane);

        mMainActivityPresenter.setMainActivityInput(this);
        mMainActivityPresenter.onViewLoaded();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        restoreSelection(savedInstanceState);
    }

    void restoreSelection(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (mIsDualPane && mHeadLineFragment != null) {
                mHeadlineIndex = savedInstanceState.getInt("headlineIndex", 0);
                mHeadLineFragment.setSelection(mHeadlineIndex);
                onHeadlineSelected(mHeadlineIndex);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mIsDualPane && mHeadLineFragment != null && mHeadLineFragment.getListView() != null) {
            mHeadLineFragment.getListView().setSelectionFromTop(mResumeIndex, mTopPadding);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!mIsDualPane && mHeadLineFragment != null && mHeadLineFragment.getListView() != null) {
            mResumeIndex = mHeadLineFragment.getListView().getFirstVisiblePosition();
            View v = mHeadLineFragment.getListView().getChildAt(0);
            mTopPadding = (v == null)
                          ? 0
                          : (v.getTop() - mHeadLineFragment.getListView().getPaddingTop());
        }
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onHeadlinesLoaded(List<NewsEntity> newsEntities) {
        mNewsEntities.clear();
        mNewsEntities.addAll(newsEntities);
        if (mHeadLineFragment != null) {
            mHeadLineFragment.loadHeadlines(mNewsEntities);
            if (mIsDualPane && mHeadLineFragment != null) {
                mHeadLineFragment.setSelection(mHeadlineIndex);
                onHeadlineSelected(mHeadlineIndex);
            }
        }
    }

    @Override
    public void onEmptyData() {

    }

    @Override
    public void onHeadlineSelected(int index) {
        mHeadlineIndex = index;
        if (index > mNewsEntities.size() || mNewsEntities.size() == 0) {
            return;
        }
        if (mIsDualPane) {
            mArticleFragment.displayArticle(mNewsEntities.get(index));
        } else {
            Intent i = new Intent(this, ArticleActivity.class);
            i.putExtra("news", mNewsEntities.get(index));
            startActivity(i);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("headlineIndex", mHeadlineIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainActivityPresenter.onDestroy();
    }
}
