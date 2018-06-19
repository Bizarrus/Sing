package com.smule.singandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat.OnRequestPermissionsResultCallback;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster.Duration;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.registration.LoginActivity;
import com.smule.singandroid.registration.NewHandleActivity_;
import com.smule.singandroid.registration.RegisterActivity;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.task.InitAppTask;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Locale;
import java.util.Observer;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@EActivity
public abstract class BaseActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback, OnViewChangedListener {
    public static final String f7016a = BaseActivity.class.getName();
    public static Uri f7017b;
    protected static boolean f7018c = false;
    protected static NetworkResponse f7019d;
    private static boolean f7020h;
    private static boolean f7021i = false;
    private static boolean f7022j = false;
    private static String f7023k = null;
    private static Intent f7024l = null;
    private static Locale f7025y;
    protected boolean f7026e = true;
    public boolean f7027f = true;
    private LifecycleState f7028g = null;
    private TextAlertDialog f7029m = null;
    private Handler f7030n;
    private Dialog f7031o;
    private boolean f7032p;
    private RunTimePermissionsRequester f7033q = null;
    private OnAudioFocusChangeListener f7034r = new AudioManagerFocusListener(this, null);
    private int f7035s = 0;
    private Observer f7036t = new 2(this);
    private Observer f7037u = new 4(this);
    private Observer f7038v = new 5(this);
    private Observer f7039w = new 6(this);
    private Observer f7040x = new 7(this);

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        Log.m7770b(f7016a, "onCreate:" + getClass().getName());
        if (bundle != null && bundle.containsKey("HAS_MEASURED_APP_STARTUP")) {
            SingAnalytics.c = bundle.getBoolean("HAS_MEASURED_APP_STARTUP");
        }
        this.f7028g = LifecycleState.a;
        m8628a(SingApplication.m8799g().m8820p());
        ReferenceMonitor.m8403a().m8408a((Object) this);
        f7017b = Uri.parse("android-app://" + getApplicationContext().getPackageName() + "/smulesing/");
        this.f7030n = new Handler(getMainLooper());
        NotificationCenter.a().a("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.f7040x);
        NotificationCenter.a().a("MagicNetwork.UPGRADE_REQUIRED_EVENT", this.f7038v);
        NotificationCenter.a().a("MagicNetwork.SERVER_MAINTENANCE_EVENT", this.f7037u);
        if (this.f7026e) {
            NotificationCenter.a().a("AUTO_LOGIN_FAILED", this.f7039w);
        }
        if (!f7022j) {
            InitAppTask.a();
            f7022j = true;
        }
        boolean z2 = this instanceof StartupActivity;
        if (SingApplication.f7089d || SingApplication.f7090e) {
            z = false;
        }
        if (z2 && !isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && action != null && action.equals("android.intent.action.MAIN")) {
                finish();
            }
        }
        if (z && UserManager.m8111a().m8231z()) {
            new InitAppTask(this).execute(new Void[0]);
        }
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.a(context));
    }

    protected void onStart() {
        super.onStart();
        Log.m7770b(f7016a, "onStart: " + getClass().getName());
        this.f7028g = LifecycleState.b;
        EventLogger2.m7730a((Activity) this);
        NotificationCenter.a().a(getClass().getName(), this.f7036t);
    }

    protected void onRestart() {
        super.onRestart();
        Log.m7770b(f7016a, "onRestart: " + getClass().getName());
    }

    protected void onResume() {
        super.onResume();
        Log.m7770b(f7016a, "onResume: " + getClass().getName());
        this.f7028g = LifecycleState.c;
        m8633e();
        try {
            ((AudioManager) getSystemService("audio")).setMode(0);
        } catch (Throwable e) {
            MagicCrittercism.m7780a(e);
        }
        setVolumeControlStream(3);
        if (f7018c) {
            m8607a(f7019d);
            f7018c = false;
            f7019d = null;
        }
        m8642n();
    }

    protected void onPostResume() {
        super.onPostResume();
        Log.m7770b(f7016a, "onPostResume: " + getClass().getName());
    }

    protected void onPause() {
        super.onPause();
        Log.m7770b(f7016a, "onPause: " + getClass().getName());
        this.f7028g = LifecycleState.d;
        MediaPlayerServiceController.a().r();
    }

    protected void onStop() {
        super.onStop();
        Log.m7770b(f7016a, "onStop:" + getClass().getName());
        this.f7028g = LifecycleState.e;
        EventLogger2.m7739b((Activity) this);
        NotificationCenter.a().b(getClass().getName(), this.f7036t);
    }

    @AfterViews
    protected final void m8631c() {
        if (!m8636h()) {
            mo4100d();
        }
    }

    protected void mo4100d() {
    }

    protected void onDestroy() {
        Log.m7770b(f7016a, "onDestroy:" + getClass().getName());
        this.f7028g = LifecycleState.g;
        mo4075a();
        if (this.f7026e) {
            NotificationCenter.a().b("AUTO_LOGIN_FAILED", this.f7039w);
        }
        NotificationCenter.a().b("MagicNetwork.UPGRADE_REQUIRED_EVENT", this.f7038v);
        NotificationCenter.a().b("MagicNetwork.SERVER_MAINTENANCE_EVENT", this.f7037u);
        NotificationCenter.a().b("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.f7040x);
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        Log.m7770b(f7016a, "onSaveInstanceState:" + getClass().getName());
        bundle.putBoolean("HAS_MEASURED_APP_STARTUP", SingAnalytics.c);
        bundle.putBoolean("HAS_MEASURED_SONGBOOK_LOAD", SingAnalytics.d);
        super.onSaveInstanceState(bundle);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle.containsKey("HAS_MEASURED_APP_STARTUP")) {
            SingAnalytics.c = bundle.getBoolean("HAS_MEASURED_APP_STARTUP");
        }
        if (bundle.containsKey("HAS_MEASURED_SONGBOOK_LOAD")) {
            SingAnalytics.d = bundle.getBoolean("HAS_MEASURED_SONGBOOK_LOAD");
        }
    }

    public void finish() {
        this.f7028g = LifecycleState.f;
        super.finish();
        Log.m7770b(f7016a, "finish:" + getClass().getName());
    }

    @SupposeUiThread
    public void m8623a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        if (this.f7033q != null) {
            throw new IllegalStateException("A permission request is already in progress");
        }
        this.f7033q = new RunTimePermissionsRequester(runTimePermissionsRequest, new 1(this, resultCallback));
        this.f7033q.a(this);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.f7033q != null) {
            this.f7033q.a(this, i, strArr, iArr);
        }
    }

    public void mo4073a(HasViews hasViews) {
    }

    private void mo4075a() {
        try {
            HasViews junkView = new JunkView(this);
            setContentView((View) junkView);
            mo4073a(junkView);
        } catch (Throwable e) {
            MagicCrittercism.m7780a(new Exception("Issue clearing out memory in BaseFragment.onDestroy", e));
        }
    }

    protected void m8633e() {
    }

    public boolean m8634f() {
        return this.f7028g == LifecycleState.c;
    }

    public boolean m8635g() {
        return this.f7028g == LifecycleState.b || this.f7028g == LifecycleState.c || this.f7028g == LifecycleState.d;
    }

    public boolean m8636h() {
        return this.f7028g == LifecycleState.f || this.f7028g == LifecycleState.g || isFinishing();
    }

    protected void m8637i() {
        if (((AudioManager) getApplicationContext().getSystemService("audio")).requestAudioFocus(this.f7034r, 3, 1) == 1) {
            Log.m7770b(f7016a, "requestAudioFocus - AUDIOFOCUS_REQUEST_GRANTED");
            m8630a(false);
        }
    }

    protected void m8638j() {
        Log.m7770b(f7016a, "abandonAudioFocus called");
        ((AudioManager) getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f7034r);
    }

    public void startActivity(Intent intent) {
        if (intent.getComponent() == null || intent.getComponent().getClassName() == null || !f7021i || intent.getComponent().getClassName().equals(RegistrationEntryActivity.class.getName()) || intent.getComponent().getClassName().equals(LoginActivity.class.getName()) || intent.getComponent().getClassName().equals(RegisterActivity.class.getName()) || intent.getComponent().getClassName().equals(NewHandleActivity_.class.getName())) {
            super.startActivity(intent);
            return;
        }
        Log.m7770b(f7016a, "startActivity login is initiated. Skipping activity start. Intent: " + intent);
        f7024l = intent;
    }

    protected void m8630a(boolean z) {
    }

    protected void m8639k() {
    }

    public void m8624a(Runnable runnable) {
        NotificationCenter.a().a(getClass().getName(), runnable);
    }

    public void m8625a(Runnable runnable, long j) {
        this.f7030n.postDelayed(new 3(this, runnable), j);
    }

    protected void m8640l() {
    }

    protected boolean m8641m() {
        return this.f7032p || f7020h;
    }

    private void mo4084b() {
        Log.m7770b(f7016a, "handleAutoLoginAccountFrozen");
        Intent intent = new Intent(this, AccountFrozenActivity_.class);
        f7020h = true;
        m8625a(new 8(this, intent), 200);
    }

    private void m8607a(NetworkResponse networkResponse) {
        Log.m7770b(f7016a, "handleAutoLoginFailed:" + (networkResponse != null ? Integer.valueOf(networkResponse.f6756b) : "null"));
        if (m8641m()) {
            Log.m7770b(f7016a, "handleAutoLoginFailed:error dialog already showing");
        } else if (networkResponse != null && networkResponse.f6756b == 1002) {
            mo4084b();
        } else if (f7021i) {
            Log.m7770b(f7016a, "handleAutoLoginFailed:login already initiated");
        } else {
            f7021i = true;
            MagicNetwork.m7808g().post(new 9(this));
        }
    }

    public void m8642n() {
        if (f7023k != null) {
            mo4099c(f7023k);
            f7023k = null;
        }
    }

    private void mo4099c(String str) {
        Log.m7770b(f7016a, "showForceUpgrade " + str);
        Intent intent = new Intent(this, ForcedUpgradeActivity_.class);
        intent.putExtra(ForcedUpgradeActivity.g, str);
        f7020h = true;
        m8625a(new 10(this, intent), 200);
    }

    public void m8622a(int i, boolean z, Runnable runnable, Runnable runnable2) {
        runOnUiThread(new 11(this, i, z, runnable2, runnable));
    }

    public void m8626a(Runnable runnable, Runnable runnable2) {
        runOnUiThread(new 12(this, runnable, runnable2));
    }

    public void m8621a(int i, boolean z, Runnable runnable) {
        m8622a(i, z, runnable, null);
    }

    protected void a_(String str) {
        m8627a(str, Duration.b);
    }

    protected void m8620a(int i, Duration duration) {
        m8627a(getResources().getString(i), duration);
    }

    protected void m8627a(String str, Duration duration) {
        Toaster.m8431a((Context) this, str, duration);
    }

    public void onBackPressed() {
        Log.m7774d(f7016a, "onBackPressed - DEFAULT implementation called in BaseActivity");
        super.onBackPressed();
    }

    protected boolean m8643o() {
        return f7021i;
    }

    protected void m8628a(Locale locale) {
        String str;
        if (locale == null || locale.equals(Locale.getDefault())) {
            str = "Using system default locale";
        } else {
            str = "Setting a custom locale: " + locale;
            Configuration configuration = getBaseContext().getResources().getConfiguration();
            Locale.setDefault(locale);
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        }
        if (f7025y == null || !f7025y.equals(locale)) {
            Log.m7772c(f7016a, str);
        }
        f7025y = locale;
    }
}
