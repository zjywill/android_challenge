package news.agoda.com.sample;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.reactivex.Observable;
import java.util.List;
import news.agoda.com.sample.activity.main.MainActivity;
import news.agoda.com.sample.injection.MockApplicationComponentRule;
import news.agoda.com.sample.model.domain.entity.NewsEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * Created by junyi on 4/16/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final MockApplicationComponentRule component = new MockApplicationComponentRule(
        InstrumentationRegistry.getTargetContext());

    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(
        MainActivity.class, false, false) {
        @Override
        protected Intent getActivityIntent() {
            // Override the default intent so we pass a false flag for syncing so it doesn't
            // start a sync service in the background that would affect  the behaviour of
            // this test.
            return new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);
        }
    };

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule public final TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void testLists() {
        List<NewsEntity> testDataNewsEntity = TestDataFactory.makeListNewsEntity(20);
        when(component.getMockApiManager().getNews()).thenReturn(
            Observable.just(testDataNewsEntity));
        System.out.println("testLists");
        main.launchActivity(null);

        onView(withText("NEWS TITLE: 0")).check(matches(isDisplayed()));
    }
}
