package news.agoda.com.sample.fragment.article;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import javax.inject.Inject;
import news.agoda.com.sample.R;
import news.agoda.com.sample.base.BaseFragment;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.model.domain.entity.NewsEntity;
import news.agoda.com.sample.util.Loge;

/**
 * Created by junyizhang on 14/04/2017.
 */

public class ArticleFragment extends BaseFragment {

    @Inject Context mContext;
    private TextView mTitleView;
    private ImageView mImageView;
    private TextView mSummaryView;
    private Button mFullStoryButton;
    private NewsEntity mNews;

    @Override
    protected void injectDependencies(ApplicationComponent application, Context context) {
        application.plus(new ArticleModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_article, container, false);
        mTitleView = (TextView) root.findViewById(R.id.title);
        mImageView = (ImageView) root.findViewById(R.id.news_image);
        mSummaryView = (TextView) root.findViewById(R.id.summary_content);
        mFullStoryButton = (Button) root.findViewById(R.id.full_story_link);
        if (mFullStoryButton != null) {
            mFullStoryButton.setOnClickListener(this::onFullStoryClicked);
        }
        return root;
    }

    public void displayArticle(NewsEntity news) {
        Loge.d("displayArticle: " + news);
        mNews = news;
        if (news != null) {
            if (mTitleView != null) {
                mTitleView.setText(news.getTitle());
            }
            if (mImageView != null) {
                if (news.getLargeImage() != null) {
                    mImageView.setVisibility(View.VISIBLE);
                    Glide.with(mContext)
                        .load(news.getLargeImage())
                        .placeholder(R.drawable.place_holder)
                        .into(mImageView);
                } else {
                    mImageView.setVisibility(View.GONE);
                }
            }
            if (mSummaryView != null) {
                mSummaryView.setText(news.getSummary());
            }
            if (mFullStoryButton != null && news.getArticleUrl() == null) {
                mFullStoryButton.setVisibility(View.GONE);
            }
        }
    }

    public void onFullStoryClicked(View view) {
        if (mNews != null && mNews.getArticleUrl() != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mNews.getArticleUrl()));
            startActivity(intent);
        }
    }
}
