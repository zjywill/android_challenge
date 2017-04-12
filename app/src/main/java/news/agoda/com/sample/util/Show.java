package news.agoda.com.sample.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import java.io.File;

/**
 * Created by zjy on 3/9/16.
 */
public class Show {
    public static final String EXTRA = "extra";

    private Show() {
    }

    public static void activity(Context context, Class<?> cls) {
        if (context != null) {
            Intent intent = new Intent(context, cls);
            context.startActivity(intent);
        }
    }

    public static void activity(Context context, Class<?> cls, Bundle extra) {
        if (context != null) {
            Intent intent = new Intent(context, cls);
            if (extra != null) {
                intent.putExtra(EXTRA, extra);
            }
            context.startActivity(intent);
        }
    }

    public static void activity(Context context, Class<?> cls, int flags, Bundle extra) {
        if (context != null) {
            Intent intent = new Intent(context, cls);
            if (extra != null) {
                intent.putExtra(EXTRA, extra);
            }
            intent.setFlags(flags);
            context.startActivity(intent);
        }
    }

    public static void activityForResult(Activity activity, Class<?> cls, int requestCode,
                                         Bundle extra) {
        if (activity != null) {
            Intent intent = new Intent(activity, cls);
            if (extra != null) {
                intent.putExtra(EXTRA, extra);
            }
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void activityForResult(Fragment fragment, Class<?> cls, int requestCode,
                                         Bundle extra) {
        if (fragment != null) {
            Intent intent = new Intent(fragment.getContext(), cls);
            if (extra != null) {
                intent.putExtra(EXTRA, extra);
            }
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    public static void toast(Context context, String text) {
        if (context != null && text != null) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(Context context, int textId) {
        if (context != null) {
            Toast.makeText(context, textId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void systemSetting(Activity activity) {
        if (activity != null) {
            Intent systemSetting = new Intent(android.provider.Settings.ACTION_SETTINGS);
            activity.startActivityForResult(systemSetting, 0);
        }
    }

    public static void systemSetting(Context context) {
        if (context != null) {
            Intent systemSetting = new Intent(android.provider.Settings.ACTION_SETTINGS);
            context.startActivity(systemSetting);
        }
    }

}
