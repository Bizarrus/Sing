package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.AppDelegate;
import com.smule.android.AppDelegate.ExternalID;
import com.smule.android.AppDelegate.NotificationParams;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.MagicDevice;
import com.smule.chat.Chat.Type;
import com.smule.singandroid.chat.ChatNotification;
import com.smule.singandroid.chat.ChatNotification.Builder;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.DeepLink.Hosts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SingDelegate extends AppDelegate {
    public static final String f7110a = SingDelegate.class.getName();

    public SingDelegate() {
        super(SingApplication.m8798f().getApplicationContext());
    }

    public String getFacebookAppId() {
        return this.mContext.getString(C1947R.string.facebook_app_id);
    }

    public String getAppUID() {
        return SingApplication.m8800h();
    }

    public String getDeviceId() {
        return MagicDevice.a(this.mContext);
    }

    public String getAndroidId() {
        return MagicDevice.b(this.mContext);
    }

    public String getAdvertisingId(boolean z) {
        return MagicDevice.a(this.mContext, z);
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public String getServerHost() {
        return SingApplication.m8798f().getString(C1947R.string.server_host);
    }

    public String getVideoServerHost() {
        return SingApplication.m8798f().getString(C1947R.string.video_server_host);
    }

    public int getFileUploaderServiceThreadCount() {
        return SingApplication.m8798f().getResources().getInteger(C1947R.integer.video_upload_threads);
    }

    public String getAppVersion() {
        return "4.4.9";
    }

    public Set<String> getProgressedSongsUids() {
        return new HashSet();
    }

    public List<String> getBundledContent() {
        return new ArrayList();
    }

    public List<String> getBundledEntitlements() {
        return new ArrayList();
    }

    public List<String> getBundledListings() {
        return new ArrayList();
    }

    public List<String> getAppSettingIDs() {
        String appUID = getAppUID();
        return Arrays.asList(new String[]{appUID + ".stream_values", appUID + ".user_messages", appUID + ".subscriptions", appUID + ".buy_msg", appUID + ".tutorial", appUID + ".offers", appUID + ".get_more_credits", appUID + ".cccp", appUID + ".songbook", appUID + ".preSing", appUID + ".ftuxBlocking", appUID + ".ads", appUID + ".songUpsell", appUID + ".explore", "sing.profile", "sing.audioConfig", "sing.arr", "sing.suggestions", "sing.onboarding", "sing.cccp", "sing.acappella", "sing.video", "sing.chat", "sing.search", "sing.videoFX", "sing.softPaywall", "sing.avqSurvey", "sing.paywall", "dfp", "mopub", "appLaunch", "nativeAds", "fullScreenAds", "crashReporting", "sing.crm"});
    }

    public String getSettingsAppName() {
        return "sing_google";
    }

    public List<String> getSupportedVerbTypes() {
        return Arrays.asList(new String[]{"COMMENT", "LOVE", "FOLLOW", "JOIN", "OPENCALL", "PERFORM"});
    }

    public HashSet<String> getAppsInFamily() {
        return new HashSet(Arrays.asList(new String[]{"sing", "sing_google"}));
    }

    public String getPreferencesFileName() {
        return "sing_prefs";
    }

    public void showNetworkError(String str) {
        ((SingApplication) this.mContext.getApplicationContext()).m8815a(str);
    }

    public Uri getDefaultDeepLink() {
        return DeepLink.a(Hosts.a, null);
    }

    public void showConnectionError() {
        ((SingApplication) this.mContext.getApplicationContext()).m8815a(this.mContext.getResources().getString(C1947R.string.login_cannot_connect_to_smule));
    }

    public String getExternalID(ExternalID externalID) {
        switch (3.a[externalID.ordinal()]) {
            case 1:
                return SingApplication.m8798f().getString(C1947R.string.crittercism_id);
            case 2:
                return "293071437640";
            default:
                return null;
        }
    }

    public int getArrangementPrice() {
        return SingServerValues.c();
    }

    public void postNotification(Context context, NotificationParams notificationParams, Intent intent) {
        Object obj;
        Throwable th;
        if (notificationParams.e != null) {
            try {
                Map map = (Map) JsonUtils.a().readValue(notificationParams.e, Map.class);
                Object obj2 = (map.containsKey("ac") || map.containsKey("pc")) ? 1 : null;
                try {
                    SingApplication.m8792a(m8822a((String) map.get("ac"), 0));
                    SingApplication.m8795b(m8822a((String) map.get("pc"), 0));
                    obj = obj2;
                } catch (Throwable e) {
                    th = e;
                    obj = obj2;
                    Log.m7775d(f7110a, "Failed to parse push notification params " + notificationParams.e, th);
                    if (ChatUtils.m8906a()) {
                    }
                    m8823a(context, notificationParams, intent);
                    return;
                }
            } catch (Throwable e2) {
                Throwable th2 = e2;
                obj = null;
                th = th2;
                Log.m7775d(f7110a, "Failed to parse push notification params " + notificationParams.e, th);
                if (ChatUtils.m8906a()) {
                }
                m8823a(context, notificationParams, intent);
                return;
            }
        }
        obj = null;
        if (ChatUtils.m8906a() || !(DeepLink.a(notificationParams.a) || DeepLink.b(notificationParams.a))) {
            m8823a(context, notificationParams, intent);
            return;
        }
        Type type;
        if (DeepLink.a(notificationParams.a)) {
            type = Type.a;
        } else {
            type = Type.b;
        }
        new Handler(Looper.getMainLooper()).post(new 1(this, type, notificationParams));
        if (notificationParams.g == null) {
            Log.m7776e(f7110a, "Running default notification code because there wasn't a tag");
            m8823a(context, notificationParams, intent);
            return;
        }
        if (obj == null && notificationParams.c == null && notificationParams.d == null) {
            Log.m7776e(f7110a, "Notification param doesn't have ac/pc or header/message");
        }
        new Builder(context).a(intent).a(notificationParams.b).b(notificationParams.c).c(notificationParams.d).a(this.mIconResource).d(notificationParams.f).a().a(notificationParams.g, new 2(this, context, notificationParams));
    }

    private void m8823a(Context context, NotificationParams notificationParams, Intent intent) {
        try {
            super.postNotification(context, notificationParams, intent);
        } catch (Throwable e) {
            MagicCrittercism.m7779a("postNotification:params:" + notificationParams + " uri:" + (notificationParams != null ? notificationParams.a : null) + " intent:" + intent);
            MagicCrittercism.m7780a(new DroidSing7729Exception("DroidSing7729Exception:stack overflow error").initCause(e));
        }
    }

    public void onNotificationDismissed(Context context, String str, int i) {
        if (str != null) {
            Log.m7770b(f7110a, "Clearing notification with tag: " + str);
            ChatNotification.a(context, str);
        }
    }

    public void registerDebugCommands(Context context) {
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    private int m8822a(String str, int i) {
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public boolean supportsGuestSubscriptions() {
        return false;
    }

    public List<String> getFacebookReadPermissions() {
        return Arrays.asList(new String[]{"user_birthday", "email", "user_friends"});
    }

    public List<String> getFacebookPublishPermissions() {
        return Arrays.asList(new String[]{"publish_actions"});
    }
}
