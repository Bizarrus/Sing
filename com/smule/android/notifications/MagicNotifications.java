package com.smule.android.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smule.android.AppDelegate;
import com.smule.android.AppDelegate.NotificationParams;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.PackageInfoUtils;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class MagicNotifications {
    private static final String f6903a = MagicNotifications.class.getName();
    private static MagicNotifications f6904b = null;
    private boolean f6905c = true;
    private boolean f6906d;
    private int f6907e;
    private String f6908f;
    private String f6909g;
    private long f6910h;
    private AtomicBoolean f6911i = new AtomicBoolean(false);
    private final Runnable f6912j = new 3(this);

    public static MagicNotifications m8296a() {
        if (f6904b == null) {
            f6904b = new MagicNotifications();
        }
        return f6904b;
    }

    public void m8311a(Context context) {
        MagicNetwork.m7790a(new 1(this, context));
    }

    private String m8307c(Context context) {
        m8310e(context);
        if (this.f6908f.isEmpty()) {
            Log.m7772c(f6903a, "Registration not found.");
            return "";
        } else if (this.f6910h != UserManager.m8111a().m8205g()) {
            Log.m7772c(f6903a, "Player ID changed: " + this.f6910h + " changed to " + UserManager.m8111a().m8205g());
            return "";
        } else {
            int a = PackageInfoUtils.a(context);
            if (this.f6907e != a) {
                Log.m7772c(f6903a, "App version changed was " + this.f6907e + " changed to " + a + ".");
                return "";
            }
            Log.m7772c(f6903a, "App version was not changed");
            String advertisingId = MagicNetwork.m7804d().getAdvertisingId(true);
            if (TextUtils.isEmpty(advertisingId)) {
                advertisingId = MagicNetwork.m7804d().getDeviceId();
            }
            if (advertisingId.equals(this.f6909g) || !advertisingId.startsWith("a:")) {
                Log.m7772c(f6903a, "Device ID was not changed");
                return this.f6908f;
            }
            Log.m7772c(f6903a, "Device ID changed was " + this.f6909g + " changed to " + advertisingId + ".");
            return "";
        }
    }

    private boolean m8309d(Context context) {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
    }

    private void m8299a(GoogleCloudMessaging googleCloudMessaging, Context context) {
        if (this.f6911i.getAndSet(true)) {
            Log.m7774d(f6903a, "registerInBackground - registration already in progress; aborting duplicate call");
            return;
        }
        new 2(this, googleCloudMessaging, context).execute(new Void[]{null, null, null});
    }

    public void m8313b() {
        m8310e(MagicNetwork.m7804d().getApplicationContext());
        Observer 4 = new 4(this);
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", 4);
        NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", 4);
    }

    public void m8314b(Context context) {
        if (this.f6906d) {
            m8311a(context);
        }
    }

    public static void m8297a(int i) {
        MagicNetwork.m7804d().setIconResource(i);
    }

    private void m8298a(Context context, boolean z, String str, String str2) {
        int a = PackageInfoUtils.a(context);
        Log.m7772c(f6903a, "update - updating MagicNotification info in SharedPreferences; value of registrationId is: " + str);
        context.getApplicationContext().getSharedPreferences("notification_prefs", 0).edit().putBoolean("pref_notifications_on", z).putBoolean("gcm_used", true).putInt("notification_app_ver", a).putString("notification_reg_id", str).putLong("player_id", UserManager.m8111a().m8205g()).putString("device_id", str2).apply();
        this.f6906d = z;
        this.f6907e = a;
        this.f6908f = str;
        this.f6910h = UserManager.m8111a().m8205g();
        this.f6909g = str2;
    }

    private void m8310e(Context context) {
        Log.m7772c(f6903a, "readPrefs - begin; mNeedPrefs = " + this.f6905c);
        if (this.f6905c) {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("notification_prefs", 0);
            this.f6906d = sharedPreferences.getBoolean("pref_notifications_on", true);
            this.f6907e = sharedPreferences.getInt("notification_app_ver", 0);
            this.f6908f = sharedPreferences.getString("notification_reg_id", "");
            this.f6909g = sharedPreferences.getString("device_id", "");
            this.f6910h = sharedPreferences.getLong("player_id", 0);
            this.f6905c = false;
        }
        Log.m7772c(f6903a, "readPrefs - end value of registration id is: " + this.f6908f);
    }

    public void m8312a(Context context, Bundle bundle) {
        AppDelegate d = MagicNetwork.m7804d();
        if (bundle == null) {
            bundle = new Bundle();
        }
        NotificationParams paramsFromBundle = d.getParamsFromBundle(bundle);
        Intent intent = new Intent("android.intent.action.VIEW", paramsFromBundle.a);
        intent.setData(paramsFromBundle.a);
        intent.putExtra("PARAMS", paramsFromBundle.e);
        intent.addFlags(335544320);
        MagicNetwork.m7804d().postNotification(context, paramsFromBundle, intent);
    }

    public String m8315c() {
        return this.f6908f;
    }
}
