package news.agoda.com.sample;

import android.app.Application;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.injection.component.DaggerApplicationComponent;
import news.agoda.com.sample.injection.module.AndroidModule;

/**
 * Created by junyizhang on 12/04/2017.
 */

public class MainApplication extends Application {
    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    // Needed to replace the component with a test specific one
    public static void setComponent(ApplicationComponent applicationComponent) {
        component = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().androidModule(new AndroidModule(this)).build();
    }
}
