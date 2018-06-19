package com.smule.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.Builder;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.smule.android.utils.MagicDevice;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AppDelegate {
    public static final String NOTIFICATION_ID_KEY = "com.smule.NOTIFICATION_ID_KEY";
    public static final String NOTIFICATION_TAG_KEY = "com.smule.NOTIFICATION_TAG_KEY";
    public static final int NO_LIMIT_MY_SONGS = -1;
    public static final String TAG = AppDelegate.class.getSimpleName();
    protected final Context mContext;
    protected int mIconResource;

    public abstract String getAdvertisingId(boolean z);

    public abstract String getAndroidId();

    public abstract List<String> getAppSettingIDs();

    public abstract String getAppUID();

    public abstract String getAppVersion();

    public abstract Context getApplicationContext();

    public abstract HashSet<String> getAppsInFamily();

    public abstract int getArrangementPrice();

    public abstract List<String> getBundledContent();

    public abstract List<String> getBundledEntitlements();

    public abstract List<String> getBundledListings();

    public abstract Uri getDefaultDeepLink();

    public abstract String getDeviceId();

    public abstract String getExternalID(ExternalID externalID);

    public abstract String getFacebookAppId();

    public abstract List<String> getFacebookPublishPermissions();

    public abstract List<String> getFacebookReadPermissions();

    public abstract String getPreferencesFileName();

    public abstract Set<String> getProgressedSongsUids();

    public abstract String getServerHost();

    public abstract String getSettingsAppName();

    public abstract List<String> getSupportedVerbTypes();

    public native void init();

    public abstract void registerDebugCommands(Context context);

    public abstract void showConnectionError();

    public abstract void showNetworkError(String str);

    protected AppDelegate(Context context) {
        this.mContext = context;
        init();
    }

    public Boolean limitAdTrack() {
        return MagicDevice.c(this.mContext);
    }

    public String getVideoServerHost() {
        return null;
    }

    public int getFileUploaderServiceThreadCount() {
        return 1;
    }

    public void setIconResource(int i) {
        this.mIconResource = i;
    }

    public NotificationParams getParamsFromBundle(Bundle bundle) {
        return new NotificationParams(bundle);
    }

    public void postNotification(Context context, NotificationParams notificationParams, Intent intent) {
        if (notificationParams.a != null && notificationParams.c != null && notificationParams.d != null) {
            Builder contentIntent = new Builder(context).setSmallIcon(this.mIconResource).setAutoCancel(true).setTicker(notificationParams.d).setWhen(System.currentTimeMillis()).setContentText(notificationParams.d).setContentTitle(notificationParams.c).setContentIntent(PendingIntent.getActivity(context, 0, intent, 134217728));
            if (notificationParams.f != null) {
                int dimension = (int) context.getResources().getDimension(R.dimen.notification_large_icon_size);
                new Handler(Looper.getMainLooper()).post(new 1(this, notificationParams, new ImageSize(dimension, dimension), context, contentIntent));
                return;
            }
            postNotification(context, getAppUID().hashCode(), contentIntent.build());
        }
    }

    public void onNotificationDismissed(Context context, String str, int i) {
    }

    public void postNotification(Context context, int i, Notification notification) {
        postNotification(context, null, i, notification);
    }

    public void postNotification(Context context, String str, int i, Notification notification) {
        Intent intent = new Intent("com.smule.android.notifications.DISMISSED");
        intent.putExtra(NOTIFICATION_TAG_KEY, str);
        intent.putExtra(NOTIFICATION_ID_KEY, i);
        notification.deleteIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 268435456);
        ((NotificationManager) context.getSystemService("notification")).notify(str, i, notification);
    }

    public int getMaxMySongArrs() {
        return -1;
    }

    public String getProgressedCompType(String str) {
        return null;
    }

    public boolean shouldEnforceSession() {
        return true;
    }

    public boolean useConsolidatedGuestLogin() {
        return true;
    }

    public boolean allowGooglePlus() {
        return false;
    }

    public boolean supportsGuestSubscriptions() {
        return true;
    }
}
