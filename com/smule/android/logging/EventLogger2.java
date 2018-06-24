/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.os.SystemClock
 *  android.support.annotation.NonNull
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  retrofit2.Call
 */
package com.smule.android.logging;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.smule.android.audio.AudioDefs;
import com.smule.android.audio.GlitchContext;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLog2Listener;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.api.EventLogger2API;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.StringUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

public class EventLogger2 {
    static final String a = EventLogger2.class.getName();
    private static boolean b = true;
    private static  c;
    private static EventLogger2 d;
    private static double q;
    private com.smule.android.network.api.EventLogger2API e = MagicNetwork.a().a(com.smule.android.network.api.EventLogger2API.class);
    private List<> f = new ArrayList<>();
    private int g;
    private Context h = MagicNetwork.d().getApplicationContext();
    private SharedPreferences i = this.h.getSharedPreferences("event-logger", 0);
    private boolean j = false;
    private long k = 0;
    private long l = 0;
    private final Object m = new Object();
    private long n = 1;
    private AtomicBoolean o = new AtomicBoolean(false);
    private final CopyOnWriteArrayList<EventLog2Listener> p = new CopyOnWriteArrayList();

    static {
        q = 0.0;
    }

    private EventLogger2() {
        this.g = this.i.getInt("eventlog_queue_limit", 200);
        int n = this.i.getInt("eventlog_flush_frequency", 30);
        this.l = SystemClock.elapsedRealtime();
        this.k = this.i.getLong("eventlog_server_init_time", System.currentTimeMillis());
        this.g();
        this.i();
        MagicNetwork.a(new Runnable(this){
            final /* synthetic */ EventLogger2 a;
            {
                this.a = eventLogger2;
            }

            public void run() {
                EventLogger2.b(this.a);
            }
        }, n, n, TimeUnit.SECONDS);
        if (this.f.size() > 0) {
            Log.b(a, "EventLogger2 - queue is not empty (" + this.f.size() + ") so flushing it now");
            this.f();
        }
    }

    public static EventLogger2 a() {
        synchronized (EventLogger2.class) {
            if (d == null) {
                Log.b(a, "Creating new EventLogger2 instance");
                d = new EventLogger2();
            }
            EventLogger2 eventLogger2 = d;
            return eventLogger2;
        }
    }

    public static String a( object, int n, String charSequence, String string2) {
        if (object == .a) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder().append(n).append(",").append(object.f).append(",");
        object = charSequence;
        if (TextUtils.isEmpty((CharSequence)charSequence)) {
            object = "";
        }
        charSequence = stringBuilder.append((String)object).append(",");
        object = string2;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            object = "";
        }
        return charSequence.append((String)object).toString();
    }

    static /* synthetic */ List a(EventLogger2 eventLogger2, List list) {
        eventLogger2.f = list;
        return list;
    }

    public static void a(int n, @NonNull GlitchContext object, @NonNull AudioDefs.HeadphonesType headphonesType, int n2, int n3, int n4, int n5, int n6, int n7, int n8, Boolean bl) {
        object = new Builder().a("npt_g").b(Integer.toString(n)).c(object.a()).b(n5).c(n6).d(n2).e(n3).f(n4).g(n8).k(headphonesType.a()).h(bl);
        EventLogger2.a().a((Builder)object);
    }

    public static void a(Activity activity) {
        Iterator<EventLog2Listener> iterator = EventLogger2.a().p.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(activity);
        }
        EventLogger2.a().d();
    }

    public static void a(EventLog2Listener eventLog2Listener) {
        EventLogger2.a().p.add(eventLog2Listener);
    }

    public static void a(@NonNull  nPTMembershipPersisterDelegate) {
        c = nPTMembershipPersisterDelegate;
        b = c.a();
        Log.b("npt", "NPT sampling initialized to " + b);
    }

    public static void a(String object, long l, long l2, long l3, @NonNull  object2, int n, String string2, String string3, long l4, long l5, long l6, long l7) {
        if (!b) {
            return;
        }
        Object object3 = Uri.parse((String)object);
        String string4 = object3.getPath();
        object = object3.getHost();
        object3 = object3.getScheme();
        String string5 = EventLogger2.e();
        string4 = StringUtils.a(string4, 128);
        object2 = StringUtils.a(EventLogger2.a((Object)object2, n, string2, string3), 2128);
        object = new Builder().a("npt_s").b(string4).c((String)object).d((String)object3).e(string5).c(l).d(l2).e(l3).i((String)object2).f(l4).g(l5).h(l6).i(l7).b(true).a();
        EventLogger2.a().a(object);
    }

    public static void a(String string2, long l, long l2, long l3, @NonNull  errorDomain, int n, String string3, String string4, String string5, boolean bl) {
        if (!b) {
            return;
        }
        string2 = Uri.parse((String)string2);
        EventLogger2.a(string2.getScheme(), string2.getHost(), string2.getPath(), l, l2, l3, errorDomain, n, string3, string4, string5, bl, 0);
    }

    public static void a(String string2, long l, long l2, long l3, @NonNull  errorDomain, int n, String string3, String string4, String string5, boolean bl, int n2) {
        if (!b) {
            return;
        }
        string2 = Uri.parse((String)string2);
        EventLogger2.a(string2.getScheme(), string2.getHost(), string2.getPath(), l, l2, l3, errorDomain, n, string3, string4, string5, bl, n2);
    }

    public static void a(String string2, String string3, String string4, long l, long l2, long l3, @NonNull  errorDomain, int n, String string5, String string6, String string7, boolean bl) {
        if (!b) {
            return;
        }
        EventLogger2.a(string2, string3, string4, l, l2, l3, errorDomain, n, string5, string6, string7, bl, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String object, String object2, String string2, long l, long l2, long l3, @NonNull  object3, int n, String string3, String string4, String string5, boolean bl, int n2) {
        if (!b) {
            return;
        }
        String string6 = EventLogger2.e();
        string2 = StringUtils.a(string2, 128);
        object3 = StringUtils.a(EventLogger2.a((Object)object3, n, string3, string4), 2128);
        object2 = new Builder().a("npt").b(string2).c((String)object2).d((String)object).e(string6).c(l2).d(l3).e(l).i((String)object3).j(string5);
        object = bl ? "1" : null;
        object = object2.k((String)object).i(n2).b(true).a();
        EventLogger2.a().a(object);
    }

    public static void a(boolean bl) {
        b = bl;
        c.a(b);
    }

    private boolean a(List<> list) {
        Object object = new SnpRequest(){
            public String app;
            public List<Object> events;
            {
                this.app = MagicNetwork.b();
            }

            public EventLogger2API setEvents(List<> object) {
                ArrayList<Object> arrayList = new ArrayList<Object>();
                object = object.iterator();
                while (object.hasNext()) {
                    Object object2 = object.next();
                    if (TextUtils.isEmpty((CharSequence)object2.b)) {
                        object2 = "eventType empty:" + object2;
                        Log.d("EventLogger2API", (String)object2, new java.lang.Exception((String)object2));
                        continue;
                    }
                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                    hashMap.put("ts", object2.a);
                    hashMap.put("app", this.app);
                    hashMap.put("plyrId", com.smule.android.network.managers.UserManager.a().g());
                    hashMap.put("event", object2.b);
                    if (object2.c != null) {
                        hashMap.put("target", object2.c);
                    }
                    if (object2.d != null) {
                        hashMap.put("context", object2.d);
                    }
                    if (object2.e != null) {
                        hashMap.put("value", object2.e);
                    }
                    if (object2.f != null) {
                        hashMap.put("k1", object2.f);
                    }
                    if (object2.g != null) {
                        hashMap.put("k2", object2.g);
                    }
                    if (object2.h != null) {
                        hashMap.put("k3", object2.h);
                    }
                    if (object2.i != null) {
                        hashMap.put("k4", object2.i);
                    }
                    if (object2.j != null) {
                        hashMap.put("k5", object2.j);
                    }
                    if (object2.k != null) {
                        hashMap.put("k6", object2.k);
                    }
                    if (object2.l != null) {
                        hashMap.put("k7", object2.l);
                    }
                    if (object2.m != null) {
                        hashMap.put("k8", object2.m);
                    }
                    if (object2.n != null) {
                        hashMap.put("k9", object2.n);
                    }
                    arrayList.add(hashMap);
                }
                this.events = arrayList;
                return this;
            }
        }.setEvents(list);
        if (object.events.size() != 0) {
            object = NetworkUtils.a(this.e.postEvents((Object)object));
            if (object.b != 0) {
                // empty if block
            }
            if (object.a != NetworkResponse.a) {
                Log.e(a, "Failed to send " + list.size() + " events.  Not deleting event cache");
                return false;
            }
        }
        this.i();
        return true;
    }

    public static long b(boolean bl) {
        return EventLogger2.a().c(bl);
    }

    public static void b(Activity activity) {
        Iterator<EventLog2Listener> iterator = EventLogger2.a().p.iterator();
        while (iterator.hasNext()) {
            iterator.next().b(activity);
        }
    }

    public static void b(EventLog2Listener eventLog2Listener) {
        EventLogger2.a().p.remove(eventLog2Listener);
    }

    static /* synthetic */ void b(EventLogger2 eventLogger2) {
        eventLogger2.f();
    }

    static boolean b() {
        return b;
    }

    static /* synthetic */ boolean b(EventLogger2 eventLogger2, List list) {
        return eventLogger2.a(list);
    }

    private long c(boolean bl) {
        if (bl) {
            this.d();
        }
        if (!this.j) {
            this.n = this.i.getLong("session-count", 1);
            this.j = true;
        }
        Log.b(a, "getSessionCount - returning: " + this.n);
        return this.n;
    }

    static /* synthetic */ Context c(EventLogger2 eventLogger2) {
        return eventLogger2.h;
    }

    static /* synthetic */ List d(EventLogger2 eventLogger2) {
        return eventLogger2.f;
    }

    public static String e() {
        Object object = (ConnectivityManager)EventLogger2.a().h.getSystemService("connectivity");
        NetworkInfo networkInfo = object.getNetworkInfo(1);
        NetworkInfo networkInfo2 = object.getNetworkInfo(0);
        String string2 = "wifi";
        object = string2;
        if (networkInfo != null) {
            object = string2;
            if (!networkInfo.isConnected()) {
                object = string2;
                if (networkInfo2 != null) {
                    object = string2;
                    if (networkInfo2.isConnected()) {
                        object = ((TelephonyManager)EventLogger2.a().h.getSystemService("phone")).getNetworkOperatorName();
                    }
                }
            }
        }
        return object;
    }

    static /* synthetic */ void e(EventLogger2 eventLogger2) {
        eventLogger2.h();
    }

    private void f() {
        MagicNetwork.a(new Runnable(this){
            final /* synthetic */ EventLogger2 a;
            {
                this.a = eventLogger2;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void run() {
                Object object;
                block10 : {
                    block9 : {
                        if (!NetworkUtils.a(EventLogger2.c(this.a))) break block9;
                        if (com.smule.android.network.managers.UserManager.a().g() == 0) {
                            Log.a(EventLogger2.a, "flushEvents: playerId was 0; not flushing");
                            return;
                        }
                        EventLogger2 eventLogger2 = this.a;
                        synchronized (eventLogger2) {
                            if (EventLogger2.d(this.a).size() <= 0) {
                                return;
                            }
                            boolean bl = false;
                            object = EventLogger2.d(this.a).iterator();
                            while (object.hasNext()) {
                                if ((object.next()).p) continue;
                                bl = true;
                            }
                            object = EventLogger2.d(this.a);
                            EventLogger2.a(this.a, new ArrayList<E>());
                            if (object != null) break block10;
                        }
                    }
                    return;
                }
                EventLogger2.b(this.a, (List)object);
            }
        });
    }

    static /* synthetic */ void f(EventLogger2 eventLogger2) {
        eventLogger2.i();
    }

    static /* synthetic */ AtomicBoolean g(EventLogger2 eventLogger2) {
        return eventLogger2.o;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void g() {
        Log.c(a, "Loading analytics events.");
        ObjectInputStream objectInputStream = new ObjectInputStream(this.h.openFileInput("event-logger"));
        // MONITORENTER : this
        this.f = (List)objectInputStream.readObject();
        Log.b(a, "load - following load from file, the event queue size is: " + this.f.size());
        // MONITOREXIT : this
        try {
            objectInputStream.close();
            return;
        }
        catch (ClassNotFoundException classNotFoundException) {
            Log.d(a, "Failed to load existing event queue.", classNotFoundException);
            return;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to load existing event queue.", iOException);
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void h() {
        ObjectOutputStream objectOutputStream;
        Log.c(a, "Saving analytics events.");
        try {
            objectOutputStream = new ObjectOutputStream(this.h.openFileOutput("event-logger", 0));
            // MONITORENTER : this
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to save current event queue.", iOException);
            return;
        }
        objectOutputStream.writeObject(this.f);
        objectOutputStream.flush();
        // MONITOREXIT : this
        objectOutputStream.close();
    }

    private void i() {
        this.h.deleteFile("event-logger");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private long j() {
        Object object = this.m;
        synchronized (object) {
            long l = SystemClock.elapsedRealtime() - this.l;
            if (l < 0 || l > 31536000000L) {
                Log.e(a, "Elapsed time is awkward: " + l);
            }
            long l2 = this.k;
            return l + l2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(long l) {
        SharedPreferences.Editor editor = this.i.edit();
        Object object = this.m;
        synchronized (object) {
            this.l = SystemClock.elapsedRealtime();
            this.k = l;
            editor.putLong("eventlog_server_init_time", this.k);
            editor.apply();
        }
        Log.c(a, "calibrateTimeStamp: " + l);
    }

    public void a(Builder builder) {
        this.a(builder.a());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a( event) {
        Log.c(a, "Logged new event: " + event);
        synchronized (this) {
            this.f.add(event);
            if (event.o) {
                MagicNetwork.a(new Runnable(this){
                    final /* synthetic */ EventLogger2 a;
                    {
                        this.a = eventLogger2;
                    }

                    public void run() {
                        EventLogger2.b(this.a);
                    }
                }, 1, TimeUnit.SECONDS);
            }
            if (this.f.size() > this.g) {
                if (NetworkUtils.a(this.h)) {
                    this.f();
                } else {
                    this.f.remove(0);
                }
            }
        }
        Iterator<EventLog2Listener> iterator = this.p.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(event);
        }
        return;
    }

    public void a(String string2) {
        this.a(new Serializable(string2, null, null, null, null, null, null, null, null, null, null){
            public long a;
            public String b;
            public String c;
            public String d;
            public String e;
            public String f;
            public String g;
            public String h;
            public String i;
            public String j;
            public String k;
            public String l;
            public String m;
            public String n;
            public boolean o;
            public boolean p;
            {
                this.b = builder.a;
                this.c = builder.b;
                this.d = builder.c;
                this.e = builder.d;
                this.f = builder.e;
                this.g = builder.f;
                this.h = builder.g;
                this.i = builder.h;
                this.j = builder.i;
                this.k = builder.j;
                this.l = builder.k;
                this.m = builder.l;
                this.n = builder.m;
                this.o = builder.n;
                this.p = builder.o;
                this.a = EventLogger2.a().j();
            }
            {
                this(string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, null, null, false);
            }
            {
                this.b = string2;
                this.c = string3;
                this.d = string4;
                this.e = string5;
                this.f = string6;
                this.g = string7;
                this.h = string8;
                this.i = string9;
                this.j = string10;
                this.k = string11;
                this.l = string12;
                this.m = string13;
                this.n = string14;
                this.o = bl;
                this.a = EventLogger2.a().j();
            }
            {
                this(string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, null, null, bl);
            }

            public String toString() {
                return "[timeStamp=" + this.a + ", eventType=" + this.b + ", target=" + this.c + ", context=" + this.d + ", value=" + this.e + ", k1=" + this.f + ", k2=" + this.g + ", k3=" + this.h + ", k4=" + this.i + ", k5=" + this.j + ", k6=" + this.k + ", k7=" + this.l + ", k8=" + this.m + ", k9=" + this.n + ", immediate=" + this.o + "]";
            }

            public static class Builder {
                private String a;
                private String b = null;
                private String c = null;
                private String d = null;
                private String e = null;
                private String f = null;
                private String g = null;
                private String h = null;
                private String i = null;
                private String j = null;
                private String k = null;
                private String l = null;
                private String m = null;
                private boolean n = false;
                private boolean o = false;

                public Builder a(float f) {
                    this.e = String.valueOf(f);
                    return this;
                }

                public Builder a(int n) {
                    this.d = String.valueOf(n);
                    return this;
                }

                public Builder a(long l) {
                    this.d = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder a(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.b = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder a(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.d = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder a(Number object) {
                    object = object != null ? object.toString() : null;
                    this.c = object;
                    return this;
                }

                public Builder a(Object object) {
                    this.b = String.valueOf(object);
                    return this;
                }

                public Builder a(String string2) {
                    this.a = string2;
                    return this;
                }

                public Builder a(boolean bl) {
                    this.n = bl;
                    return this;
                }

                public  a() {
                    return new ;
                }

                public Builder b(float f) {
                    this.f = String.valueOf(f);
                    return this;
                }

                public Builder b(int n) {
                    this.e = String.valueOf(n);
                    return this;
                }

                public Builder b(long l) {
                    this.e = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder b(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.c = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder b(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.e = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder b(Number object) {
                    object = object != null ? object.toString() : null;
                    this.d = object;
                    return this;
                }

                public Builder b(String string2) {
                    this.b = string2;
                    return this;
                }

                public Builder b(boolean bl) {
                    this.o = bl;
                    return this;
                }

                public Builder c(float f) {
                    this.g = String.valueOf(f);
                    return this;
                }

                public Builder c(int n) {
                    this.f = String.valueOf(n);
                    return this;
                }

                public Builder c(long l) {
                    this.f = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder c(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.d = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder c(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.f = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder c(Number object) {
                    object = object != null ? object.toString() : null;
                    this.e = object;
                    return this;
                }

                public Builder c(String string2) {
                    this.c = string2;
                    return this;
                }

                public Builder d(float f) {
                    this.h = String.valueOf(f);
                    return this;
                }

                public Builder d(int n) {
                    this.g = String.valueOf(n);
                    return this;
                }

                public Builder d(long l) {
                    this.g = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder d(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.e = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder d(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.h = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder d(Number object) {
                    object = object != null ? object.toString() : null;
                    this.f = object;
                    return this;
                }

                public Builder d(String string2) {
                    this.d = string2;
                    return this;
                }

                public Builder e(float f) {
                    this.i = String.valueOf(f);
                    return this;
                }

                public Builder e(int n) {
                    this.h = String.valueOf(n);
                    return this;
                }

                public Builder e(long l) {
                    this.h = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder e(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.f = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder e(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.i = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder e(Number object) {
                    object = object != null ? object.toString() : null;
                    this.g = object;
                    return this;
                }

                public Builder e(String string2) {
                    this.e = string2;
                    return this;
                }

                public Builder f(int n) {
                    this.i = String.valueOf(n);
                    return this;
                }

                public Builder f(long l) {
                    this.j = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder f(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.g = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder f(Boolean object) {
                    object = object != null ? Boolean.toString(object.booleanValue()) : null;
                    this.j = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder f(Number object) {
                    object = object != null ? object.toString() : null;
                    this.h = object;
                    return this;
                }

                public Builder f(String string2) {
                    this.f = string2;
                    return this;
                }

                public Builder g(int n) {
                    this.j = String.valueOf(n);
                    return this;
                }

                public Builder g(long l) {
                    this.k = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder g(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.h = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder g(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.k = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder g(Number object) {
                    object = object != null ? object.toString() : null;
                    this.i = object;
                    return this;
                }

                public Builder g(String string2) {
                    this.g = string2;
                    return this;
                }

                public Builder h(int n) {
                    this.k = String.valueOf(n);
                    return this;
                }

                public Builder h(long l) {
                    this.l = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder h(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.i = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder h(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.l = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder h(Number object) {
                    object = object != null ? object.toString() : null;
                    this.j = object;
                    return this;
                }

                public Builder h(String string2) {
                    this.h = string2;
                    return this;
                }

                public Builder i(int n) {
                    this.l = String.valueOf(n);
                    return this;
                }

                public Builder i(long l) {
                    this.m = String.valueOf(l);
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder i(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.j = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder i(Boolean object) {
                    object = object != null ? object.toString() : null;
                    this.m = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder i(Number object) {
                    object = object != null ? object.toString() : null;
                    this.k = object;
                    return this;
                }

                public Builder i(String string2) {
                    this.i = string2;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder j(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.k = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder j(Number object) {
                    object = object != null ? object.toString() : null;
                    this.l = object;
                    return this;
                }

                public Builder j(String string2) {
                    this.j = string2;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder k(Analytics object) {
                    object = object != null ? object.a() : null;
                    this.l = object;
                    return this;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public Builder k(Number object) {
                    object = object != null ? object.toString() : null;
                    this.m = object;
                    return this;
                }

                public Builder k(String string2) {
                    this.k = string2;
                    return this;
                }

                public Builder l(String string2) {
                    this.m = string2;
                    return this;
                }
            }

        });
    }

    public void a(String string2, String string3, String string4) {
        this.a(new );
    }

    public void a(String string2, String string3, String string4, String string5, String string6) {
        this.a(new );
    }

    public void a(String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
        this.a(new );
    }

    public void a(String string2, String string3, String string4, String string5, String string6, String string7, boolean bl) {
        this.a(new );
    }

    public void a(String string2, String string3, String string4, String string5, String string6, boolean bl) {
        this.a(new );
    }

    public void a(String string2, String string3, String string4, String string5, boolean bl) {
        this.a(new );
    }

    public void a(String string2, String string3, String string4, boolean bl) {
        this.a(new );
    }

    public void a(String string2, boolean bl) {
        this.a(new );
    }

    public void c() {
        Log.b(a, "saveEventsToDisk called - events currently in the queue: " + this.f.size());
        this.h();
    }

    public void d() {
        SharedPreferences.Editor editor = this.i.edit();
        long l = this.i.getLong("last-activity", 0);
        long l2 = System.currentTimeMillis() / 1000;
        editor.putLong("last-activity", l2);
        if (l + 600 < l2) {
            l = this.i.getLong("session-count", 1) + 1;
            editor.putLong("session-count", l);
            this.n = l;
        }
        editor.apply();
    }

}

