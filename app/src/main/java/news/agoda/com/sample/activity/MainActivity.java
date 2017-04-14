package news.agoda.com.sample.activity;

import android.os.Bundle;

import news.agoda.com.sample.base.BaseFragmentActivity;
import news.agoda.com.sample.injection.component.ApplicationComponent;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }


}
