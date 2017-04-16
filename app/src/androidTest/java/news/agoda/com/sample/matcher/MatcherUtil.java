package news.agoda.com.sample.matcher;

import android.support.test.internal.util.Checks;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.core.IsInstanceOf.any;

public class MatcherUtil {

    public static int counter = 0;
    public static boolean first = false;

    public static void resetMatcherUtil() {
        counter = 0;
        first = false;
    }

    public static Matcher<View> withTextAndCount(final String testText) {
        Checks.checkNotNull(testText);
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with item text: " + testText);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (view instanceof TextView && testText.equals(((TextView) view).getText())) {
                    counter++;
                    return true;
                }
                return false;
            }
        };
    }

    public static <T> Matcher<T> first(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }

    public static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
                                                final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
                    ((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static class CustomViewHolderMatcher extends TypeSafeMatcher<RecyclerView.ViewHolder> {
        private Matcher<View> itemMatcher = any(View.class);

        public CustomViewHolderMatcher() { }

        public CustomViewHolderMatcher(Matcher<View> itemMatcher) {
            this.itemMatcher = itemMatcher;
        }

        @Override
        public boolean matchesSafely(RecyclerView.ViewHolder viewHolder) {
            return itemMatcher.matches(viewHolder.itemView);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("is assignable from CustomViewHolder");
        }
    }
}
