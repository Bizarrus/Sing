/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.content.Context
 *  android.os.Bundle
 *  com.adjust.sdk.Adjust
 *  com.adjust.sdk.AdjustConfig
 *  com.adjust.sdk.LogLevel
 *  com.getkeepsafe.relinker.ReLinker
 */
package com.smule;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.getkeepsafe.relinker.ReLinker;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.utils.SessionRefreshActivityLifecycleCallbacks;
import com.smule.android.utils.UncaughtExceptionHelper;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SmuleApplication
extends Application {
    public static final String a = SmuleApplication.class.getName();
    private static SmuleApplication d;
    protected boolean b = true;
    SimpleActivityLifecycleCallbacks c;
    private final List<String> e = new LinkedList<String>();
    private final Set<WeakReference<ApplicationLifecycleListener>> f = new HashSet<WeakReference<ApplicationLifecycleListener>>();
    private MagicCrittercism g;
    private UncaughtExceptionHelper h = new UncaughtExceptionHelper();

    public SmuleApplication() {
        this.c = new SimpleActivityLifecycleCallbacks(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onActivityStarted(Activity object) {
                SmuleApplication.this.e.add(object.getClass().getCanonicalName());
                if (SmuleApplication.this.e.size() == 1) {
                    Log.b(SmuleApplication.a, "App brought to foreground");
                    object = SmuleApplication.this.f;
                    synchronized (object) {
                        Iterator iterator = SmuleApplication.this.f.iterator();
                        while (iterator.hasNext()) {
                            ApplicationLifecycleListener applicationLifecycleListener = (ApplicationLifecycleListener)((WeakReference)iterator.next()).get();
                            if (applicationLifecycleListener == null) continue;
                            applicationLifecycleListener.a();
                        }
                    }
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onActivityStopped(Activity object) {
                SmuleApplication.this.e.remove(object.getClass().getCanonicalName());
                if (SmuleApplication.this.e.isEmpty()) {
                    Log.b(SmuleApplication.a, "App sent to background");
                    object = SmuleApplication.this.f;
                    synchronized (object) {
                        Iterator iterator = SmuleApplication.this.f.iterator();
                        while (iterator.hasNext()) {
                            ApplicationLifecycleListener applicationLifecycleListener = (ApplicationLifecycleListener)((WeakReference)iterator.next()).get();
                            if (applicationLifecycleListener == null) continue;
                            applicationLifecycleListener.b();
                        }
                    }
                }
            }
        };
    }

    public static Context a() {
        return d;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static void a(Context context, int n, boolean bl) {
        if (d == null || SmuleApplication.d.g != null) {
            return;
        }
        SmuleApplication.d.g = new MagicCrittercism(context, n, bl);
    }

    public static MagicCrittercism b() {
        if (d != null) {
            return SmuleApplication.d.g;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(ApplicationLifecycleListener applicationLifecycleListener) {
        Set<WeakReference<ApplicationLifecycleListener>> set = this.f;
        synchronized (set) {
            this.f.add(new WeakReference<ApplicationLifecycleListener>(applicationLifecycleListener));
            return;
        }
    }

    protected void a(AdjustAttributionSettings object) {
        AdjustConfig adjustConfig = new AdjustConfig((Context)this, object.a(), object.b());
        adjustConfig.setLogLevel(LogLevel.DEBUG);
        long[] arrl = object.e();
        if (arrl != null) {
            adjustConfig.setAppSecret(arrl[0], arrl[1], arrl[2], arrl[3], arrl[4]);
        }
        if ((object = object.f()) != null) {
            adjustConfig.setDefaultTracker((String)object);
        }
        Adjust.onCreate((AdjustConfig)adjustConfig);
        this.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new SimpleActivityLifecycleCallbacks(){

            @Override
            public void onActivityPaused(Activity activity) {
                Adjust.onPause();
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Adjust.onResume();
            }
        });
    }

    public UncaughtExceptionHelper c() {
        return this.h;
    }

    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this.h);
        d = this;
        if (this.b) {
            this.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new SessionRefreshActivityLifecycleCallbacks());
        }
        this.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)this.c);
        ReLinker.a((Context)this, (String)"cma");
    }

    public static interface ApplicationLifecycleListener {
        public void a();

        public void b();
    }

    protected class SimpleActivityLifecycleCallbacks
    implements Application.ActivityLifecycleCallbacks {
        protected SimpleActivityLifecycleCallbacks() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

}

