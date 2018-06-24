/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.v4.app.NotificationCompat
 *  android.support.v4.app.NotificationCompat$Builder
 *  android.util.Pair
 *  com.nostra13.universalimageloader.core.assist.ImageSize
 *  org.json.JSONObject
 */
package com.smule.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.smule.android.AppDelegate;
import com.smule.android.R;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.MagicDevice;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public abstract class AppDelegate {
    public static final String NOTIFICATION_ID_KEY = "com.smule.NOTIFICATION_ID_KEY";
    public static final String NOTIFICATION_TAG_KEY = "com.smule.NOTIFICATION_TAG_KEY";
    public static final int NO_LIMIT_MY_SONGS = -1;
    public static final String TAG = AppDelegate.class.getSimpleName();
    protected final Context mContext;
    protected int mIconResource;

    protected AppDelegate(Context context) {
        this.mContext = context;
        this.init();
    }

    public boolean allowGooglePlus() {
        return false;
    }

    public abstract String getAdvertisingId(boolean var1);

    public abstract String getAndroidId();

    public abstract List<String> getAppSettingIDs();

    public abstract String getAppUID();

    public abstract String getAppVersion();

    public abstract Context getApplicationContext();

    public abstract HashSet<String> getAppsInFamily();

    public abstract int getArrangementPrice();

    public boolean getBoostEnabled() {
        return false;
    }

    public abstract List<String> getBundledContent();

    public abstract List<String> getBundledEntitlements();

    public abstract List<String> getBundledListings();

    public abstract Uri getDefaultDeepLink();

    public abstract String getDeviceId();

    public abstract String getExternalID( var1);

    public abstract String getFacebookAppId();

    public abstract List<String> getFacebookPublishPermissions();

    public abstract List<String> getFacebookReadPermissions();

    public int getFileUploaderServiceThreadCount() {
        return 1;
    }

    public abstract int getFileUploaderServiceUploadsDialogThreshold();

    public abstract boolean getFileUploaderServiceWifiOnly();

    public int getMaxMySongArrs() {
        return -1;
    }

    public  getParamsFromBundle(Bundle bundle) {
        return new Object(bundle){
            public final Uri a;
            public final String b;
            public final String c;
            public final String d;
            public final String e;
            public final String f;
            public final String g;
            {
                String string2 = (String)bundle.get("DESTINATION_URI");
                this.a = string2 != null ? Uri.parse((String)string2) : MagicNetwork.d().getDefaultDeepLink();
                this.b = (String)bundle.get("MESSAGE");
                this.c = (String)bundle.get("HEADER");
                this.d = (String)bundle.get("MESSAGE");
                this.e = (String)bundle.get("PARAMS");
                this.f = (String)bundle.get("ICON");
                this.g = (String)bundle.get("TAG");
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public String toString() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    String string2 = this.a == null ? "null" : this.a.toString();
                    jSONObject.put("uri", (Object)string2);
                    jSONObject.put("tickerText", (Object)this.b);
                    jSONObject.put("header", (Object)this.c);
                    jSONObject.put("msg", (Object)this.d);
                    jSONObject.put("params", (Object)this.e);
                    jSONObject.put("icon", (Object)this.f);
                    jSONObject.put("tag", (Object)this.g);
                    return jSONObject.toString();
                }
                catch (Exception exception) {
                    return super.toString();
                }
            }
        };
    }

    public abstract String getPreferencesFileName();

    public String getProgressedCompType(String string2) {
        return null;
    }

    public abstract Set<String> getProgressedSongsUids();

    public abstract String getServerHost();

    public abstract String getSettingsAppName();

    public abstract String[] getSupportedLanguages();

    public abstract List<String> getSupportedVerbTypes();

    public String getVideoServerHost() {
        return null;
    }

    public abstract String getWebServerHost();

    public native void init();

    public Boolean limitAdTrack() {
        return MagicDevice.c(this.mContext);
    }

    public void logAppboyEvent(String string2, List<Pair<String, String>> list) {
    }

    public void onNotificationDismissed(Context context, String string2, int n) {
    }

    public void postNotification(Context context, int n, Notification notification) {
        this.postNotification(context, null, n, notification);
    }

    public void postNotification(Context context,  notificationParams, Intent intent) {
        if (notificationParams.a == null || notificationParams.c == null || notificationParams.d == null) {
            return;
        }
        intent = PendingIntent.getActivity((Context)context, (int)0, (Intent)intent, (int)134217728);
        intent = new NotificationCompat.Builder(context).setSmallIcon(this.mIconResource).setAutoCancel(true).setTicker((CharSequence)notificationParams.d).setWhen(System.currentTimeMillis()).setContentText((CharSequence)notificationParams.d).setContentTitle((CharSequence)notificationParams.c).setContentIntent((PendingIntent)intent);
        if (notificationParams.f != null) {
            int n = (int)context.getResources().getDimension(R.dimen.notification_large_icon_size);
            ImageSize imageSize = new ImageSize(n, n);
            new Handler(Looper.getMainLooper()).post(new Runnable(this, notificationParams, imageSize, context, (NotificationCompat.Builder)intent){
                final /* synthetic */  a;
                final /* synthetic */ ImageSize b;
                final /* synthetic */ Context c;
                final /* synthetic */ NotificationCompat.Builder d;
                final /* synthetic */ AppDelegate e;
                {
                    this.e = appDelegate;
                    this.a = notificationParams;
                    this.b = imageSize;
                    this.c = context;
                    this.d = builder;
                }

                public void run() {
                    com.nostra13.universalimageloader.core.ImageLoader.a().a(this.a.f, this.b, new com.nostra13.universalimageloader.core.listener.ImageLoadingListener(this){
                        final /* synthetic */  a;
                        {
                            this.a = var1_1;
                        }

                        public void a(String string2, android.view.View view) {
                            com.smule.android.logging.Log.b(AppDelegate.TAG, "onLoadingStarted");
                        }

                        public void a(String string2, android.view.View view, android.graphics.Bitmap bitmap) {
                            this.a.d.setLargeIcon(bitmap);
                            this.a.e.postNotification(this.a.c, this.a.e.getAppUID().hashCode(), this.a.d.build());
                        }

                        public void a(String string2, android.view.View view, com.nostra13.universalimageloader.core.assist.FailReason failReason) {
                            this.a.e.postNotification(this.a.c, this.a.e.getAppUID().hashCode(), this.a.d.build());
                        }

                        public void b(String string2, android.view.View view) {
                            this.a.e.postNotification(this.a.c, this.a.e.getAppUID().hashCode(), this.a.d.build());
                        }
                    });
                }
            });
            return;
        }
        this.postNotification(context, this.getAppUID().hashCode(), intent.build());
    }

    public void postNotification(Context context, String string2, int n, Notification notification) {
        Intent intent = new Intent("com.smule.android.notifications.DISMISSED");
        intent.putExtra("com.smule.NOTIFICATION_TAG_KEY", string2);
        intent.putExtra("com.smule.NOTIFICATION_ID_KEY", n);
        notification.deleteIntent = PendingIntent.getBroadcast((Context)this.getApplicationContext(), (int)0, (Intent)intent, (int)268435456);
        ((NotificationManager)context.getSystemService("notification")).notify(string2, n, notification);
    }

    public abstract void registerDebugCommands(Context var1);

    public void setIconResource(int n) {
        this.mIconResource = n;
    }

    public boolean shouldEnforceSession() {
        return true;
    }

    public abstract void showConnectionError();

    public abstract void showNetworkError(String var1);

    public boolean supportsGuestSubscriptions() {
        return true;
    }

    public boolean useConsolidatedGuestLogin() {
        return true;
    }

}

