/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.NetworkOnMainThreadException
 *  android.text.TextUtils
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  okhttp3.Call
 *  okhttp3.Call$Factory
 *  okhttp3.ConnectionPool
 *  okhttp3.CookieJar
 *  okhttp3.Interceptor
 *  okhttp3.JavaNetCookieJar
 *  okhttp3.OkHttpClient
 *  okhttp3.OkHttpClient$Builder
 *  okhttp3.Protocol
 *  okhttp3.Request
 *  okhttp3.Request$Builder
 *  okhttp3.Response
 *  okhttp3.ResponseBody
 *  retrofit2.AdvIdInterceptor
 *  retrofit2.CallAdapter
 *  retrofit2.CallAdapter$Factory
 *  retrofit2.CommonInterceptor
 *  retrofit2.ConsolidatedGuestLoginInterceptor
 *  retrofit2.Converter
 *  retrofit2.Converter$Factory
 *  retrofit2.DigestInterceptor
 *  retrofit2.ExceptionsInterceptor
 *  retrofit2.GuestUserInterceptor
 *  retrofit2.HostInterceptor
 *  retrofit2.MsgIdInterceptor
 *  retrofit2.NptInterceptor
 *  retrofit2.ReleaseLoggingInterceptor
 *  retrofit2.Retrofit
 *  retrofit2.Retrofit$Builder
 *  retrofit2.RetryInterceptor
 *  retrofit2.SSLHandshakeInterceptor
 *  retrofit2.SessionInterceptor
 *  retrofit2.SnpAdapter
 *  retrofit2.SnpAdapter$SnpAdapterFactory
 *  retrofit2.SnpConverterFactory
 *  retrofit2.SnpOkClient
 *  retrofit2.SnpResponseInterceptor
 *  retrofit2.StandardParametersInterceptor
 *  retrofit2.UserAgentInterceptor
 */
package com.smule.android.network.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.NetworkOnMainThreadException;
import android.text.TextUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smule.android.AppDelegate;
import com.smule.android.debug.RequestRecorder;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.core.DelegatingSSLSocketFactory;
import com.smule.android.network.core.MagicCookieHandler;
import com.smule.android.network.core.MagicCookieStore;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ServerException;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.io.IOException;
import java.io.InputStream;
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
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.AdvIdInterceptor;
import retrofit2.CallAdapter;
import retrofit2.CommonInterceptor;
import retrofit2.ConsolidatedGuestLoginInterceptor;
import retrofit2.Converter;
import retrofit2.DigestInterceptor;
import retrofit2.ExceptionsInterceptor;
import retrofit2.GuestUserInterceptor;
import retrofit2.HostInterceptor;
import retrofit2.MsgIdInterceptor;
import retrofit2.NptInterceptor;
import retrofit2.ReleaseLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.RetryInterceptor;
import retrofit2.SSLHandshakeInterceptor;
import retrofit2.SessionInterceptor;
import retrofit2.SnpAdapter;
import retrofit2.SnpConverterFactory;
import retrofit2.SnpOkClient;
import retrofit2.SnpResponseInterceptor;
import retrofit2.StandardParametersInterceptor;
import retrofit2.UserAgentInterceptor;

public class MagicNetwork {
    private static OkHttpClient A;
    private static OkHttpClient B;
    private static MagicCookieStore C;
    private static MagicCookieHandler D;
    public static final List<Protocol> c;
    private static final String d;
    private static String e;
    private static String f;
    private static final String g;
    private static MagicNetwork u;
    private static Handler v;
    private static Retrofit w;
    private static OkHttpClient x;
    private static OkHttpClient y;
    private static OkHttpClient z;
    private String E;
    private String F;
    private String G;
    public boolean a = true;
    public AtomicLong b;
    private Context h;
    private AppDelegate i;
    private String j;
    private long k;
    private long l;
    private AtomicBoolean m;
    private boolean n;
    private Object o = new Object();
    private boolean p;
    private boolean q;
    private  r = .a;
    private ScheduledThreadPoolExecutor s = null;
    private  t = new Object(){
        public boolean a = true;
    };

    static {
        d = MagicNetwork.class.getName();
        e = null;
        f = null;
        g = Build.VERSION.RELEASE;
        v = new Handler(Looper.getMainLooper());
        c = Collections.singletonList(Protocol.b);
    }

    public static MagicNetwork a() {
        synchronized (MagicNetwork.class) {
            MagicNetwork magicNetwork = u;
            return magicNetwork;
        }
    }

    public static Future<?> a(Runnable runnable) {
        return MagicNetwork.a().s.submit(MagicNetwork.b(runnable));
    }

    public static ScheduledFuture<?> a(Runnable runnable, long l, long l2, TimeUnit timeUnit) {
        return MagicNetwork.a().s.scheduleWithFixedDelay(MagicNetwork.b(runnable), l, l2, timeUnit);
    }

    public static ScheduledFuture<?> a(Runnable runnable, long l, TimeUnit timeUnit) {
        return MagicNetwork.a().s.schedule(MagicNetwork.b(runnable), l, timeUnit);
    }

    public static void a(AppDelegate appDelegate) {
        MagicNetwork.a(appDelegate, null, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(AppDelegate object,  options, Interceptor interceptor) {
        boolean bl = true;
        if (u != null) return;
        u = new MagicNetwork();
        MagicNetwork.u.h = object.getApplicationContext();
        MagicNetwork.u.i = object;
        MagicNetwork.u.s = new ScheduledThreadPoolExecutor(3);
        e = object.getServerHost();
        f = object.getVideoServerHost();
        if (options != null) {
            MagicNetwork.u.t = options;
        }
        object = MagicNetwork.u.h.getSharedPreferences("network", 0);
        MagicNetwork.u.j = object.getString("session", null);
        MagicNetwork.u.k = object.getLong("session_time", 0);
        MagicNetwork.u.l = object.getLong("session_ttl", 0);
        MagicNetwork.u.n = true;
        MagicNetwork.u.b = new AtomicLong(object.getLong("message_id", u.x()) + 1000);
        MagicNetwork.u.m = new AtomicBoolean(false);
        u.y();
        u.d(true);
        MagicNetwork.a(interceptor);
        RequestRecorder.a().d();
        MagicNetwork.u.q = AppSettingsManager.a().a("appLaunch", "resumeSession", true);
        object = u;
        if (MagicNetwork.u.q) {
            bl = false;
        }
        object.p = bl;
        Log.b(d, "fast launch enabled:" + MagicNetwork.u.q);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(NetworkResponse networkResponse) {
        if (networkResponse != null) {
            String string2;
            String string3 = d;
            StringBuilder stringBuilder = new StringBuilder().append("unexpected response: ");
            if (networkResponse.j != null) {
                string2 = networkResponse.j;
            } else {
                StringBuilder stringBuilder2 = new StringBuilder().append("empty body string; ");
                string2 = networkResponse.c != null ? networkResponse.c : "empty message string";
                string2 = stringBuilder2.append(string2).toString();
            }
            Log.e(string3, stringBuilder.append(string2).toString());
            if (networkResponse.c != null) {
                MagicNetwork.a().i.showNetworkError(networkResponse.c);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void a(Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().a(new ConnectionPool(5, 20, TimeUnit.SECONDS)).a(c);
        C = new MagicCookieStore();
        D = new MagicCookieHandler(C);
        CookieHandler.setDefault(D);
        builder.a((CookieJar)new JavaNetCookieJar((CookieHandler)D));
        Object object = new SSLHandshakeInterceptor();
        try {
            builder.a((SSLSocketFactory)new DelegatingSSLSocketFactory(DelegatingSSLSocketFactory.defaultSslSocketFactory(DelegatingSSLSocketFactory.defaultTrustManager()), (SSLHandshakeInterceptor)object));
            builder.a((Interceptor)object);
        }
        catch (Exception exception) {
            Log.d(d, "Failed to init ssl socket factory", exception);
        }
        builder.b((Interceptor)new ReleaseLoggingInterceptor());
        object = ProxySelector.getDefault().select(URI.create("http://www.smule.com"));
        if (object.size() > 0 && object.get(0) != Proxy.NO_PROXY) {
            builder.a((Proxy)object.get(0));
        }
        object = new UserAgentInterceptor(u.a("resdl"));
        B = builder.a((Interceptor)object).a(45000, TimeUnit.MILLISECONDS).b(45000, TimeUnit.MILLISECONDS).c(45000, TimeUnit.MILLISECONDS).b();
        builder.a().remove(object);
        String string2 = "http://" + e;
        object = new SnpOkClient();
        builder = builder.b(false).a((Interceptor)new RetryInterceptor((SnpOkClient)object, string2)).a((Interceptor)new ExceptionsInterceptor()).a((Interceptor)new NptInterceptor((SnpOkClient)object, string2)).a((Interceptor)new AdvIdInterceptor((SnpOkClient)object, string2)).a((Interceptor)new SessionInterceptor((SnpOkClient)object, string2)).a((Interceptor)new GuestUserInterceptor((SnpOkClient)object, string2)).a((Interceptor)new MsgIdInterceptor((SnpOkClient)object, string2)).a((Interceptor)new StandardParametersInterceptor((SnpOkClient)object, MagicNetwork.u.E, string2)).a((Interceptor)new HostInterceptor()).a((Interceptor)new CommonInterceptor((SnpOkClient)object, string2)).a((Interceptor)new ConsolidatedGuestLoginInterceptor((SnpOkClient)object, string2)).a((Interceptor)new DigestInterceptor((SnpOkClient)object, string2)).a((Interceptor)new SnpResponseInterceptor((SnpOkClient)object, string2)).b(RequestRecorder.a().f());
        if (interceptor != null) {
            builder.a(interceptor);
        }
        x = builder.a(15000, TimeUnit.MILLISECONDS).b(15000, TimeUnit.MILLISECONDS).c(15000, TimeUnit.MILLISECONDS).b();
        y = builder.a(45000, TimeUnit.MILLISECONDS).b(45000, TimeUnit.MILLISECONDS).c(45000, TimeUnit.MILLISECONDS).b();
        z = builder.a(600000, TimeUnit.MILLISECONDS).b(600000, TimeUnit.MILLISECONDS).c(600000, TimeUnit.MILLISECONDS).b();
        A = builder.a(0, TimeUnit.MILLISECONDS).b(0, TimeUnit.MILLISECONDS).c(0, TimeUnit.MILLISECONDS).b();
        object.setClients(x, y, z, A);
        w = new Retrofit.Builder().baseUrl("http://" + e).callFactory((Call.Factory)object).addCallAdapterFactory((CallAdapter.Factory)new SnpAdapter.SnpAdapterFactory()).addConverterFactory((Converter.Factory)new SnpConverterFactory(JsonUtils.a())).build();
    }

    private static Runnable b(Runnable runnable) {
        return new Runnable(runnable){
            final /* synthetic */ Runnable a;
            {
                this.a = runnable;
            }

            public void run() {
                try {
                    this.a.run();
                    return;
                }
                catch (Exception exception) {
                    Log.d(MagicNetwork.u(), "Uncaught exception in a NETWORK thread!", exception);
                    com.smule.android.logging.MagicCrittercism.a("MagicNetwork.wrapRunnable()");
                    com.smule.android.logging.MagicCrittercism.a(exception);
                    return;
                }
            }
        };
    }

    public static String b() {
        return MagicNetwork.d().getAppUID();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String b(String string2) throws IOException {
        String string3;
        String string4;
        if (!string2.startsWith("http://" + e)) {
            string4 = string2;
            if (!string2.startsWith("https://" + e)) return string4;
        }
        string4 = string2;
        if (string2.contains("session=")) return string4;
        string4 = string3 = this.k();
        if (string3 == null) {
            this.j();
            string4 = string3 = this.k();
            if (string3 == null) {
                throw new IOException("Failed to connect to smule server " + e);
            }
        }
        if (string2.contains("?")) {
            string2 = string2 + "&";
            do {
                return string2 + "session=" + URLEncoder.encode(string4, "UTF-8");
                break;
            } while (true);
        }
        string2 = string2 + "?";
        return string2 + "session=" + URLEncoder.encode(string4, "UTF-8");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(boolean bl) throws IllegalStateException {
        synchronized (this) {
            if (bl || !(bl = this.l())) {
                this.m.set(true);
                try {
                    if (com.smule.android.network.managers.UserManager.a().B()) {
                        NotificationCenter.a().a("AUTO_LOGIN_FAILED", (Object)com.smule.android.network.managers.UserManager.a().C());
                    } else if (com.smule.android.network.managers.UserManager.a().y()) {
                        if (this.q && this.a) {
                            this.r = this.l() ? .b : .c;
                            Log.b(d, "fastReLogin:" + this.r.a());
                            com.smule.android.network.managers.UserManager.a().I();
                            MagicNetwork.a(new Runnable(this){
                                final /* synthetic */ MagicNetwork a;
                                {
                                    this.a = magicNetwork;
                                }

                                public void run() {
                                    this.a.a(true);
                                }
                            });
                        } else {
                            com.smule.android.network.managers.UserManager.a().J();
                        }
                    } else {
                        com.smule.android.network.managers.UserManager.a().a(this.v());
                    }
                }
                finally {
                    this.a = false;
                    this.m.set(false);
                }
            }
            return;
        }
    }

    public static String c() {
        return e;
    }

    public static void c(NetworkResponse object) {
        if ((object = object.a("upgradeUrl")) == null || object.length() == 0) {
            Log.e(d, "Upgrade required returned without an upgrade url!");
            return;
        }
        NotificationCenter.a().a("MagicNetwork.UPGRADE_REQUIRED_EVENT", object);
    }

    private void c(boolean bl) throws IllegalStateException {
        if (!bl && this.l()) {
            return;
        }
        this.b(bl);
    }

    public static AppDelegate d() {
        return MagicNetwork.a().i;
    }

    private void d(boolean bl) {
        long l = this.b.get();
        if (!bl && l % 5 != 0) {
            return;
        }
        this.h.getSharedPreferences("network", 0).edit().putLong("message_id", l).apply();
        Log.b(d, "persistMessageId - messageId persisted to " + l);
    }

    public static SharedPreferences e() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences(MagicNetwork.d().getClass().getName(), 0);
    }

    public static String f() {
        return C.a();
    }

    public static Handler g() {
        return v;
    }

    public static String n() {
        String string2 = MagicNetwork.d().getAdvertisingId(true);
        if (string2 != null) {
            return string2;
        }
        return MagicNetwork.d().getDeviceId();
    }

    public static void q() {
        NotificationCenter.a().b("MagicNetwork.SERVER_MAINTENANCE_EVENT", new Object[0]);
    }

    static /* synthetic */ String u() {
        return d;
    }

    private boolean v() {
        if (com.smule.android.network.managers.UserManager.a().g() == 0 && this.t.a) {
            return true;
        }
        return false;
    }

    private Object w() {
        if (this.p) {
            return this;
        }
        return this.o;
    }

    private long x() {
        return new SecureRandom().nextInt(1000);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void y() {
        String string2;
        try {
            string2 = this.h.getPackageManager().getPackageInfo((String)this.h.getPackageName(), (int)0).versionName;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            nameNotFoundException.printStackTrace();
            string2 = "unknown";
        }
        this.F = this.h.getPackageName() + "/" + string2;
        this.G = g + "," + Build.MODEL + "," + Locale.getDefault().toString();
        this.E = this.F + " (" + this.G + ")";
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public  a(String object, NetworkUtils.ProgressListener object2) throws IOException {
        Object object3;
        int n;
        long l;
        Response response;
        Request request;
        block9 : {
            l = System.currentTimeMillis();
            try {
                request = new Request.Builder().a(this.b((String)object)).b();
                object3 = B.newCall(request);
                response = object3.b();
            }
            catch (NetworkOnMainThreadException networkOnMainThreadException) {
                Log.d(d, "getStreamResponseFromUrl", (Throwable)networkOnMainThreadException);
                throw networkOnMainThreadException;
            }
            catch (IOException iOException) {
                com.smule.android.logging.EventLogger2.a((String)object, System.currentTimeMillis() - l, 0, 0, EventLogger2.e, 0, "resourcedownload", iOException.toString() + " " + iOException.getCause(), null, false);
                throw iOException;
            }
            n = response.c();
            if (n != 200) {
                object3 = "";
                object2 = NetworkUtils.a(response, (NetworkUtils.ProgressListener)object2);
            }
            break block9;
            catch (RuntimeException runtimeException) {
                Log.b(d, "API Call was cancelled");
                object2 = object3;
            }
            object3 = NptInterceptor.removeNullCharacterAndEscapedNullString((String)object2);
            com.smule.android.logging.EventLogger2.a((String)object, System.currentTimeMillis() - l, NetworkUtils.a(request), 0, EventLogger2.b, n, null, (String)object3, null, false);
            throw new ServerException(response, (String)object2);
        }
        com.smule.android.logging.EventLogger2.a((String)object, System.currentTimeMillis() - l, NetworkUtils.a(request), NetworkUtils.b(response), EventLogger2.a, n, null, null, null, false);
        object = new Object(){
            Request a;
            Response b;
            Call c;
            InputStream d;
            long e;
        };
        object.a = request;
        object.b = response;
        object.d = response.h().byteStream();
        object.c = object3;
        object2 = response.a("Content-Length");
        if (object2 == null) {
            object.e = -1;
            return object;
        }
        try {
            object.e = Long.parseLong((String)object2);
            return object;
        }
        catch (NumberFormatException numberFormatException) {
            object.e = -1;
            return object;
        }
    }

    public <T> T a(Class<T> class_) {
        return (T)w.create(class_);
    }

    public String a(String string2) {
        return this.F + " (" + this.G + "," + string2 + ")";
    }

    public void a(boolean bl) throws IllegalStateException {
        if (this.p) {
            this.b(bl);
            return;
        }
        this.c(bl);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(NetworkResponse object) {
        if (object.g == null) {
            this.d(false);
            return;
        }
        if (object.b != 0) {
            this.d(false);
            return;
        }
        Object object2 = this.k();
        if (object.g.equals(object2)) {
            object = this.w();
            synchronized (object) {
                this.n = false;
            }
            this.d(false);
            return;
        }
        object2 = this.w();
        synchronized (object2) {
            this.j = object.g;
            this.b = new AtomicLong(this.x());
            this.n = false;
            this.k = System.currentTimeMillis() / 1000;
            this.l = object.h - 30 < 0 ? object.h : object.h - 30;
        }
        this.h.getSharedPreferences("network", 0).edit().putString("session", object.g).putLong("session_time", this.k).putLong("session_ttl", this.l).apply();
        this.d(true);
        Log.c(d, "Session updated to " + object.g + "/" + this.b.get());
    }

    public boolean h() {
        return this.m.get();
    }

    public boolean i() {
        return this.n;
    }

    public void j() throws IllegalStateException {
        this.a(false);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String k() {
        Object object = this.w();
        synchronized (object) {
            return this.j;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean l() {
        Object object = this.w();
        synchronized (object) {
            if (TextUtils.isEmpty((CharSequence)this.j)) return false;
            if (System.currentTimeMillis() / 1000 >= this.k + this.l) return false;
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void m() {
        if (!this.m.get()) {
            Object object = this.w();
            synchronized (object) {
                this.j = null;
                return;
            }
        }
    }

    public Long o() {
        return this.b.incrementAndGet();
    }

    public Long p() {
        return this.b.addAndGet(100);
    }

    public  r() {
        return this.r;
    }

    public boolean s() {
        return this.q;
    }

    public OkHttpClient t() {
        return B;
    }

}

