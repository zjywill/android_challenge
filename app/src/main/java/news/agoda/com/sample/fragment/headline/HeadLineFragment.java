package news.agoda.com.sample.fragment.headline;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import javax.inject.Inject;
import news.agoda.com.sample.base.BaseListFragment;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

/**
 * Created by junyizhang on 14/04/2017.
 */

public class HeadLineFragment extends BaseListFragment implements AdapterView.OnItemClickListener {

    @Inject Context mContext;
    private OnHeadlineSelectedListener mHeadlineSelectedListener = null;
    private HeadLineListAdapter mListAdapter;

    public HeadLineFragment() {
        super();
    }

    @Override
    protected void injectDependencies(ApplicationComponent application, Context context) {
        application.plus(new HeadLineModule()).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new HeadLineListAdapter(mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        setListAdapter(mListAdapter);
        getListView().setOnItemClickListener(this);
    }

    public void setOnHeadlineSelectedListener(OnHeadlineSelectedListener listener) {
        mHeadlineSelectedListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mHeadlineSelectedListener) {
            mHeadlineSelectedListener.onHeadlineSelected(position);
        }
    }

    public void loadHeadlines(List<NewsEntity> newsEntities) {
        mListAdapter.setNewsItemList(newsEntities);
        mListAdapter.notifyDataSetChanged();
    }

    public void setSelectable(boolean selectable) {
        if (selectable) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        } else {
            getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
        }
    }

    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(int index);
    }
}
