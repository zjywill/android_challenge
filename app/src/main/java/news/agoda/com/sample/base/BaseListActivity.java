package news.agoda.com.sample.base;

import android.app.ListActivity;
import android.os.Bundle;
import news.agoda.com.sample.MainApplication;
import news.agoda.com.sample.injection.component.ApplicationComponent;

public abstract class BaseListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(MainApplication.getComponent());

        // can be used for general purpose in all Activities of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component);
}
