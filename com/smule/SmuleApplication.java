package com.smule;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SmuleApplication extends Application {
    public static final String f6659a = SmuleApplication.class.getName();
    private static SmuleApplication f6660d;
    protected boolean f6661b = true;
    SimpleActivityLifecycleCallbacks f6662c = new C19402(this);
    private final List<String> f6663e = new LinkedList();
    private final Set<WeakReference<ApplicationLifecycleListener>> f6664f = new HashSet();
    private MagicCrittercism f6665g;
    private UncaughtExceptionHelper f6666h = new UncaughtExceptionHelper();

    protected class SimpleActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        final /* synthetic */ SmuleApplication f6656b;

        protected SimpleActivityLifecycleCallbacks(SmuleApplication smuleApplication) {
            this.f6656b = smuleApplication;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    class C19391 extends SimpleActivityLifecycleCallbacks {
        final /* synthetic */ SmuleApplication f6657a;

        C19391(SmuleApplication smuleApplication) {
            this.f6657a = smuleApplication;
            super(smuleApplication);
        }

        public void onActivityResumed(Activity activity) {
            Adjust.m1518b();
        }

        public void onActivityPaused(Activity activity) {
            Adjust.m1519c();
        }
    }

    class C19402 extends SimpleActivityLifecycleCallbacks {
        final /* synthetic */ SmuleApplication f6658a;

        C19402(SmuleApplication smuleApplication) {
            this.f6658a = smuleApplication;
            super(smuleApplication);
        }

        public void onActivityStarted(Activity activity) {
            this.f6658a.f6663e.add(activity.getClass().getCanonicalName());
            if (this.f6658a.f6663e.size() == 1) {
                Log.m7770b(SmuleApplication.f6659a, "App brought to foreground");
                synchronized (this.f6658a.f6664f) {
                    for (WeakReference weakReference : this.f6658a.f6664f) {
                        ApplicationLifecycleListener applicationLifecycleListener = (ApplicationLifecycleListener) weakReference.get();
                        if (applicationLifecycleListener != null) {
                            applicationLifecycleListener.mo4104a();
                        }
                    }
                }
            }
        }

        public void onActivityStopped(Activity activity) {
            this.f6658a.f6663e.remove(activity.getClass().getCanonicalName());
            if (this.f6658a.f6663e.isEmpty()) {
                Log.m7770b(SmuleApplication.f6659a, "App sent to background");
                synchronized (this.f6658a.f6664f) {
                    for (WeakReference weakReference : this.f6658a.f6664f) {
                        ApplicationLifecycleListener applicationLifecycleListener = (ApplicationLifecycleListener) weakReference.get();
                        if (applicationLifecycleListener != null) {
                            applicationLifecycleListener.mo4105b();
                        }
                    }
                }
            }
        }
    }

    public interface ApplicationLifecycleListener {
        void mo4104a();

        void mo4105b();
    }

    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this.f6666h);
        f6660d = this;
        if (this.f6661b) {
            registerActivityLifecycleCallbacks(new SessionRefreshActivityLifecycleCallbacks());
        }
        registerActivityLifecycleCallbacks(this.f6662c);
        ReLinker.m7257a(this, "cma");
    }

    public static Context m7644a() {
        return f6660d;
    }

    public static MagicCrittercism m7647b() {
        return f6660d != null ? f6660d.f6665g : null;
    }

    protected static void m7646a(Context context, int i, boolean z) {
        if (f6660d != null && f6660d.f6665g == null) {
            f6660d.f6665g = new MagicCrittercism(context, i, z);
        }
    }

    protected void m7650a(AdjustAttributionSettings adjustAttributionSettings) {
        AdjustConfig adjustConfig = new AdjustConfig(this, adjustAttributionSettings.m7656a(), adjustAttributionSettings.m7658b());
        adjustConfig.m1525a(LogLevel.DEBUG);
        Adjust.m1516a(adjustConfig);
        registerActivityLifecycleCallbacks(new C19391(this));
    }

    public void m7649a(ApplicationLifecycleListener applicationLifecycleListener) {
        synchronized (this.f6664f) {
            this.f6664f.add(new WeakReference(applicationLifecycleListener));
        }
    }

    public UncaughtExceptionHelper m7651c() {
        return this.f6666h;
    }
}
