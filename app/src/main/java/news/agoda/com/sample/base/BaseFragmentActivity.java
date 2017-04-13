package news.agoda.com.sample.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import news.agoda.com.sample.MainApplication;
import news.agoda.com.sample.injection.component.ApplicationComponent;

public abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(MainApplication.getComponent());

        // can be used for general purpose in all Activities of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component);
}
