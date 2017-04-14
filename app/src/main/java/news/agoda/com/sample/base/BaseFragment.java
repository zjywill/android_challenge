package news.agoda.com.sample.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import news.agoda.com.sample.MainApplication;
import news.agoda.com.sample.injection.component.ApplicationComponent;

/**
 * Created by junyizhang on 14/04/2017.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(MainApplication.getComponent(), getActivity());
    }

    protected abstract void injectDependencies(ApplicationComponent application, Context context);
}
