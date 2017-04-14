package news.agoda.com.sample.fragment.headline;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import news.agoda.com.sample.base.BaseFragment;
import news.agoda.com.sample.injection.component.ApplicationComponent;

/**
 * Created by junyizhang on 14/04/2017.
 */

public class HeadLineFragment extends BaseFragment {

    @Override
    protected void injectDependencies(ApplicationComponent application, Context context) {
        application.plus(new HeadLineModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
