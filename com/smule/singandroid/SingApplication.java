/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.media.AudioDeviceInfo
 *  android.media.AudioManager
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.multidex.MultiDex
 *  android.text.TextUtils
 *  android.util.Log
 *  com.facebook.FacebookSdk
 *  com.facebook.appevents.AppEventsLogger
 *  com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator
 *  com.nostra13.universalimageloader.cache.memory.MemoryCache
 *  com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.ImageLoaderConfiguration
 *  com.nostra13.universalimageloader.core.ImageLoaderConfiguration$Builder
 *  com.nostra13.universalimageloader.core.download.ImageDownloader
 *  com.nostra13.universalimageloader.utils.L
 *  com.squareup.leakcanary.LeakCanary
 *  com.squareup.leakcanary.RefWatcher
 *  uk.co.chrisjenx.calligraphy.CalligraphyConfig
 *  uk.co.chrisjenx.calligraphy.CalligraphyConfig$Builder
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.L;
import com.smule.SmuleApplication;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.ads.attribution.MagicAdjustEventLogger;
import com.smule.android.ads.vendors.mopub.MagicMoPubFullScreenAdMediatorAdapter;
import com.smule.android.ads.vendors.mopub.MagicMoPubNativeAdMediatorAdapter;
import com.smule.android.console.CmdInfo;
import com.smule.android.console.commands.AppSettingsCmd;
import com.smule.android.facebook.MagicFacebookEventLogger;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.l10n.LocalizationApplicationDelegate;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.AnalyticsProcessor;
import com.smule.android.logging.EventLog2Listener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.logging.MagicEventLog2Forwarder;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.BalanceManager;
import com.smule.android.network.managers.BoostManager;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.notifications.MagicNotifications;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.utils.HardwareSpecificUtils;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.UncaughtExceptionHelper;
import com.smule.android.utils.VorgomUtils;
import com.smule.chat.ChatConfiguration;
import com.smule.chat.ChatManager;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingCoreBridge;
import com.smule.singandroid.SingDelegate;
import com.smule.singandroid.chat.ChatAnalyticsMonitor;
import com.smule.singandroid.chat.ChatNotificationListener;
import com.smule.singandroid.console.AudioDebugCmd;
import com.smule.singandroid.console.DelaySliderCmd;
import com.smule.singandroid.console.EffectsJsonCmd;
import com.smule.singandroid.console.SessionsCmd;
import com.smule.singandroid.console.StreamVerCmd;
import com.smule.singandroid.utils.BuildUtils;
import com.smule.singandroid.utils.ChatUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SingApplication
extends SmuleApplication {
    public static boolean e;
    public static boolean f;
    public static boolean g;
    public static Boolean h;
    public static final Map<String, Locale> l;
    private static final String m;
    private static SingApplication n;
    private static OperationLoader o;
    LocalizationApplicationDelegate d = new LocalizationApplicationDelegate();
    final FileFilter i;
    final FileFilter j;
    public int k;
    private Handler p;
    private ChatManager q;
    private Runnable r;
    private Locale s;
    private boolean t;
    private MagicEventLog2Forwarder u;
    private MagicEventLog2Forwarder v;
    private ChatNotificationListener w;
    private final SmuleApplication.ApplicationLifecycleListener x;
    private RefWatcher y;

    static {
        m = SingApplication.class.getName();
        e = false;
        f = false;
        g = false;
        h = false;
        n = null;
        o = new OperationLoader();
        l = new HashMap<String, Locale>();
        l.put("de", Locale.GERMANY);
        l.put("en", Locale.US);
        l.put("es", new Locale("es", "ES"));
        l.put("fr", Locale.FRANCE);
        l.put("in", new Locale("in", "ID"));
        l.put("ja", new Locale("ja", "JP", "JP"));
        l.put("ms", new Locale("ms", "MY"));
        l.put("ko", Locale.KOREA);
        l.put("pt", new Locale("pt", "PT"));
        l.put("zh", Locale.SIMPLIFIED_CHINESE);
        l.put("th", new Locale("th", "TH"));
    }

    public SingApplication() {
        this.r = new Runnable(){

            @Override
            public void run() {
                if (SingApplication.this.q != null && SingApplication.this.q.a()) {
                    Log.c(m, "Disconnecting after timeout");
                    SingApplication.this.q.a(false);
                }
            }
        };
        this.x = new SmuleApplication.ApplicationLifecycleListener(){

            @Override
            public void a() {
                SingApplication.this.p.removeCallbacks(SingApplication.this.r);
                Toaster.c();
            }

            @Override
            public void b() {
                if (ChatUtils.a()) {
                    ChatAnalyticsMonitor.a().b();
                    long l = TimeUnit.MINUTES.toMillis(SingApplication.this.getResources().getInteger(2131623942));
                    SingApplication.this.p.postDelayed(SingApplication.this.r, l);
                }
                Toaster.b();
            }
        };
        this.i = new FileFilter(){

            @Override
            public boolean accept(File file) {
                if (file != null && (file.getName().endsWith("wav") || file.getName().endsWith("wavform") || file.getName().endsWith("wav.json") || file.getName().endsWith("wav.bin"))) {
                    return true;
                }
                return false;
            }
        };
        this.j = new FileFilter(){

            @Override
            public boolean accept(File file) {
                if (file != null && file.getName().matches("[0-9]*")) {
                    return true;
                }
                return false;
            }
        };
        this.k = 0;
    }

    private void a(File arrfile, FileFilter object2) {
        if (arrfile != null && arrfile.isDirectory()) {
            for (File file : arrfile.listFiles((FileFilter)object2)) {
                if (file.delete()) continue;
                Log.a(m, "Failed to delete resource: " + file);
            }
        }
    }

    public static OperationLoader d() {
        return o;
    }

    public static Context g() {
        return n;
    }

    public static SingApplication h() {
        return n;
    }

    public static String i() {
        return SingApplication.g().getString(2131297740);
    }

    public static String j() {
        return "credit_google";
    }

    public static ChatManager k() {
        synchronized (SingApplication.class) {
            Object object;
            if (SingApplication.n.q == null) {
                if (!ChatUtils.a()) {
                    // empty if block
                }
                object = new ChatConfiguration(){

                    @Override
                    public Context a() {
                        return n.getApplicationContext();
                    }

                    @Override
                    public SharedPreferences b() {
                        return this.a().getSharedPreferences("chat", 0);
                    }

                    @Override
                    public long c() {
                        return n.getResources().getInteger(2131623976);
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public String[] d() {
                        int n = 0;
                        Object object = n.getResources();
                        String[] arrstring = this.f();
                        arrstring = arrstring != null && !arrstring.isEmpty() ? object.getStringArray(2131230724) : object.getStringArray(2131230723);
                        object = object.getString(2131297948);
                        while (n < arrstring.length) {
                            arrstring[n] = TextUtils.replace((CharSequence)arrstring[n], (String[])new String[]{"{support_url}"}, (CharSequence[])new String[]{object}).toString();
                            ++n;
                        }
                        return arrstring;
                    }

                    @Override
                    public String[] e() {
                        return n.getResources().getStringArray(2131230722);
                    }

                    @Override
                    public String f() {
                        String string2 = null;
                        if (SingApplication.n()) {
                            string2 = AppSettingsManager.a().a("sing.chat", "welcomePerfKey", (String)null);
                        }
                        return string2;
                    }
                };
                SingApplication.n.q = new ChatManager((ChatConfiguration)object);
                ChatAnalyticsMonitor.a().a(SingApplication.n.q);
                SingApplication.n.w = new ChatNotificationListener(SingApplication.g(), SingApplication.n.q);
            }
            object = SingApplication.n.q;
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean m() {
        block3 : {
            block2 : {
                if (n == null) break block2;
                Log.b(m, "MANUFACTURER:" + Build.MANUFACTURER);
                Log.b(m, "PRODUCT:" + Build.PRODUCT);
                Log.b(m, "MODEL:" + Build.MODEL);
                boolean bl = new DeviceSettings().q();
                if (SingApplication.v() && bl) break block3;
            }
            return false;
        }
        return true;
    }

    public static boolean n() {
        boolean bl = new DeviceSettings().p();
        if (SingApplication.v() && n != null && bl) {
            return true;
        }
        return false;
    }

    private void r() {
        GoogleV3Billing.a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtYoCYiBv1buUmnmUGi+1CzsBTyzYpfCuFPwYf/3oyVm1KYjrDCNm8z1n7hEQ9zAUGwz5yMh12froSeLC1lXPNSb/D0EGoeMg/M3w2x38HFdh9+mWyaQZZne7iTA4cZHHUzPEFia1z7bIFYxRoixZJFSWC1OOLbSrm/K2pjAkHqcQA61AoValxTQQJZoSDeBTarr4ICmI9dV88CEJfJ3ZzUQHmDRfBz+BDTfxFPZk+XLVmi8idVrayn8Uz5MhTWBqXuuLM6Uje1jmuAUtZolIhewWpe3QAQiudzbf9YUVARIcY2cw2WsjSyMOLmq81PznO9rhmZ++OOsTRRdpoHGNsQIDAQAB");
        SubscriptionManager.a().a((Context)this);
        SubscriptionManager.a().a((String)null);
        BoostManager.a().a(this.getApplicationContext());
    }

    private void s() {
        SingApplication.a(SingApplication.g(), this.x(), true);
        com.smule.android.logging.EventLogger2.a(SingApplication.b());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void t() {
        Object object = BuildUtils.Flavor.d.a() || BuildUtils.Flavor.c.a() ? "production" : "sandbox";
        object = new AdjustAttributionSettings("t8qnlqbba41s", (String)object);
        object.b("blxnrn");
        object.a("h5ziuo");
        object.a("6hywuu", (Context)this);
        object.a(new long[]{1, 1215262951, 662603325, 1033312039, 301273732});
        this.a((AdjustAttributionSettings)object);
    }

    private void u() {
        final Map map = new HashMap<String, String>();
        map.put((String)"rec_complete", (String)"rec_complete");
        map.put((String)"rec_start", (String)"rec_start");
        map.put((String)"perf_listen", (String)"perf_listen");
        map.put((String)"search_result_clk", (String)"search_result_clk");
        map.put((String)"perf_join_create", (String)"perf_join_create");
        map.put((String)"chat_send", (String)"send");
        map.put((String)"share_ext_clk", (String)"send");
        map.put((String)"share_ext", (String)"send");
        map.put((String)"perf_love", (String)"send");
        map.put((String)"perf_comment", (String)"send");
        map.put((String)"perf_favorite", (String)"send");
        map.put((String)"perf_invite", (String)"send");
        map.put((String)"follow_clk", (String)"send");
        MagicEventLog2Forwarder.Filter filter = new MagicEventLog2Forwarder.Filter(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public boolean a(EventLogger2 event) {
                String string2;
                String string3 = event.b;
                if (!map.containsKey(string3) || (string3.equals("rec_complete") || string3.equals("rec_start")) && !(string2 = event.h).equals(Analytics.b.a()) && !string2.equals(Analytics.c.a()) || string3.equals("follow_clk") && !event.d.equals(Analytics.a.a())) {
                    return false;
                }
                return true;
            }
        };
        this.u = new MagicEventLog2Forwarder(filter, new MagicFacebookEventLogger(SingApplication.g(), map));
        com.smule.android.logging.EventLogger2.a(this.u);
        map = new Hashtable();
        map.put("rec_complete", "fcvciy");
        map.put("rec_start", "d3qfau");
        map.put("perf_listen", "26jxwa");
        map.put("search_result_clk", "t9j2lg");
        map.put("perf_join_create", "2l0fq9");
        map.put("chat_send", "84txij");
        map.put("share_ext_clk", "84txij");
        map.put("share_ext", "84txij");
        map.put("perf_love", "84txij");
        map.put("perf_comment", "84txij");
        map.put("perf_favorite", "84txij");
        map.put("perf_invite", "84txij");
        map.put("follow_clk", "84txij");
        this.v = new MagicEventLog2Forwarder(filter, new MagicAdjustEventLogger(map));
        com.smule.android.logging.EventLogger2.a(this.v);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean v() {
        if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT == 19 && (Build.VERSION.RELEASE == null || Build.VERSION.RELEASE.isEmpty() || Build.VERSION.RELEASE.equals("4.4") || Build.VERSION.RELEASE.equals("4.4.0") || Build.VERSION.RELEASE.equals("4.4.1"))) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void w() {
        boolean bl;
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            if (packageInfo == null) {
                this.t = false;
                return;
            }
            long l = packageInfo.firstInstallTime;
            long l2 = packageInfo.lastUpdateTime;
            SharedPreferences sharedPreferences = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0);
            String string2 = sharedPreferences.getString("APP_VERSION_STARTUP", "");
            sharedPreferences.edit().putString("APP_VERSION_STARTUP", packageInfo.versionName).apply();
            bl = l != l2 && !string2.equals(packageInfo.versionName);
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            Log.d(m, "couldn't get package info name:" + this.getPackageName());
            this.t = false;
            return;
        }
        this.t = bl;
    }

    private int x() {
        return this.getResources().getInteger(2131623953);
    }

    public void a(String string2) {
        Toaster.a((Context)this, string2);
    }

    public void a(Locale locale) {
        SharedPreferences.Editor editor = this.getSharedPreferences(SingApplication.class.getName(), 0).edit();
        if (locale != null && !locale.equals(Locale.getDefault())) {
            editor.putString("custom_locale", locale.getLanguage()).apply();
            return;
        }
        editor.remove("custom_locale").apply();
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(this.d.b(context));
        MultiDex.install((Context)this);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Nullable
    public Locale b(String object) {
        object = l.containsKey(object) ? l.get(object) : Locale.getDefault();
        this.s = object;
        return this.s;
    }

    public boolean e() {
        return ((AudioManager)this.getSystemService("audio")).isWiredHeadsetOn();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean f() {
        AudioDeviceInfo[] arraudioDeviceInfo = (AudioDeviceInfo[])this.getSystemService("audio");
        if (arraudioDeviceInfo == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (arraudioDeviceInfo.isBluetoothA2dpOn()) return true;
            if (!arraudioDeviceInfo.isBluetoothScoOn()) return false;
            return true;
        }
        arraudioDeviceInfo = arraudioDeviceInfo.getDevices(2);
        int n = arraudioDeviceInfo.length;
        int n2 = 0;
        while (n2 < n) {
            AudioDeviceInfo audioDeviceInfo = arraudioDeviceInfo[n2];
            if (audioDeviceInfo.getType() == 8) return true;
            if (audioDeviceInfo.getType() == 7) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public void l() {
        this.a(this.getCacheDir(), this.i);
        this.a(this.getFilesDir(), this.i);
        this.a(this.getCacheDir(), this.j);
    }

    public boolean o() {
        return this.t;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.d.a((Context)this);
    }

    @Override
    public void onCreate() {
        AnalyticsProcessor.b("app_create_time");
        super.onCreate();
        n = this;
        SingDelegate singDelegate = new SingDelegate();
        MagicCrittercism.a((Context)this, singDelegate, this.x());
        SingCoreBridge.loadLibrary((Context)this);
        this.y = LeakCanary.a((Application)this);
        h = this.getResources().getBoolean(2131558416);
        FacebookSdk.setLegacyTokenUpgradeSupported((boolean)true);
        AppEventsLogger.activateApp((Application)this);
        android.util.Log.i((String)m, (String)"onCreate");
        android.util.Log.i((String)m, (String)"Build time: 2018/06/20 09:59:28 PM");
        android.util.Log.i((String)m, (String)("Is vorgom: " + VorgomUtils.c()));
        android.util.Log.i((String)m, (String)"Is debug: false");
        MagicNetwork.a(singDelegate);
        LocaleSettings.a();
        UserManager.a((Context)this);
        BalanceManager.a().a((Context)this);
        this.r();
        this.w();
        if (this.o()) {
            MagicNetwork.a().m();
            UserManager.a().e();
            if (UserManager.a().y()) {
                AppSettingsManager.a().b();
            }
        }
        MagicNotifications.a(2130837977);
        this.s();
        UserManager.a().u();
        this.c().a(OutOfMemoryError.class, new UncaughtExceptionHelper.Listener(){

            @Override
            public void a(Throwable throwable, boolean bl) {
                MagicCrittercism.a("RefMon:" + ReferenceMonitor.a().b());
            }
        });
        HardwareSpecificUtils.a((Context)this);
        singDelegate = new ImageLoaderConfiguration.Builder(this.getApplicationContext()).a((MemoryCache)new LruMemoryCache(2097152)).a((FileNameGenerator)new SmuleFileNameGenerator()).a(52428800).b(1000).a(ImageUtils.a(this.getApplicationContext())).a();
        L.a((boolean)false);
        ImageLoader.a().a((ImageLoaderConfiguration)singDelegate);
        LocalizationManager.a((Context)this);
        CmdInfo.a(new DelaySliderCmd((Context)this));
        CmdInfo.a(new AppSettingsCmd((Context)this));
        CmdInfo.a(new AudioDebugCmd((Context)this));
        CmdInfo.a(new StreamVerCmd((Context)this));
        CmdInfo.a(new SessionsCmd((Context)this));
        CmdInfo.a(new EffectsJsonCmd((Context)this));
        MagicMoPubNativeAdMediatorAdapter.registerWithFactory((Context)this);
        MagicMoPubFullScreenAdMediatorAdapter.registerWithFactory((Context)this);
        CalligraphyConfig.a((CalligraphyConfig)new CalligraphyConfig.Builder().a("fonts/ProximaNova-Regular.ttf").a(2130771974).a());
        StringCacheManager.a().a((Context)this);
        this.p = new Handler(this.getMainLooper());
        this.a(this.x);
        this.t();
        this.u();
        this.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new SmuleApplication.SimpleActivityLifecycleCallbacks(){

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                AnalyticsProcessor.b("app_time");
                Log.b(m, "onActivityCreated");
            }
        });
        AnalyticsProcessor.c("app_create_time");
        Log.b(m, "create time:" + AnalyticsProcessor.d("app_create_time"));
    }

    public void onTrimMemory(int n) {
        super.onTrimMemory(n);
        MagicCrittercism.a("RefMon:" + ReferenceMonitor.a().b());
    }

    private static class SmuleFileNameGenerator
    implements FileNameGenerator {
        static final Pattern a = Pattern.compile("[^a-z0-9_-]{1,64}");

        private SmuleFileNameGenerator() {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public String a(@NonNull String object) {
            Object object2 = Uri.parse((String)object);
            if (object2.getHost() != null && !object2.getHost().contains("smule.com")) {
                return String.valueOf(object.hashCode());
            }
            object = object2 = a.matcher(object.substring(object.lastIndexOf("/") + 1)).replaceAll("");
            if (!TextUtils.isEmpty((CharSequence)object2)) return object;
            return UUID.randomUUID().toString();
        }
    }

}

