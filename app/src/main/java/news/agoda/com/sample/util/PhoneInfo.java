package news.agoda.com.sample.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by junyi on 3/17/16.
 */
public class PhoneInfo {

    private PhoneInfo() {}

    public static boolean isMiUi() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(
                new InputStreamReader(p.getInputStream(), Charset.forName("UTF-8")), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Loge.w(e);
                }
            }
        }
        return line;
    }

    public static String versionName(Context context) {
        String versionName = "";
        if (context != null) {
            try {
                PackageInfo pInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
                versionName = pInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                Loge.w(e);
            }
        }
        return versionName;
    }

    public static String getApplicationValue(Context context, String key) {
        String value = "";
        try {
            if (context == null) {
                return "";
            }
            ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object val = appInfo.metaData.get(key);
            value = String.valueOf(val);
        } catch (PackageManager.NameNotFoundException e) {
        } catch (Exception e) {
        }
        return value;
    }

    public static String getUUID(Context context) {
        final String androidId = android.provider.Settings.Secure.getString(
            context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        String uniqueId = androidId;
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(
                Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial;
            tmDevice = tm.getDeviceId();
            tmSerial = tm.getSimSerialNumber();
            Loge.d("tmDevice=" + tmDevice);
            Loge.d("tmSerial=" + tmSerial);
            Loge.d("androidId=" + androidId);
            String originString = tmDevice + tmSerial + androidId;
            UUID deviceUuid = UUID.nameUUIDFromBytes(
                originString.getBytes(Charset.forName("UTF-8")));
            uniqueId = deviceUuid.toString().toUpperCase();
            Loge.d("uuid=" + uniqueId);
        } catch (Exception e) {
            uniqueId = UUID.randomUUID().toString().toUpperCase();
            Loge.w(e);
        }
        return uniqueId;
    }

    public static String deviceModel() {
        return Build.MANUFACTURER + "; " + Build.MODEL;
    }
}
