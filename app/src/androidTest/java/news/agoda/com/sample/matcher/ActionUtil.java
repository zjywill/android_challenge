package news.agoda.com.sample.matcher;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;
import news.agoda.com.sample.util.Loge;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;

public class ActionUtil {

    public static class ReplaceTextAcion implements ViewAction {
        private int viewId;
        private String text;

        public ReplaceTextAcion(int viewId, String text) {
            this.viewId = viewId;
            this.text = text;
        }

        @Override
        public Matcher<View> getConstraints() {
            return isDisplayingAtLeast(90);
        }

        @Override
        public void perform(UiController uiController, View view) {
            Loge.d("ReplaceTextAcion : " + view);
            TextView textView = (TextView) view.findViewById(viewId);
            if (textView != null) {
                Loge.d("ReplaceTextAcion with : " + textView);
                textView.setText(text);
            }
        }

        @Override
        public String getDescription() {
            return "ReplaceTextAcion";
        }
    }
}
