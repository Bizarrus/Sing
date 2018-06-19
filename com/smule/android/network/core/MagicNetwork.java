package com.smule.android.network.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.smule.android.AppDelegate;
import com.smule.android.debug.RequestRecorder;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2.ErrorDomain;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkUtils.ProgressListener;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.Call;
import okhttp3.Call.Factory;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.AdvIdInterceptor;
import retrofit2.CommonInterceptor;
import retrofit2.ConsolidatedGuestLoginInterceptor;
import retrofit2.DigestInterceptor;
import retrofit2.ExceptionsInterceptor;
import retrofit2.GuestUserInterceptor;
import retrofit2.HostInterceptor;
import retrofit2.MsgIdInterceptor;
import retrofit2.NptInterceptor;
import retrofit2.ReleaseLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.RetryInterceptor;
import retrofit2.SessionInterceptor;
import retrofit2.SnpAdapter.SnpAdapterFactory;
import retrofit2.SnpConverterFactory;
import retrofit2.SnpOkClient;
import retrofit2.SnpResponseInterceptor;
import retrofit2.StandardParametersInterceptor;
import retrofit2.UserAgentInterceptor;

public class MagicNetwork {
    private static OkHttpClient f6721A;
    private static MagicCookieStore f6722B;
    private static MagicCookieHandler f6723C;
    public static final List<Protocol> f6724c = Collections.singletonList(Protocol.b);
    private static final String f6725d = MagicNetwork.class.getName();
    private static String f6726e = null;
    private static String f6727f = null;
    private static final String f6728g = VERSION.RELEASE;
    private static MagicNetwork f6729u;
    private static Handler f6730v = new Handler(Looper.getMainLooper());
    private static Retrofit f6731w;
    private static OkHttpClient f6732x;
    private static OkHttpClient f6733y;
    private static OkHttpClient f6734z;
    private String f6735D;
    private String f6736E;
    private String f6737F;
    public boolean f6738a = true;
    public AtomicLong f6739b;
    private Context f6740h;
    private AppDelegate f6741i;
    private String f6742j;
    private long f6743k;
    private long f6744l;
    private AtomicBoolean f6745m;
    private boolean f6746n;
    private Object f6747o = new Object();
    private boolean f6748p;
    private boolean f6749q;
    private LaunchModeType f6750r = LaunchModeType.a;
    private ScheduledThreadPoolExecutor f6751s = null;
    private Options f6752t = new Options();

    public static synchronized MagicNetwork m7789a() {
        MagicNetwork magicNetwork;
        synchronized (MagicNetwork.class) {
            magicNetwork = f6729u;
        }
        return magicNetwork;
    }

    public static String m7798b() {
        return m7804d().getAppUID();
    }

    public static String m7801c() {
        return f6726e;
    }

    public static AppDelegate m7804d() {
        return m7789a().f6741i;
    }

    public static SharedPreferences m7806e() {
        return m7804d().getApplicationContext().getSharedPreferences(m7804d().getClass().getName(), 0);
    }

    public static String m7807f() {
        return f6722B.a();
    }

    private static Runnable m7797b(Runnable runnable) {
        return new 1(runnable);
    }

    public static Future<?> m7790a(Runnable runnable) {
        return m7789a().f6751s.submit(m7797b(runnable));
    }

    public static ScheduledFuture<?> m7791a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return m7789a().f6751s.scheduleWithFixedDelay(m7797b(runnable), j, j2, timeUnit);
    }

    public static ScheduledFuture<?> m7792a(Runnable runnable, long j, TimeUnit timeUnit) {
        return m7789a().f6751s.schedule(m7797b(runnable), j, timeUnit);
    }

    public static Handler m7808g() {
        return f6730v;
    }

    public static void m7794a(AppDelegate appDelegate, Options options, Interceptor interceptor) {
        boolean z = true;
        if (f6729u == null) {
            f6729u = new MagicNetwork();
            f6729u.f6740h = appDelegate.getApplicationContext();
            f6729u.f6741i = appDelegate;
            f6729u.f6751s = new ScheduledThreadPoolExecutor(3);
            f6726e = appDelegate.getServerHost();
            f6727f = appDelegate.getVideoServerHost();
            if (options != null) {
                f6729u.f6752t = options;
            }
            SharedPreferences sharedPreferences = f6729u.f6740h.getSharedPreferences("network", 0);
            f6729u.f6742j = sharedPreferences.getString("session", null);
            f6729u.f6743k = sharedPreferences.getLong("session_time", 0);
            f6729u.f6744l = sharedPreferences.getLong("session_ttl", 0);
            f6729u.f6746n = true;
            f6729u.f6739b = new AtomicLong(sharedPreferences.getLong("message_id", f6729u.m7814w()) + 1000);
            f6729u.f6745m = new AtomicBoolean(false);
            f6729u.m7815x();
            f6729u.m7805d(true);
            m7796a(interceptor);
            RequestRecorder.a().d();
            f6729u.f6749q = AppSettingsManager.m7855a().m7881a("appLaunch", "resumeSession", true);
            MagicNetwork magicNetwork = f6729u;
            if (f6729u.f6749q) {
                z = false;
            }
            magicNetwork.f6748p = z;
            Log.m7770b(f6725d, "fast launch enabled:" + f6729u.f6749q);
        }
    }

    private static void m7796a(Interceptor interceptor) {
        Builder a = new Builder().b(false).a(new ConnectionPool(5, 20, TimeUnit.SECONDS)).a(f6724c);
        f6722B = new MagicCookieStore();
        f6723C = new MagicCookieHandler(f6722B);
        CookieHandler.setDefault(f6723C);
        a.a(new JavaNetCookieJar(f6723C));
        a.b(new ReleaseLoggingInterceptor());
        List select = ProxySelector.getDefault().select(URI.create("http://www.smule.com"));
        if (select.size() > 0 && select.get(0) != Proxy.NO_PROXY) {
            a.a((Proxy) select.get(0));
        }
        Interceptor userAgentInterceptor = new UserAgentInterceptor(f6729u.m7818a("resdl"));
        f6721A = a.a(userAgentInterceptor).a(60000, TimeUnit.MILLISECONDS).b(60000, TimeUnit.MILLISECONDS).c(60000, TimeUnit.MILLISECONDS).b();
        a.a().remove(userAgentInterceptor);
        String str = "http://" + f6726e;
        Factory snpOkClient = new SnpOkClient();
        Builder b = a.a(new RetryInterceptor(snpOkClient, str)).a(new ExceptionsInterceptor()).a(new NptInterceptor(snpOkClient, str)).a(new AdvIdInterceptor(snpOkClient, str)).a(new SessionInterceptor(snpOkClient, str)).a(new GuestUserInterceptor(snpOkClient, str)).a(new MsgIdInterceptor(snpOkClient, str)).a(new StandardParametersInterceptor(snpOkClient, f6729u.f6735D, str)).a(new HostInterceptor()).a(new CommonInterceptor(snpOkClient, str)).a(new ConsolidatedGuestLoginInterceptor(snpOkClient, str)).a(new DigestInterceptor(snpOkClient, str)).a(new SnpResponseInterceptor(snpOkClient, str)).b(RequestRecorder.a().f());
        if (interceptor != null) {
            b.a(interceptor);
        }
        f6732x = b.a(15000, TimeUnit.MILLISECONDS).b(15000, TimeUnit.MILLISECONDS).c(15000, TimeUnit.MILLISECONDS).b();
        f6733y = b.a(600000, TimeUnit.MILLISECONDS).b(600000, TimeUnit.MILLISECONDS).c(600000, TimeUnit.MILLISECONDS).b();
        f6734z = b.a(0, TimeUnit.MILLISECONDS).b(0, TimeUnit.MILLISECONDS).c(0, TimeUnit.MILLISECONDS).b();
        snpOkClient.setClients(f6732x, f6733y, f6734z);
        f6731w = new Retrofit.Builder().baseUrl("http://" + f6726e).callFactory(snpOkClient).addCallAdapterFactory(new SnpAdapterFactory()).addConverterFactory(new SnpConverterFactory(JsonUtils.a())).build();
    }

    public <T> T m7817a(Class<T> cls) {
        return f6731w.create(cls);
    }

    public static void m7793a(AppDelegate appDelegate) {
        m7794a(appDelegate, null, null);
    }

    public static void m7795a(NetworkResponse networkResponse) {
        if (networkResponse != null) {
            String str;
            String str2 = f6725d;
            StringBuilder append = new StringBuilder().append("unexpected response: ");
            if (networkResponse.f6764j != null) {
                str = networkResponse.f6764j;
            } else {
                str = "empty body string; " + (networkResponse.f6757c != null ? networkResponse.f6757c : "empty message string");
            }
            Log.m7776e(str2, append.append(str).toString());
            if (networkResponse.f6757c != null) {
                m7789a().f6741i.showNetworkError(networkResponse.f6757c);
            }
        }
    }

    private boolean m7812u() {
        return UserManager.m8111a().m8205g() == 0 && this.f6752t.a;
    }

    public boolean m7821h() {
        return this.f6745m.get();
    }

    public boolean m7822i() {
        return this.f6746n;
    }

    public void m7823j() throws IllegalStateException {
        m7819a(false);
    }

    public void m7819a(boolean z) throws IllegalStateException {
        if (this.f6748p) {
            m7800b(z);
        } else {
            m7803c(z);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m7800b(boolean r4) throws java.lang.IllegalStateException {
        /*
        r3 = this;
        monitor-enter(r3);
        if (r4 != 0) goto L_0x000b;
    L_0x0003:
        r0 = r3.m7825l();	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r3);
        return;
    L_0x000b:
        r0 = r3.f6745m;	 Catch:{ all -> 0x0036 }
        r1 = 1;
        r0.set(r1);	 Catch:{ all -> 0x0036 }
        r0 = com.smule.android.network.managers.UserManager.m8111a();	 Catch:{ all -> 0x0083 }
        r0 = r0.m8140C();	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0039;
    L_0x001b:
        r0 = com.smule.android.utils.NotificationCenter.a();	 Catch:{ all -> 0x0083 }
        r1 = "AUTO_LOGIN_FAILED";
        r2 = com.smule.android.network.managers.UserManager.m8111a();	 Catch:{ all -> 0x0083 }
        r2 = r2.m8141D();	 Catch:{ all -> 0x0083 }
        r0.a(r1, r2);	 Catch:{ all -> 0x0083 }
    L_0x002c:
        r0 = 0;
        r3.f6738a = r0;	 Catch:{ all -> 0x0036 }
        r0 = r3.f6745m;	 Catch:{ all -> 0x0036 }
        r1 = 0;
        r0.set(r1);	 Catch:{ all -> 0x0036 }
        goto L_0x0009;
    L_0x0036:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0039:
        r0 = com.smule.android.network.managers.UserManager.m8111a();	 Catch:{ all -> 0x0083 }
        r0 = r0.m8231z();	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x009b;
    L_0x0043:
        r0 = r3.f6749q;	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0093;
    L_0x0047:
        r0 = r3.f6738a;	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0093;
    L_0x004b:
        r0 = r3.m7825l();	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x008e;
    L_0x0051:
        r0 = com.smule.android.network.core.MagicNetwork.LaunchModeType.b;	 Catch:{ all -> 0x0083 }
        r3.f6750r = r0;	 Catch:{ all -> 0x0083 }
    L_0x0055:
        r0 = f6725d;	 Catch:{ all -> 0x0083 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r1.<init>();	 Catch:{ all -> 0x0083 }
        r2 = "fastReLogin:";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0083 }
        r2 = r3.f6750r;	 Catch:{ all -> 0x0083 }
        r2 = r2.a();	 Catch:{ all -> 0x0083 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0083 }
        r1 = r1.toString();	 Catch:{ all -> 0x0083 }
        com.smule.android.logging.Log.m7770b(r0, r1);	 Catch:{ all -> 0x0083 }
        r0 = com.smule.android.network.managers.UserManager.m8111a();	 Catch:{ all -> 0x0083 }
        r0.m8146J();	 Catch:{ all -> 0x0083 }
        r0 = new com.smule.android.network.core.MagicNetwork$2;	 Catch:{ all -> 0x0083 }
        r0.<init>(r3);	 Catch:{ all -> 0x0083 }
        m7790a(r0);	 Catch:{ all -> 0x0083 }
        goto L_0x002c;
    L_0x0083:
        r0 = move-exception;
        r1 = 0;
        r3.f6738a = r1;	 Catch:{ all -> 0x0036 }
        r1 = r3.f6745m;	 Catch:{ all -> 0x0036 }
        r2 = 0;
        r1.set(r2);	 Catch:{ all -> 0x0036 }
        throw r0;	 Catch:{ all -> 0x0036 }
    L_0x008e:
        r0 = com.smule.android.network.core.MagicNetwork.LaunchModeType.c;	 Catch:{ all -> 0x0083 }
        r3.f6750r = r0;	 Catch:{ all -> 0x0083 }
        goto L_0x0055;
    L_0x0093:
        r0 = com.smule.android.network.managers.UserManager.m8111a();	 Catch:{ all -> 0x0083 }
        r0.m8147K();	 Catch:{ all -> 0x0083 }
        goto L_0x002c;
    L_0x009b:
        r0 = com.smule.android.network.managers.UserManager.m8111a();	 Catch:{ all -> 0x0083 }
        r1 = r3.m7812u();	 Catch:{ all -> 0x0083 }
        r0.m8161a(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.android.network.core.MagicNetwork.b(boolean):void");
    }

    private void m7803c(boolean z) throws IllegalStateException {
        if (z || !m7825l()) {
            m7800b(z);
        }
    }

    private Object m7813v() {
        return this.f6748p ? this : this.f6747o;
    }

    public void m7820b(NetworkResponse networkResponse) {
        if (networkResponse.f6761g == null) {
            m7805d(false);
        } else if (networkResponse.f6756b != 0) {
            m7805d(false);
        } else {
            if (networkResponse.f6761g.equals(m7824k())) {
                synchronized (m7813v()) {
                    this.f6746n = false;
                }
                m7805d(false);
                return;
            }
            synchronized (m7813v()) {
                this.f6742j = networkResponse.f6761g;
                this.f6739b = new AtomicLong(m7814w());
                this.f6746n = false;
                this.f6743k = System.currentTimeMillis() / 1000;
                if (networkResponse.f6762h - 30 < 0) {
                    this.f6744l = networkResponse.f6762h;
                } else {
                    this.f6744l = networkResponse.f6762h - 30;
                }
            }
            this.f6740h.getSharedPreferences("network", 0).edit().putString("session", networkResponse.f6761g).putLong("session_time", this.f6743k).putLong("session_ttl", this.f6744l).apply();
            m7805d(true);
            Log.m7772c(f6725d, "Session updated to " + networkResponse.f6761g + "/" + this.f6739b.get());
        }
    }

    private void m7805d(boolean z) {
        long j = this.f6739b.get();
        if (z || j % 5 == 0) {
            this.f6740h.getSharedPreferences("network", 0).edit().putLong("message_id", j).apply();
            Log.m7770b(f6725d, "persistMessageId - messageId persisted to " + j);
        }
    }

    private long m7814w() {
        return (long) new SecureRandom().nextInt(1000);
    }

    public String m7824k() {
        String str;
        synchronized (m7813v()) {
            str = this.f6742j;
        }
        return str;
    }

    public boolean m7825l() {
        boolean z;
        synchronized (m7813v()) {
            z = !TextUtils.isEmpty(this.f6742j) && System.currentTimeMillis() / 1000 < this.f6743k + this.f6744l;
        }
        return z;
    }

    public void m7826m() {
        if (!this.f6745m.get()) {
            synchronized (m7813v()) {
                this.f6742j = null;
            }
        }
    }

    public static String m7809n() {
        String advertisingId = m7804d().getAdvertisingId(true);
        return advertisingId != null ? advertisingId : m7804d().getDeviceId();
    }

    public StreamResponse m7816a(String str, ProgressListener progressListener) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        String str2;
        try {
            Request b = new Request.Builder().a(m7799b(str)).b();
            Call newCall = f6721A.newCall(b);
            Response b2 = newCall.b();
            int c = b2.c();
            if (c != 200) {
                str2 = "";
                try {
                    str2 = NetworkUtils.a(b2, progressListener);
                } catch (RuntimeException e) {
                    Log.m7770b(f6725d, "API Call was cancelled");
                }
                EventLogger2.m7733a(str, System.currentTimeMillis() - currentTimeMillis, NetworkUtils.a(b), 0, ErrorDomain.b, c, null, str2, null, false);
                throw new ServerException(b2, str2);
            }
            EventLogger2.m7733a(str, System.currentTimeMillis() - currentTimeMillis, NetworkUtils.a(b), NetworkUtils.b(b2), ErrorDomain.a, c, null, null, null, false);
            StreamResponse streamResponse = new StreamResponse();
            streamResponse.a = b;
            streamResponse.b = b2;
            streamResponse.d = b2.h().byteStream();
            streamResponse.c = newCall;
            String a = b2.a("Content-Length");
            if (a != null) {
                try {
                    streamResponse.e = Long.parseLong(a);
                } catch (NumberFormatException e2) {
                    streamResponse.e = -1;
                }
            } else {
                streamResponse.e = -1;
            }
            return streamResponse;
        } catch (Throwable e3) {
            Log.m7775d(f6725d, "getStreamResponseFromUrl", e3);
            throw e3;
        } catch (IOException e4) {
            str2 = e4.toString() + " " + e4.getCause();
            EventLogger2.m7733a(str, System.currentTimeMillis() - currentTimeMillis, 0, 0, ErrorDomain.e, 0, "resourcedownload", str2, null, false);
            throw e4;
        }
    }

    public Long m7827o() {
        return Long.valueOf(this.f6739b.incrementAndGet());
    }

    public Long m7828p() {
        return Long.valueOf(this.f6739b.addAndGet(100));
    }

    private String m7799b(String str) throws IOException {
        if ((!str.startsWith("http://" + f6726e) && !str.startsWith("https://" + f6726e)) || str.contains("session=")) {
            return str;
        }
        String str2;
        String k = m7824k();
        if (k == null) {
            m7823j();
            k = m7824k();
            if (k == null) {
                throw new IOException("Failed to connect to smule server " + f6726e);
            }
        }
        if (str.contains("?")) {
            str2 = str + "&";
        } else {
            str2 = str + "?";
        }
        return str2 + "session=" + URLEncoder.encode(k, "UTF-8");
    }

    public static void m7802c(NetworkResponse networkResponse) {
        String a = networkResponse.m7846a("upgradeUrl");
        if (a == null || a.length() == 0) {
            Log.m7776e(f6725d, "Upgrade required returned without an upgrade url!");
        } else {
            NotificationCenter.a().a("MagicNetwork.UPGRADE_REQUIRED_EVENT", a);
        }
    }

    public static void m7810q() {
        NotificationCenter.a().b("MagicNetwork.SERVER_MAINTENANCE_EVENT", new Object[0]);
    }

    private void m7815x() {
        String str;
        try {
            str = this.f6740h.getPackageManager().getPackageInfo(this.f6740h.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            str = "unknown";
        }
        this.f6736E = this.f6740h.getPackageName() + "/" + str;
        this.f6737F = f6728g + "," + Build.MODEL + "," + Locale.getDefault().toString();
        this.f6735D = this.f6736E + " (" + this.f6737F + ")";
    }

    public String m7818a(String str) {
        return this.f6736E + " (" + this.f6737F + "," + str + ")";
    }

    public LaunchModeType m7829r() {
        return this.f6750r;
    }

    public boolean m7830s() {
        return this.f6749q;
    }
}
