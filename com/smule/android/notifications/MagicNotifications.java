/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.support.v4.app.NotificationManagerCompat
 *  android.text.TextUtils
 *  com.google.android.gms.common.GoogleApiAvailability
 *  com.google.android.gms.gcm.GoogleCloudMessaging
 */
package com.smule.android.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smule.android.AppDelegate;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.UserManager;
import com.smule.android.notifications.MagicNotifications;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.PackageInfoUtils;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class MagicNotifications {
    private static final String a = MagicNotifications.class.getName();
    private static MagicNotifications b = null;
    private boolean c = true;
    private boolean d;
    private boolean e;
    private int f;
    private String g;
    private String h;
    private long i;
    private AtomicBoolean j = new AtomicBoolean(false);
    private final Runnable k;

    public MagicNotifications() {
        this.k = new Runnable(this){
            final /* synthetic */ MagicNotifications a;
            {
                this.a = magicNotifications;
            }

            public void run() {
                this.a.b(MagicNetwork.d().getApplicationContext());
            }
        };
    }

    public static MagicNotifications a() {
        if (b == null) {
            b = new MagicNotifications();
        }
        return b;
    }

    public static void a(int n) {
        MagicNetwork.d().setIconResource(n);
    }

    private void a(Context context, boolean bl, String string2, String string3, boolean bl2) {
        int n = PackageInfoUtils.a(context);
        Log.c(a, "update - updating MagicNotification info in SharedPreferences; value of registrationId is: " + string2);
        context.getApplicationContext().getSharedPreferences("notification_prefs", 0).edit().putBoolean("pref_notifications_on", bl).putBoolean("pref_notification_enabled", bl2).putBoolean("gcm_used", true).putInt("notification_app_ver", n).putString("notification_reg_id", string2).putLong("player_id", UserManager.a().g()).putString("device_id", string3).apply();
        this.d = bl;
        this.e = bl2;
        this.f = n;
        this.g = string2;
        this.i = UserManager.a().g();
        this.h = string3;
    }

    private void a(GoogleCloudMessaging googleCloudMessaging, Context context) {
        if (this.j.getAndSet(true)) {
            Log.d(a, "registerInBackground - registration already in progress; aborting duplicate call");
            return;
        }
        new AsyncTask<Void, Void, Void>(this, googleCloudMessaging, context){
            final /* synthetic */ GoogleCloudMessaging a;
            final /* synthetic */ Context b;
            final /* synthetic */ MagicNotifications c;
            {
                this.c = magicNotifications;
                this.a = googleCloudMessaging;
                this.b = context;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            protected /* varargs */ Void a(Void ... object) {
                try {
                    String string2 = MagicNetwork.d().getAdvertisingId(true);
                    object = string2;
                    if (string2 == null) {
                        object = MagicNetwork.d().getDeviceId();
                    }
                    string2 = this.a.register(new String[]{com.smule.android.notifications.GCMIntentService.a()});
                    Log.c(MagicNotifications.d(), "registerInBackground - device registered; registration ID = " + string2);
                    boolean bl = NotificationManagerCompat.from((Context)this.b).areNotificationsEnabled();
                    if (!com.smule.android.network.managers.PushTokenManager.a().a(string2, (String)object, bl).c()) return null;
                    Log.c(MagicNotifications.d(), "registerInBackground - network call successful; registration ID = " + string2 + ", device ID = " + (String)object);
                    MagicNotifications.a(this.c, this.b, MagicNotifications.a(this.c), string2, (String)object, bl);
                    return null;
                }
                catch (java.io.IOException iOException) {
                    Log.c(MagicNotifications.d(), "registerInBackground - IOException thrown while registering", iOException);
                    return null;
                }
                catch (java.lang.Exception exception) {
                    Log.d(MagicNotifications.d(), "registerInBackground - failed to perform registration", exception);
                    return null;
                }
            }

            protected void a(Void void_) {
                super.onPostExecute((Object)void_);
                MagicNotifications.b(this.c).set(false);
            }

            protected /* synthetic */ Object doInBackground(Object[] arrobject) {
                return this.a((Void[])arrobject);
            }

            protected /* synthetic */ void onPostExecute(Object object) {
                this.a((Void)object);
            }
        }.execute((Object[])new Void[]{null, null, null});
    }

    static /* synthetic */ void a(MagicNotifications magicNotifications, Context context, boolean bl, String string2, String string3, boolean bl2) {
        magicNotifications.a(context, bl, string2, string3, bl2);
    }

    static /* synthetic */ void a(MagicNotifications magicNotifications, GoogleCloudMessaging googleCloudMessaging, Context context) {
        magicNotifications.a(googleCloudMessaging, context);
    }

    static /* synthetic */ boolean a(MagicNotifications magicNotifications) {
        return magicNotifications.d;
    }

    static /* synthetic */ boolean a(MagicNotifications magicNotifications, Context context) {
        return magicNotifications.d(context);
    }

    static /* synthetic */ String b(MagicNotifications magicNotifications, Context context) {
        return magicNotifications.c(context);
    }

    static /* synthetic */ AtomicBoolean b(MagicNotifications magicNotifications) {
        return magicNotifications.j;
    }

    static /* synthetic */ Runnable c(MagicNotifications magicNotifications) {
        return magicNotifications.k;
    }

    private String c(Context context) {
        String string2;
        this.e(context);
        if (this.g.isEmpty()) {
            Log.c(a, "Registration not found.");
            return "";
        }
        if (this.i != UserManager.a().g()) {
            Log.c(a, "Player ID changed: " + this.i + " changed to " + UserManager.a().g());
            return "";
        }
        int n = PackageInfoUtils.a(context);
        if (this.f != n) {
            Log.c(a, "App version changed was " + this.f + " changed to " + n + ".");
            return "";
        }
        Log.c(a, "App version was not changed");
        String string3 = string2 = MagicNetwork.d().getAdvertisingId(true);
        if (TextUtils.isEmpty((CharSequence)string2)) {
            string3 = MagicNetwork.d().getDeviceId();
        }
        if (!string3.equals(this.h) && string3.startsWith("a:")) {
            Log.c(a, "Device ID changed was " + this.h + " changed to " + string3 + ".");
            return "";
        }
        Log.c(a, "Device ID was not changed");
        boolean bl = NotificationManagerCompat.from((Context)context).areNotificationsEnabled();
        if (this.e != bl) {
            Log.c(a, "Push Notification setting changed: was " + this.e + " changed to " + bl + ".");
            return "";
        }
        Log.c(a, "Push Notification setting was not changed");
        return this.g;
    }

    static /* synthetic */ String d() {
        return a;
    }

    private boolean d(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
            return true;
        }
        return false;
    }

    private void e(Context context) {
        Log.c(a, "readPrefs - begin; mNeedPrefs = " + this.c);
        if (this.c) {
            context = context.getApplicationContext().getSharedPreferences("notification_prefs", 0);
            this.d = context.getBoolean("pref_notifications_on", true);
            this.e = context.getBoolean("pref_notification_enabled", true);
            this.f = context.getInt("notification_app_ver", 0);
            this.g = context.getString("notification_reg_id", "");
            this.h = context.getString("device_id", "");
            this.i = context.getLong("player_id", 0);
            this.c = false;
        }
        Log.c(a, "readPrefs - end value of registration id is: " + this.g);
    }

    public void a(Context context) {
        MagicNetwork.a(new Runnable(this, context){
            final /* synthetic */ Context a;
            final /* synthetic */ MagicNotifications b;
            {
                this.b = magicNotifications;
                this.a = context;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                if (!MagicNotifications.a(this.b, this.a)) {
                    String string2;
                    int n = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.a);
                    switch (n) {
                        default: {
                            string2 = "Unknown response code: " + n;
                            break;
                        }
                        case 1: {
                            string2 = "Play services are missing";
                            break;
                        }
                        case 2: {
                            string2 = "Play services require an update";
                            break;
                        }
                        case 3: {
                            string2 = "Play services are disabled";
                            break;
                        }
                        case 9: {
                            string2 = "Play services returned 'invalid'";
                        }
                    }
                    Log.d(MagicNotifications.d(), "register failed: " + string2);
                    return;
                }
                GoogleCloudMessaging googleCloudMessaging = GoogleCloudMessaging.getInstance((Context)this.a);
                if (MagicNotifications.b(this.b, this.a).isEmpty()) {
                    Log.c(MagicNotifications.d(), "register - registering device for push notifications.");
                    MagicNotifications.a(this.b, googleCloudMessaging, this.a);
                    return;
                }
                Log.c(MagicNotifications.d(), "register - registrationId is not empty, so not registering again");
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Context context, Bundle object) {
        AppDelegate appDelegate = MagicNetwork.d();
        if (object == null) {
            object = new Bundle();
        }
        object = appDelegate.getParamsFromBundle((Bundle)object);
        appDelegate = new Intent("android.intent.action.VIEW", object.a);
        appDelegate.setData(object.a);
        appDelegate.putExtra("PARAMS", object.e);
        appDelegate.addFlags(335544320);
        MagicNetwork.d().postNotification(context, object, (Intent)appDelegate);
    }

    public void b() {
        this.e(MagicNetwork.d().getApplicationContext());
        Observer observer = new Observer(this){
            final /* synthetic */ MagicNotifications a;
            private final android.os.Handler b;
            {
                this.a = magicNotifications;
                this.b = new android.os.Handler(android.os.Looper.getMainLooper());
            }

            public void update(java.util.Observable observable, Object object) {
                this.b.removeCallbacks(MagicNotifications.c(this.a));
                this.b.postDelayed(MagicNotifications.c(this.a), java.util.concurrent.TimeUnit.SECONDS.toMillis(5));
            }
        };
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", observer);
        NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", observer);
    }

    public void b(Context context) {
        if (this.d) {
            this.a(context);
        }
    }

    public String c() {
        return this.g;
    }
}

