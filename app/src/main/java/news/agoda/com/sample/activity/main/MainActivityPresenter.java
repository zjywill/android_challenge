package news.agoda.com.sample.activity.main;

import android.content.Context;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import news.agoda.com.sample.model.domain.ApiManager;
import news.agoda.com.sample.util.Loge;
import news.agoda.com.sample.util.SchedulerProvider;

/**
 * Created by junyi on 4/15/17.
 */

public class MainActivityPresenter implements MainActivityOutput {

    private final Context mContext;
    private final ApiManager mApiManager;
    private final SchedulerProvider mSchedulerProvider;
    private MainActivityInput mMainActivityInput;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public MainActivityPresenter(Context context, ApiManager apiManager,
                                 SchedulerProvider shedulerProvider) {
        mContext = context;
        mApiManager = apiManager;
        mSchedulerProvider = shedulerProvider;
    }

    public void setMainActivityInput(MainActivityInput mainActivityInput) {
        mMainActivityInput = mainActivityInput;
    }

    @Override
    public void onViewLoaded() {
        Loge.d("onViewLoaded");
        loadHeadlines();
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }

    private void loadHeadlines() {
        Disposable disposable = mApiManager.getNews()
            .compose(mSchedulerProvider.applySchedulers())
            .subscribe(mMainActivityInput::onHeadlinesLoaded,
                       e -> mMainActivityInput.onEmptyData());
        mCompositeDisposable.add(disposable);
    }
}
