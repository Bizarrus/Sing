package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import com.facebook.FacebookSdk;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.utils.C1938L;
import com.smule.SmuleApplication;
import com.smule.SmuleApplication.ApplicationLifecycleListener;
import com.smule.android.AppDelegate;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.ads.vendors.mopub.MagicMoPubFullScreenAdMediatorAdapter;
import com.smule.android.ads.vendors.mopub.MagicMoPubNativeAdMediatorAdapter;
import com.smule.android.console.CmdInfo;
import com.smule.android.console.commands.AppSettingsCmd;
import com.smule.android.logging.AnalyticsProcessor;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.BalanceManager;
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
import com.smule.android.utils.UncaughtExceptionHelper.Listener;
import com.smule.android.utils.VorgomUtils;
import com.smule.chat.ChatConfiguration;
import com.smule.chat.ChatManager;
import com.smule.singandroid.chat.ChatAnalyticsMonitor;
import com.smule.singandroid.chat.ChatNotificationListener;
import com.smule.singandroid.console.AudioDebugCmd;
import com.smule.singandroid.console.DelaySliderCmd;
import com.smule.singandroid.console.SessionsCmd;
import com.smule.singandroid.console.StreamVerCmd;
import com.smule.singandroid.utils.BuildUtils.Flavor;
import com.smule.singandroid.utils.ChatUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SingApplication extends SmuleApplication {
    public static boolean f7089d = false;
    public static boolean f7090e = false;
    public static boolean f7091f = false;
    public static Boolean f7092g = Boolean.valueOf(false);
    public static final Map<String, Locale> f7093k = new HashMap();
    private static final String f7094l = SingApplication.class.getName();
    private static int f7095m = 0;
    private static int f7096n = 0;
    private static SingApplication f7097o = null;
    private static OperationLoader f7098p = new OperationLoader();
    final FileFilter f7099h = new C19536(this);
    final FileFilter f7100i = new C19547(this);
    public int f7101j = 0;
    private Handler f7102q;
    private ChatManager f7103r;
    private Runnable f7104s = new C19481(this);
    private Locale f7105t;
    private boolean f7106u;
    private ChatNotificationListener f7107v;
    private final ApplicationLifecycleListener f7108w = new C19492(this);
    private RefWatcher f7109x;

    class C19481 implements Runnable {
        final /* synthetic */ SingApplication f7082a;

        C19481(SingApplication singApplication) {
            this.f7082a = singApplication;
        }

        public void run() {
            if (this.f7082a.f7103r != null && this.f7082a.f7103r.m8574a()) {
                Log.m7772c(SingApplication.f7094l, "Disconnecting after timeout");
                this.f7082a.f7103r.m8572a(false);
            }
        }
    }

    class C19492 implements ApplicationLifecycleListener {
        final /* synthetic */ SingApplication f7083a;

        C19492(SingApplication singApplication) {
            this.f7083a = singApplication;
        }

        public void mo4104a() {
            this.f7083a.f7102q.removeCallbacks(this.f7083a.f7104s);
            Toaster.m8434c();
        }

        public void mo4105b() {
            if (ChatUtils.m8906a()) {
                ChatAnalyticsMonitor.m8827a().m8829b();
                this.f7083a.f7102q.postDelayed(this.f7083a.f7104s, TimeUnit.MINUTES.toMillis((long) this.f7083a.getResources().getInteger(C1947R.integer.chat_disconnect_timeout)));
            }
            Toaster.m8433b();
        }
    }

    class C19503 implements Listener {
        final /* synthetic */ SingApplication f7084a;

        C19503(SingApplication singApplication) {
            this.f7084a = singApplication;
        }

        public void mo4106a(Throwable th, boolean z) {
            MagicCrittercism.m7779a("RefMon:" + ReferenceMonitor.m8403a().m8410b());
        }
    }

    class C19514 extends SimpleActivityLifecycleCallbacks {
        final /* synthetic */ SingApplication f7085a;

        C19514(SingApplication singApplication) {
            this.f7085a = singApplication;
            super(singApplication);
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            AnalyticsProcessor.m7716b("app_time");
            Log.m7770b(SingApplication.f7094l, "onActivityCreated");
        }
    }

    static class C19525 implements ChatConfiguration {
        C19525() {
        }

        public Context mo4108a() {
            return SingApplication.f7097o.getApplicationContext();
        }

        public SharedPreferences mo4109b() {
            return mo4108a().getSharedPreferences("chat", 0);
        }

        public long mo4110c() {
            return (long) SingApplication.f7097o.getResources().getInteger(C1947R.integer.team_smule_account);
        }

        public String[] mo4111d() {
            String[] stringArray;
            int i = 0;
            Resources resources = SingApplication.f7097o.getResources();
            String f = mo4113f();
            if (f == null || f.isEmpty()) {
                stringArray = resources.getStringArray(C1947R.array.chat_welcome_text_array);
            } else {
                stringArray = resources.getStringArray(C1947R.array.chat_welcome_text_video_array);
            }
            String[] strArr = new String[]{"{support_url}"};
            CharSequence[] charSequenceArr = new String[]{resources.getString(C1947R.string.sing_android_help_url)};
            while (i < stringArray.length) {
                stringArray[i] = TextUtils.replace(stringArray[i], strArr, charSequenceArr).toString();
                i++;
            }
            return stringArray;
        }

        public String[] mo4112e() {
            return SingApplication.f7097o.getResources().getStringArray(C1947R.array.chat_welcome_reply_text_array);
        }

        public String mo4113f() {
            if (SingApplication.m8806o()) {
                return AppSettingsManager.m7855a().m7876a("sing.chat", "welcomePerfKey", null);
            }
            return null;
        }
    }

    class C19536 implements FileFilter {
        final /* synthetic */ SingApplication f7086a;

        C19536(SingApplication singApplication) {
            this.f7086a = singApplication;
        }

        public boolean accept(File file) {
            return file != null && (file.getName().endsWith("wav") || file.getName().endsWith("wavform") || file.getName().endsWith("wav.json"));
        }
    }

    class C19547 implements FileFilter {
        final /* synthetic */ SingApplication f7087a;

        C19547(SingApplication singApplication) {
            this.f7087a = singApplication;
        }

        public boolean accept(File file) {
            return file != null && file.getName().matches("[0-9]*");
        }
    }

    public static class SmuleFileNameGenerator implements FileNameGenerator {
        static final Pattern f7088a = Pattern.compile("[^a-z0-9_-]{1,64}");

        public String mo4114a(@NonNull String str) {
            Uri parse = Uri.parse(str);
            if (parse != null && parse.getHost() != null && !parse.getHost().contains("smule.com")) {
                return String.valueOf(str.hashCode());
            }
            String replaceAll = f7088a.matcher(str.substring(str.lastIndexOf("/") + 1)).replaceAll("");
            return TextUtils.isEmpty(replaceAll) ? UUID.randomUUID().toString() : replaceAll;
        }
    }

    static {
        f7093k.put("de", Locale.GERMANY);
        f7093k.put("en", Locale.US);
        f7093k.put("es", new Locale("es", "ES"));
        f7093k.put("fr", Locale.FRANCE);
        f7093k.put("in", new Locale("in", "ID"));
        f7093k.put("ja", new Locale("ja", "JP", "JP"));
        f7093k.put("ms", new Locale("ms", "MY"));
        f7093k.put("ko", Locale.KOREA);
        f7093k.put("pt", new Locale("pt", "PT"));
        f7093k.put("zh", Locale.SIMPLIFIED_CHINESE);
        f7093k.put("th", new Locale("th", "TH"));
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public void onCreate() {
        AnalyticsProcessor.m7716b("app_create_time");
        super.onCreate();
        f7097o = this;
        AppDelegate singDelegate = new SingDelegate();
        MagicCrittercism.m7778a(this, singDelegate, m8814y());
        SingCoreBridge.loadLibrary(this);
        this.f7109x = LeakCanary.m8913a(this);
        f7092g = Boolean.valueOf(getResources().getBoolean(C1947R.bool.monkey_test));
        FacebookSdk.setLegacyTokenUpgradeSupported(true);
        android.util.Log.i(f7094l, "onCreate");
        android.util.Log.i(f7094l, "Build time: 2017/06/14 06:04:16 PM");
        android.util.Log.i(f7094l, "Is vorgom: " + VorgomUtils.m8443c());
        android.util.Log.i(f7094l, "Is debug: false");
        MagicNetwork.m7793a(singDelegate);
        UserManager.m8112a((Context) this);
        BalanceManager.m7927a().m7934a((Context) this);
        m8809t();
        m8813x();
        if (m8821q()) {
            MagicNetwork.m7789a().m7826m();
            UserManager.m8111a().m8202e();
            if (UserManager.m8111a().m8231z()) {
                AppSettingsManager.m7855a().m7884b();
            }
        }
        MagicNotifications.m8297a((int) C1947R.drawable.icn_push_notification);
        m8810u();
        UserManager.m8111a().m8228w();
        m7651c().m8438a(OutOfMemoryError.class, new C19503(this));
        HardwareSpecificUtils.m8350a(this);
        ImageLoaderConfiguration a = new Builder(getApplicationContext()).m7629a(new LruMemoryCache(2097152)).m7628a(new SmuleFileNameGenerator()).m7627a(52428800).m7632b(1000).m7630a(ImageUtils.m8355a(getApplicationContext())).m7631a();
        C1938L.m7638a(false);
        ImageLoader.m7591a().m7594a(a);
        LocalizationManager.m7947a((Context) this);
        CmdInfo.m7663a(new DelaySliderCmd(this));
        CmdInfo.m7663a(new AppSettingsCmd(this));
        CmdInfo.m7663a(new AudioDebugCmd(this));
        CmdInfo.m7663a(new StreamVerCmd(this));
        CmdInfo.m7663a(new SessionsCmd(this));
        MagicMoPubNativeAdMediatorAdapter.registerWithFactory(this);
        MagicMoPubFullScreenAdMediatorAdapter.registerWithFactory(this);
        CalligraphyConfig.m8953a(new CalligraphyConfig.Builder().m8950a("fonts/ProximaNova-Regular.ttf").m8949a((int) C1947R.attr.fontPath).m8951a());
        StringCacheManager.m8413a().m8419a((Context) this);
        this.f7102q = new Handler(getMainLooper());
        m7649a(this.f7108w);
        m8811v();
        registerActivityLifecycleCallbacks(new C19514(this));
        AnalyticsProcessor.m7717c("app_create_time");
        Log.m7770b(f7094l, "create time:" + AnalyticsProcessor.m7718d("app_create_time"));
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        MagicCrittercism.m7779a("RefMon:" + ReferenceMonitor.m8403a().m8410b());
    }

    private void m8809t() {
        GoogleV3Billing.m8324a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtYoCYiBv1buUmnmUGi+1CzsBTyzYpfCuFPwYf/3oyVm1KYjrDCNm8z1n7hEQ9zAUGwz5yMh12froSeLC1lXPNSb/D0EGoeMg/M3w2x38HFdh9+mWyaQZZne7iTA4cZHHUzPEFia1z7bIFYxRoixZJFSWC1OOLbSrm/K2pjAkHqcQA61AoValxTQQJZoSDeBTarr4ICmI9dV88CEJfJ3ZzUQHmDRfBz+BDTfxFPZk+XLVmi8idVrayn8Uz5MhTWBqXuuLM6Uje1jmuAUtZolIhewWpe3QAQiudzbf9YUVARIcY2cw2WsjSyMOLmq81PznO9rhmZ++OOsTRRdpoHGNsQIDAQAB");
        SubscriptionManager.m8066a().m8081a((Context) this);
        SubscriptionManager.m8066a().m8083a(null);
    }

    private void m8810u() {
        SmuleApplication.m7646a(m8798f(), m8814y(), true);
        EventLogger2.m7731a(SmuleApplication.m7647b());
    }

    private void m8811v() {
        String str;
        if (Flavor.Prod.m8891a() || Flavor.ProdBeta.m8891a()) {
            str = "production";
        } else {
            str = "sandbox";
        }
        AdjustAttributionSettings adjustAttributionSettings = new AdjustAttributionSettings("t8qnlqbba41s", str);
        adjustAttributionSettings.m7659b("blxnrn");
        adjustAttributionSettings.m7657a("h5ziuo");
        m7650a(adjustAttributionSettings);
    }

    public static OperationLoader m8797d() {
        return f7098p;
    }

    public boolean m8818e() {
        return ((AudioManager) getSystemService("audio")).isWiredHeadsetOn();
    }

    public void m8815a(String str) {
        Toaster.m8430a((Context) this, str);
    }

    public static Context m8798f() {
        return f7097o;
    }

    public static SingApplication m8799g() {
        return f7097o;
    }

    public static String m8800h() {
        return m8798f().getString(C1947R.string.google_app_uid);
    }

    public static String m8801i() {
        return "credit_google";
    }

    public static synchronized ChatManager m8802j() {
        ChatManager chatManager;
        synchronized (SingApplication.class) {
            if (f7097o.f7103r == null) {
                ChatConfiguration c19525;
                if (ChatUtils.m8906a()) {
                    c19525 = new C19525();
                    f7097o.f7103r = new ChatManager(c19525);
                    ChatAnalyticsMonitor.m8827a().m8828a(f7097o.f7103r);
                    f7097o.f7107v = new ChatNotificationListener(m8798f(), f7097o.f7103r);
                } else {
                    c19525 = new C19525();
                    f7097o.f7103r = new ChatManager(c19525);
                    ChatAnalyticsMonitor.m8827a().m8828a(f7097o.f7103r);
                    f7097o.f7107v = new ChatNotificationListener(m8798f(), f7097o.f7103r);
                }
            }
            chatManager = f7097o.f7103r;
        }
        return chatManager;
    }

    public void m8819k() {
        m8793a(getCacheDir(), this.f7099h);
        m8793a(getFilesDir(), this.f7099h);
        m8793a(getCacheDir(), this.f7100i);
    }

    private void m8793a(File file, FileFilter fileFilter) {
        if (file != null && file.isDirectory()) {
            for (File file2 : file.listFiles(fileFilter)) {
                if (!file2.delete()) {
                    Log.m7767a(f7094l, "Failed to delete resource: " + file2);
                }
            }
        }
    }

    public static int m8803l() {
        return f7095m;
    }

    public static void m8792a(int i) {
        Log.m7772c(f7094l, "setNumNewActivityNotifications called with: " + i);
        f7095m = i;
    }

    public static int m8804m() {
        return f7096n;
    }

    public static void m8795b(int i) {
        Log.m7772c(f7094l, "setNumNewInvitesFromFollowingNotifications called with: " + i);
        f7096n = i;
    }

    private static boolean m8812w() {
        if (VERSION.SDK_INT < 19) {
            return false;
        }
        if (VERSION.SDK_INT == 19 && (VERSION.RELEASE == null || VERSION.RELEASE.isEmpty() || VERSION.RELEASE.equals("4.4") || VERSION.RELEASE.equals("4.4.0") || VERSION.RELEASE.equals("4.4.1"))) {
            return false;
        }
        return true;
    }

    public static boolean m8805n() {
        if (f7097o == null) {
            return false;
        }
        Log.m7770b(f7094l, "MANUFACTURER:" + Build.MANUFACTURER);
        Log.m7770b(f7094l, "PRODUCT:" + Build.PRODUCT);
        Log.m7770b(f7094l, "MODEL:" + Build.MODEL);
        if (m8812w() && DeviceSettings.m8671q()) {
            return true;
        }
        return false;
    }

    public static boolean m8806o() {
        return m8812w() && f7097o != null && DeviceSettings.m8670p();
    }

    @Nullable
    public Locale m8820p() {
        if (this.f7105t == null) {
            this.f7105t = m8817b(getSharedPreferences(SingApplication.class.getName(), 0).getString("custom_locale", null));
        }
        return this.f7105t;
    }

    @Nullable
    public Locale m8817b(String str) {
        Locale locale;
        if (f7093k.containsKey(str)) {
            locale = (Locale) f7093k.get(str);
        } else {
            locale = Locale.getDefault();
        }
        this.f7105t = locale;
        return this.f7105t;
    }

    public void m8816a(Locale locale) {
        Editor edit = getSharedPreferences(SingApplication.class.getName(), 0).edit();
        if (locale == null || locale.equals(Locale.getDefault())) {
            edit.remove("custom_locale").apply();
        } else {
            edit.putString("custom_locale", locale.getLanguage()).apply();
        }
    }

    private void m8813x() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            if (packageInfo == null) {
                this.f7106u = false;
                return;
            }
            boolean z;
            long j = packageInfo.firstInstallTime;
            long j2 = packageInfo.lastUpdateTime;
            SharedPreferences sharedPreferences = m8798f().getSharedPreferences(MasterActivity.class.getName(), 0);
            String string = sharedPreferences.getString("APP_VERSION_STARTUP", "");
            sharedPreferences.edit().putString("APP_VERSION_STARTUP", packageInfo.versionName).apply();
            if (j == j2 || string.equals(packageInfo.versionName)) {
                z = false;
            } else {
                z = true;
            }
            this.f7106u = z;
        } catch (NameNotFoundException e) {
            Log.m7774d(f7094l, "couldn't get package info name:" + getPackageName());
            this.f7106u = false;
        }
    }

    public boolean m8821q() {
        return this.f7106u;
    }

    private int m8814y() {
        return getResources().getInteger(C1947R.integer.crittercism_sample_percentage);
    }
}
