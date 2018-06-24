/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Looper
 *  android.text.TextUtils
 *  android.util.Pair
 *  com.appboy.models.outgoing.AppboyProperties
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.DeepLink$Hosts
 */
package com.smule.singandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.appboy.models.outgoing.AppboyProperties;
import com.smule.android.AppDelegate;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.NotificationBadgeManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.MagicDevice;
import com.smule.chat.Chat;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingDelegate;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.chat.ChatNotification;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.DeepLink;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SingDelegate
extends com.smule.android.AppDelegate {
    public static final String a = SingDelegate.class.getName();

    public SingDelegate() {
        super(SingApplication.g().getApplicationContext());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Context context, AppDelegate notificationParams, Intent intent) {
        try {
            super.postNotification(context, notificationParams, intent);
            return;
        }
        catch (StackOverflowError stackOverflowError) {
            StringBuilder stringBuilder = new StringBuilder().append("postNotification:params:").append(notificationParams).append(" uri:");
            context = notificationParams != null ? notificationParams.a : null;
            MagicCrittercism.a(stringBuilder.append((Object)context).append(" intent:").append((Object)intent).toString());
            MagicCrittercism.a(new Throwable("DroidSing7729Exception:stack overflow error"){}.initCause(stackOverflowError));
            return;
        }
    }

    @Override
    public String getAdvertisingId(boolean bl) {
        return MagicDevice.a(this.mContext, bl);
    }

    @Override
    public String getAndroidId() {
        return MagicDevice.b(this.mContext);
    }

    @Override
    public List<String> getAppSettingIDs() {
        String string2 = this.getAppUID();
        return Arrays.asList(string2 + ".stream_values", string2 + ".user_messages", string2 + ".subscriptions", string2 + ".buy_msg", string2 + ".tutorial", string2 + ".offers", string2 + ".get_more_credits", string2 + ".cccp", string2 + ".songbook", string2 + ".preSing", string2 + ".ftuxBlocking", string2 + ".ads", string2 + ".songUpsell", string2 + ".explore", "sing.profile", "sing.audioConfig", "sing.arr", "sing.suggestions", "sing.onboarding", "sing.cccp", "sing.acappella", "sing.video", "sing.chat", "sing.search", "sing.videoFX", "sing.videoStyles", "sing.softPaywall", "sing.avqSurvey", "sing.paywall", "sing.freeform", "dfp", "mopub", "appLaunch", "nativeAds", "fullScreenAds", "crashReporting", "sing.crm", "sing.boost", "sing.share", "sing.feed", "sing.findFriendsModule", "sing.registration", "sing.localization", "sing.seeds");
    }

    @Override
    public String getAppUID() {
        return SingApplication.i();
    }

    @Override
    public String getAppVersion() {
        return "5.7.5";
    }

    @Override
    public Context getApplicationContext() {
        return this.mContext;
    }

    @Override
    public HashSet<String> getAppsInFamily() {
        return new HashSet<String>(Arrays.asList("sing", "sing_google"));
    }

    @Override
    public int getArrangementPrice() {
        return new SingServerValues().c();
    }

    @Override
    public boolean getBoostEnabled() {
        return new SingServerValues().ac();
    }

    @Override
    public List<String> getBundledContent() {
        return new ArrayList<String>();
    }

    @Override
    public List<String> getBundledEntitlements() {
        return new ArrayList<String>();
    }

    @Override
    public List<String> getBundledListings() {
        return new ArrayList<String>();
    }

    @Override
    public Uri getDefaultDeepLink() {
        return DeepLink.a((DeepLink.Hosts)DeepLink.Hosts.a, (String)null);
    }

    @Override
    public String getDeviceId() {
        return MagicDevice.a(this.mContext);
    }

    @Override
    public String getExternalID(AppDelegate externalID) {
        switch (.a[externalID.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return SingApplication.g().getString(2131297703);
            }
            case 2: 
        }
        return "293071437640";
    }

    @Override
    public String getFacebookAppId() {
        return this.mContext.getString(2131297718);
    }

    @Override
    public List<String> getFacebookPublishPermissions() {
        return Arrays.asList("publish_actions");
    }

    @Override
    public List<String> getFacebookReadPermissions() {
        return Arrays.asList("user_birthday", "email", "user_friends");
    }

    @Override
    public int getFileUploaderServiceThreadCount() {
        return SingApplication.g().getResources().getInteger(2131623979);
    }

    @Override
    public int getFileUploaderServiceUploadsDialogThreshold() {
        return new SingServerValues().x();
    }

    @Override
    public boolean getFileUploaderServiceWifiOnly() {
        return new SingServerValues().w();
    }

    @Override
    public String getPreferencesFileName() {
        return "sing_prefs";
    }

    @Override
    public Set<String> getProgressedSongsUids() {
        return new HashSet<String>();
    }

    @Override
    public String getServerHost() {
        return SingApplication.g().getString(2131297946);
    }

    @Override
    public String getSettingsAppName() {
        return "sing_google";
    }

    @Override
    public String[] getSupportedLanguages() {
        return this.mContext.getResources().getStringArray(2131230731);
    }

    @Override
    public List<String> getSupportedVerbTypes() {
        return Arrays.asList("COMMENT", "LOVE", "FOLLOW", "JOIN", "OPENCALL", "PERFORM");
    }

    @Override
    public String getVideoServerHost() {
        return SingApplication.g().getString(2131297972);
    }

    @Override
    public String getWebServerHost() {
        return SingApplication.g().getString(2131297973);
    }

    @Override
    public void logAppboyEvent(String string2, List<Pair<String, String>> object) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return;
        }
        AppboyProperties appboyProperties = new AppboyProperties();
        object = object.iterator();
        while (object.hasNext()) {
            Pair pair = (Pair)object.next();
            if (pair.first == null) continue;
            appboyProperties.a((String)pair.first, (String)pair.second);
        }
        SingAppboy.a().a(string2, appboyProperties);
    }

    @Override
    public void onNotificationDismissed(Context context, String string2, int n) {
        if (string2 != null) {
            Log.b(a, "Clearing notification with tag: " + string2);
            ChatNotification.a(context, string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void postNotification(Context context, AppDelegate notificationParams, Intent intent) {
        boolean bl;
        Object object;
        block9 : {
            boolean bl2;
            block8 : {
                block7 : {
                    boolean bl3 = false;
                    bl = false;
                    if (notificationParams.e != null) {
                        bl2 = bl3;
                        try {
                            object = (Map)JsonUtils.a().readValue(notificationParams.e, Map.class);
                            bl2 = bl3;
                            if (object.containsKey("ac")) break block7;
                            bl2 = bl3;
                            if (!object.containsKey("pc")) break block8;
                            break block7;
                        }
                        catch (Exception exception) {
                            bl = bl2;
                            Log.d(a, "Failed to parse push notification params " + notificationParams.e, exception);
                            break block9;
                        }
                    }
                    bl = false;
                    break block9;
                }
                bl = true;
            }
            if (bl) {
                bl2 = bl;
                NotificationBadgeManager.a().a((Map<String, String>)object);
            }
        }
        if (ChatUtils.a() && (DeepLink.a((Uri)notificationParams.a) || DeepLink.b((Uri)notificationParams.a))) {
            object = DeepLink.a((Uri)notificationParams.a) ? Chat.Type.a : Chat.Type.b;
            new Handler(Looper.getMainLooper()).post(new Runnable(this, (Chat.Type)((Object)object), notificationParams){
                final /* synthetic */ Chat.Type a;
                final /* synthetic */ AppDelegate b;
                final /* synthetic */ SingDelegate c;
                {
                    this.c = singDelegate;
                    this.a = type;
                    this.b = notificationParams;
                }

                public void run() {
                    SingApplication.k().a(com.smule.chat.Chat$Bucket.a, this.a, this.b.g);
                }
            });
            if (notificationParams.g == null) {
                Log.e(a, "Running default notification code because there wasn't a tag");
                this.a(context, notificationParams, intent);
                return;
            }
            if (!bl && notificationParams.c == null && notificationParams.d == null) {
                Log.e(a, "Notification param doesn't have ac/pc or header/message");
            }
            new ChatNotification.Builder(context).a(intent).a(notificationParams.b).b(notificationParams.c).c(notificationParams.d).a(this.mIconResource).d(notificationParams.f).a().a(notificationParams.g, new ChatNotification.NotificationReadyCallback(this, context, notificationParams){
                final /* synthetic */ Context a;
                final /* synthetic */ AppDelegate b;
                final /* synthetic */ SingDelegate c;
                {
                    this.c = singDelegate;
                    this.a = context;
                    this.b = notificationParams;
                }

                public void a(android.app.Notification notification) {
                    this.c.postNotification(this.a, this.b.g, this.b.g.hashCode(), notification);
                }
            });
            return;
        }
        this.a(context, notificationParams, intent);
    }

    @Override
    public void registerDebugCommands(Context context) {
    }

    @Override
    public void showConnectionError() {
        String string2 = this.mContext.getResources().getString(2131296895);
        ((SingApplication)this.mContext.getApplicationContext()).a(string2);
    }

    @Override
    public void showNetworkError(String string2) {
        ((SingApplication)this.mContext.getApplicationContext()).a(string2);
    }

    @Override
    public boolean supportsGuestSubscriptions() {
        return false;
    }

}

