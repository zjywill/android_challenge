package news.agoda.com.sample.injection;

import android.content.Context;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ImmediateThinScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import news.agoda.com.sample.MainApplication;
import news.agoda.com.sample.injection.module.AndroidModule;
import news.agoda.com.sample.model.domain.ApiManager;
import news.agoda.com.sample.util.Loge;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by junyi on 4/16/17.
 */

public class MockApplicationComponentRule implements TestRule {

    private final MockApplicationComponent mTestComponent;
    private final Context mContext;

    public MockApplicationComponentRule(Context context) {
        Loge.e("MockApplicationComponentRule create");
        mContext = context;
        MainApplication application = MainApplication.get(context);
        mTestComponent = DaggerMockApplicationComponent.builder()
            .androidModule(new AndroidModule(application))
            .build();
    }

    public Context getContext() {
        return mContext;
    }

    public ApiManager getMockApiManager() {
        return mTestComponent.apiManager();
    }

    @Override
    public Statement apply(Statement base, Description description) {
        Loge.e("apply return Statement");
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                setupHooks();

                MainApplication application = MainApplication.get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }

    private void setupHooks() {
        RxJavaPlugins.setIoSchedulerHandler((scheduler) -> {
            Loge.e("setIoSchedulerHandler");
            return ImmediateThinScheduler.INSTANCE;
        });
        RxJavaPlugins.setNewThreadSchedulerHandler((scheduler) -> {
            Loge.e("setNewThreadSchedulerHandler");
            return ImmediateThinScheduler.INSTANCE;
        });
        RxAndroidPlugins.setMainThreadSchedulerHandler((scheduler) -> {
            Loge.e("setMainThreadSchedulerHandler");
            return ImmediateThinScheduler.INSTANCE;
        });
    }
}
