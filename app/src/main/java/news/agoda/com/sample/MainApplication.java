package news.agoda.com.sample;

import android.app.Application;
import android.content.Context;
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

    // *************** for testing **************
    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
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
