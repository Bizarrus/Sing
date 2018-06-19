package com.smule.android.logging;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.smule.android.network.api.EventLogger2API;
import com.smule.android.network.api.EventLogger2API.PostEventsRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse.Status;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.utils.StringUtils;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventLogger2 {
    static final String f6701a = EventLogger2.class.getName();
    public static boolean f6702b = true;
    private static EventLogger2 f6703c;
    private static double f6704p = 0.0d;
    private EventLogger2API f6705d = ((EventLogger2API) MagicNetwork.m7789a().m7817a(EventLogger2API.class));
    private List<Event> f6706e = new ArrayList();
    private int f6707f = this.f6709h.getInt("eventlog_queue_limit", 200);
    private Context f6708g = MagicNetwork.m7804d().getApplicationContext();
    private SharedPreferences f6709h = this.f6708g.getSharedPreferences("event-logger", 0);
    private boolean f6710i = false;
    private long f6711j = 0;
    private long f6712k = 0;
    private final Object f6713l = new Object();
    private long f6714m = 1;
    private AtomicBoolean f6715n = new AtomicBoolean(false);
    private final List<EventLog2Listener> f6716o = new ArrayList();

    public static synchronized EventLogger2 m7727a() {
        EventLogger2 eventLogger2;
        synchronized (EventLogger2.class) {
            if (f6703c == null) {
                Log.m7770b(f6701a, "Creating new EventLogger2 instance");
                f6703c = new EventLogger2();
            }
            eventLogger2 = f6703c;
        }
        return eventLogger2;
    }

    private EventLogger2() {
        int i = this.f6709h.getInt("eventlog_flush_frequency", 30);
        this.f6712k = SystemClock.elapsedRealtime();
        this.f6711j = this.f6709h.getLong("eventlog_server_init_time", System.currentTimeMillis());
        m7746e();
        m7751g();
        MagicNetwork.m7791a(new 1(this), (long) i, (long) i, TimeUnit.SECONDS);
        if (this.f6706e.size() > 0) {
            Log.m7770b(f6701a, "EventLogger2 - queue is not empty (" + this.f6706e.size() + ") so flushing it now");
            m7745d();
        }
    }

    private boolean m7737a(List<Event> list) {
        PostEventsRequest events = new PostEventsRequest().setEvents(list);
        if (events.events.size() != 0) {
            NetworkResponse a = NetworkUtils.a(this.f6705d.postEvents(events));
            if (a.f6756b != 0) {
            }
            if (a.f6755a != Status.a) {
                Log.m7776e(f6701a, "Failed to send " + list.size() + " events.  Not deleting event cache");
                return false;
            }
        }
        m7751g();
        return true;
    }

    private void m7745d() {
        MagicNetwork.m7790a(new 2(this));
    }

    public void m7755a(Event event) {
        Log.m7772c(f6701a, "Logged new event: " + event);
        synchronized (this) {
            this.f6706e.add(event);
            if (event.o) {
                MagicNetwork.m7792a(new 3(this), 1, TimeUnit.SECONDS);
            }
            if (this.f6706e.size() > this.f6707f) {
                if (NetworkUtils.a(this.f6708g)) {
                    m7745d();
                } else {
                    this.f6706e.remove(0);
                }
            }
        }
        synchronized (this.f6716o) {
            for (EventLog2Listener a : this.f6716o) {
                a.mo4038a(event);
            }
        }
    }

    private void m7746e() {
        Log.m7772c(f6701a, "Loading analytics events.");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.f6708g.openFileInput("event-logger"));
            synchronized (this) {
                this.f6706e = (List) objectInputStream.readObject();
                Log.m7770b(f6701a, "load - following load from file, the event queue size is: " + this.f6706e.size());
            }
            objectInputStream.close();
        } catch (Throwable e) {
            Log.m7775d(f6701a, "Failed to load existing event queue.", e);
        } catch (FileNotFoundException e2) {
        } catch (Throwable e3) {
            Log.m7775d(f6701a, "Failed to load existing event queue.", e3);
        }
    }

    private void m7748f() {
        Log.m7772c(f6701a, "Saving analytics events.");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.f6708g.openFileOutput("event-logger", 0));
            synchronized (this) {
                objectOutputStream.writeObject(this.f6706e);
            }
            objectOutputStream.close();
        } catch (Throwable e) {
            Log.m7775d(f6701a, "Failed to save current event queue.", e);
        }
    }

    private void m7751g() {
        this.f6708g.deleteFile("event-logger");
    }

    private long m7752h() {
        long elapsedRealtime;
        synchronized (this.f6713l) {
            elapsedRealtime = SystemClock.elapsedRealtime() - this.f6712k;
            if (elapsedRealtime < 0 || elapsedRealtime > 31536000000L) {
                Log.m7776e(f6701a, "Elapsed time is awkward: " + elapsedRealtime);
            }
            elapsedRealtime += this.f6711j;
        }
        return elapsedRealtime;
    }

    public void m7753a(long j) {
        Editor edit = this.f6709h.edit();
        synchronized (this.f6713l) {
            this.f6712k = SystemClock.elapsedRealtime();
            this.f6711j = j;
            edit.putLong("eventlog_server_init_time", this.f6711j);
            edit.apply();
        }
        Log.m7772c(f6701a, "calibrateTimeStamp: " + j);
    }

    public void m7754a(Builder builder) {
        m7755a(builder.a());
    }

    public void m7756a(String str) {
        m7755a(new Event(str, null, null, null, null, null, null, null, null, null, null));
    }

    public void m7765a(String str, boolean z) {
        m7755a(new Event(str, null, null, null, null, null, null, null, null, null, null, z));
    }

    public void m7757a(String str, String str2, String str3) {
        m7755a(new Event(str, str2, str3, null, null, null, null, null, null, null, null));
    }

    public void m7764a(String str, String str2, String str3, boolean z) {
        m7755a(new Event(str, str2, str3, null, null, null, null, null, null, null, null, z));
    }

    public void m7763a(String str, String str2, String str3, String str4, boolean z) {
        m7755a(new Event(str, str2, str3, str4, null, null, null, null, null, null, null, z));
    }

    public void m7758a(String str, String str2, String str3, String str4, String str5) {
        m7755a(new Event(str, str2, str3, str4, str5, null, null, null, null, null, null));
    }

    public void m7762a(String str, String str2, String str3, String str4, String str5, boolean z) {
        m7755a(new Event(str, str2, str3, str4, str5, null, null, null, null, null, null, z));
    }

    public void m7761a(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        m7755a(new Event(str, str2, str3, str4, str5, str6, null, null, null, null, null, z));
    }

    public void m7759a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        m7755a(new Event(str, str2, str3, str4, str5, str6, str7, null, null, null, null));
    }

    public void m7760a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        m7755a(new Event(str, str2, str3, str4, str5, str6, str7, str8, null, null, null, z));
    }

    public void m7766b() {
        Editor edit = this.f6709h.edit();
        long j = this.f6709h.getLong("last-activity", 0);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        edit.putLong("last-activity", currentTimeMillis);
        if (j + 600 < currentTimeMillis) {
            j = this.f6709h.getLong("session-count", 1) + 1;
            edit.putLong("session-count", j);
            this.f6714m = j;
        }
        edit.apply();
    }

    public static String m7743c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) m7727a().f6708g.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        String str = "wifi";
        if (networkInfo == null || networkInfo.isConnected() || networkInfo2 == null || !networkInfo2.isConnected()) {
            return str;
        }
        return ((TelephonyManager) m7727a().f6708g.getSystemService("phone")).getNetworkOperatorName();
    }

    public static String m7728a(ErrorDomain errorDomain, int i, String str, String str2) {
        if (errorDomain == ErrorDomain.a) {
            return null;
        }
        StringBuilder append = new StringBuilder().append(i).append(",").append(errorDomain.f).append(",");
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        append = append.append(str).append(",");
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        return append.append(str2).toString();
    }

    public static void m7733a(String str, long j, long j2, long j3, @NonNull ErrorDomain errorDomain, int i, String str2, String str3, String str4, boolean z) {
        if (f6702b) {
            Uri parse = Uri.parse(str);
            m7736a(parse.getScheme(), parse.getHost(), parse.getPath(), j, j2, j3, errorDomain, i, str2, str3, str4, z, 0);
        }
    }

    public static void m7734a(String str, long j, long j2, long j3, @NonNull ErrorDomain errorDomain, int i, String str2, String str3, String str4, boolean z, int i2) {
        if (f6702b) {
            Uri parse = Uri.parse(str);
            m7736a(parse.getScheme(), parse.getHost(), parse.getPath(), j, j2, j3, errorDomain, i, str2, str3, str4, z, i2);
        }
    }

    public static void m7735a(String str, String str2, String str3, long j, long j2, long j3, @NonNull ErrorDomain errorDomain, int i, String str4, String str5, String str6, boolean z) {
        if (f6702b) {
            m7736a(str, str2, str3, j, j2, j3, errorDomain, i, str4, str5, str6, z, 0);
        }
    }

    public static void m7736a(String str, String str2, String str3, long j, long j2, long j3, @NonNull ErrorDomain errorDomain, int i, String str4, String str5, String str6, boolean z, int i2) {
        if (f6702b) {
            m7727a().m7755a(new Builder().a("npt").b(StringUtils.a(str3, 128)).c(str2).d(str).e(m7743c()).c(j2).d(j3).e(j).i(StringUtils.a(m7728a(errorDomain, i, str4, str5), 2128)).j(str6).k(z ? "1" : null).h(i2).b(true).a());
        }
    }

    public static void m7732a(String str, long j, long j2, long j3, @NonNull ErrorDomain errorDomain, int i, String str2, String str3, long j4, long j5, long j6, long j7) {
        if (f6702b) {
            Uri parse = Uri.parse(str);
            String path = parse.getPath();
            String host = parse.getHost();
            String scheme = parse.getScheme();
            String c = m7743c();
            path = StringUtils.a(path, 128);
            m7727a().m7755a(new Builder().a("npt_s").b(path).c(host).d(scheme).e(c).c(j).d(j2).e(j3).i(StringUtils.a(m7728a(errorDomain, i, str2, str3), 2128)).f(j4).g(j5).h(j6).i(j7).b(true).a());
        }
    }

    public static void m7731a(EventLog2Listener eventLog2Listener) {
        synchronized (m7727a().f6716o) {
            m7727a().f6716o.add(eventLog2Listener);
        }
    }

    public static void m7730a(Activity activity) {
        synchronized (m7727a().f6716o) {
            for (EventLog2Listener a : m7727a().f6716o) {
                a.mo4037a(activity);
            }
        }
        m7727a().m7766b();
    }

    public static void m7739b(Activity activity) {
        synchronized (m7727a().f6716o) {
            for (EventLog2Listener b : m7727a().f6716o) {
                b.mo4039b(activity);
            }
        }
    }

    public static long m7726a(boolean z) {
        return m7727a().m7738b(z);
    }

    private long m7738b(boolean z) {
        if (z) {
            m7766b();
        }
        if (!this.f6710i) {
            this.f6714m = this.f6709h.getLong("session-count", 1);
            this.f6710i = true;
        }
        Log.m7770b(f6701a, "getSessionCount - returning: " + this.f6714m);
        return this.f6714m;
    }
}
