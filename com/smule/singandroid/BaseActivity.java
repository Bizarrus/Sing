/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.app.Fragment
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v13.app.FragmentCompat
 *  android.support.v13.app.FragmentCompat$OnRequestPermissionsResultCallback
 *  android.support.v7.app.AppCompatActivity
 *  android.view.View
 *  android.view.View$OnClickListener
 *  com.smule.singandroid.task.InitAppTask
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.smule.android.l10n.LocalizationActivityDelegate;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.AccountFrozenActivity_;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.ForcedUpgradeActivity;
import com.smule.singandroid.ForcedUpgradeActivity_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.StartupActivity;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.registration.LoginActivity;
import com.smule.singandroid.registration.RegisterActivity;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.registration.WelcomeActivity_;
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
public abstract class BaseActivity
extends AppCompatActivity
implements FragmentCompat.OnRequestPermissionsResultCallback,
OnViewChangedListener {
    public static final String a = BaseActivity.class.getName();
    public static Uri b;
    protected static boolean c;
    protected static NetworkResponse d;
    private static boolean h;
    private static boolean i;
    private static boolean j;
    private static String k;
    private static Intent l;
    protected boolean e = true;
    public boolean f;
    private  g = null;
    private TextAlertDialog m = null;
    private Handler n;
    private Dialog o;
    private boolean p;
    private RunTimePermissionsRequester q = null;
    private LocalizationActivityDelegate r;
    private AudioManager.OnAudioFocusChangeListener s;
    private int t;
    private Observer u;
    private Observer v;
    private Observer w;
    private Observer x;
    private Observer y;

    static {
        c = false;
        i = false;
        j = false;
        k = null;
        l = null;
    }

    public BaseActivity() {
        this.r = new LocalizationActivityDelegate((Activity)this);
        this.f = true;
        this.s = new AudioManager.OnAudioFocusChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onAudioFocusChange(int n) {
                switch (n) {
                    default: {
                        Log.b(BaseActivity.a, "onAudioFocusChange - " + n);
                        break;
                    }
                    case -3: {
                        Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                        break;
                    }
                    case -2: {
                        Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_LOSS_TRANSIENT");
                        BaseActivity.this.n();
                        break;
                    }
                    case -1: {
                        Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_LOSS");
                        BaseActivity.this.n();
                        break;
                    }
                    case 1: {
                        Log.b(BaseActivity.a, "onAudioFocusChange - AUDIOFOCUS_GAIN");
                        BaseActivity baseActivity = BaseActivity.this;
                        boolean bl = BaseActivity.this.t == -3;
                        baseActivity.a(bl);
                    }
                }
                BaseActivity.this.t = n;
            }
        };
        this.t = 0;
        this.u = new Observer(this){
            final /* synthetic */ BaseActivity a;
            {
                this.a = baseActivity;
            }

            public void update(java.util.Observable object, Object object2) {
                if (object2 instanceof Runnable) {
                    object = (Runnable)object2;
                    this.a.runOnUiThread(new Runnable(this, (Runnable)object){
                        final /* synthetic */ Runnable a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = runnable;
                        }

                        public void run() {
                            if (this.b.a.p_()) {
                                this.a.run();
                            }
                        }
                    });
                }
            }
        };
        this.v = new Observer(this){
            final /* synthetic */ BaseActivity a;
            {
                this.a = baseActivity;
            }

            public void update(java.util.Observable observable, Object object) {
                this.a.runOnUiThread(new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        if (BaseActivity.b(this.a.a) == null) {
                            android.app.AlertDialog$Builder builder = new android.app.AlertDialog$Builder((Context)this.a.a);
                            builder.setMessage(2131296966);
                            builder.setCancelable(false);
                            builder.setPositiveButton(2131297033, new android.content.DialogInterface$OnClickListener(this){
                                final /* synthetic */ com.smule.singandroid.BaseActivity$4$1 a;
                                {
                                    this.a = var1_1;
                                }

                                public void onClick(android.content.DialogInterface dialogInterface, int n) {
                                    BaseActivity.b(this.a.a.a).dismiss();
                                    BaseActivity.a(this.a.a.a, false);
                                    this.a.a.a.o();
                                }
                            });
                            BaseActivity.a(this.a.a, (Dialog)builder.create());
                        }
                        if (!BaseActivity.b(this.a.a).isShowing()) {
                            BaseActivity.b(this.a.a).show();
                            SingAnalytics.a((com.smule.singandroid.customviews.BottomNavView$Tab)null);
                            BaseActivity.a(this.a.a, true);
                        }
                    }
                });
            }
        };
        this.w = new Observer(this){
            final /* synthetic */ BaseActivity a;
            {
                this.a = baseActivity;
            }

            public void update(java.util.Observable object, Object object2) {
                if (object2 == null) {
                    Log.e(BaseActivity.a, "No object dispatched with UPGRADE_REQUIRED_EVENT notification");
                    return;
                }
                object = (String)object2;
                if (object.isEmpty()) {
                    Log.e(BaseActivity.a, "Malformed string sent with UPGRADE_REQUIRED_EVENT notification");
                    return;
                }
                Log.c(BaseActivity.a, "Force upgrade notification received! Url: " + (String)object);
                BaseActivity.b((String)object);
                BaseActivity.a(this.a, (String)object);
            }
        };
        this.x = new Observer(this){
            final /* synthetic */ BaseActivity a;
            {
                this.a = baseActivity;
            }

            public void update(java.util.Observable observable, Object object) {
                this.a.runOnUiThread(new Runnable(this, object){
                    final /* synthetic */ Object a;
                    final /* synthetic */  b;
                    {
                        this.b = var1_1;
                        this.a = object;
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void run() {
                        NetworkResponse networkResponse = this.a instanceof NetworkResponse ? (NetworkResponse)((Object)this.a) : null;
                        if (this.b.a.p_()) {
                            BaseActivity.a(this.b.a, networkResponse);
                            return;
                        }
                        Log.b(BaseActivity.a, "AUTO_LOGIN_FAILED:" + this.b.a.getClass().getName() + ":setting static");
                        BaseActivity.c = true;
                        if (networkResponse != null) {
                            BaseActivity.d = networkResponse;
                        }
                    }
                });
            }
        };
        this.y = new Observer(this){
            final /* synthetic */ BaseActivity a;
            {
                this.a = baseActivity;
            }

            public void update(java.util.Observable observable, Object object) {
                if ((com.smule.android.uploader.FileUploaderService$VideoUploadStatus)((Object)((Bundle)object).get("FILE_UPLOAD_STATUS")) != com.smule.android.uploader.FileUploaderService$VideoUploadStatus.a) {
                    this.a.runOnUiThread(new Runnable(this){
                        final /* synthetic */  a;
                        {
                            this.a = var1_1;
                        }

                        public void run() {
                            com.smule.android.magicui.lists.adapters.MagicDataSource.a(com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                            com.smule.android.magicui.lists.adapters.MagicDataSource.a(com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                        }
                    });
                }
            }
        };
    }

    static /* synthetic */ Dialog a(BaseActivity baseActivity, Dialog dialog) {
        baseActivity.o = dialog;
        return dialog;
    }

    static /* synthetic */ Intent a(Intent intent) {
        l = intent;
        return intent;
    }

    static /* synthetic */ RunTimePermissionsRequester a(BaseActivity baseActivity, RunTimePermissionsRequester runTimePermissionsRequester) {
        baseActivity.q = runTimePermissionsRequester;
        return runTimePermissionsRequester;
    }

    private void a() {
        try {
            HasViews hasViews = new HasViews((Context)this){};
            this.setContentView((View)hasViews);
            this.a(hasViews);
            return;
        }
        catch (Exception exception) {
            MagicCrittercism.a(new Exception("Issue clearing out memory in BaseFragment.onDestroy", exception));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(NetworkResponse networkResponse) {
        String string2 = a;
        StringBuilder stringBuilder = new StringBuilder().append("handleAutoLoginFailed:");
        Integer n = networkResponse != null ? Integer.valueOf(networkResponse.b) : "null";
        Log.b(string2, stringBuilder.append(n).toString());
        if (this.p()) {
            Log.b(a, "handleAutoLoginFailed:error dialog already showing");
            return;
        }
        if (networkResponse != null && networkResponse.b == 1002) {
            this.b();
            return;
        }
        if (i) {
            Log.b(a, "handleAutoLoginFailed:login already initiated");
            return;
        }
        i = true;
        MagicNetwork.g().post(new Runnable(this){
            final /* synthetic */ BaseActivity a;
            {
                this.a = baseActivity;
            }

            public void run() {
                Log.c(BaseActivity.a, "Calling initiateRegistration");
                com.smule.singandroid.utils.NavigationUtils.a((Activity)this.a, (com.smule.singandroid.registration.RegistrationCallbacks$LoggedInCallback)new com.smule.singandroid.registration.RegistrationCallbacks$LoggedInCallback(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void a() {
                        BaseActivity.b(false);
                        Log.b(BaseActivity.a, "AfterLoginIntent is : " + (Object)BaseActivity.s());
                        if (BaseActivity.s() != null) {
                            Intent intent = BaseActivity.s();
                            BaseActivity.a(null);
                            this.a.a.startActivity(intent);
                        }
                    }
                }, (com.smule.singandroid.registration.RegistrationCallbacks$DeviceLookupFailedCallback)new com.smule.singandroid.registration.RegistrationCallbacks$DeviceLookupFailedCallback(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void a(NetworkResponse object) {
                        Integer n = null;
                        if (object != null) {
                            n = object.h();
                        }
                        Log.b(BaseActivity.a, "initiateRegistration: device lookup failed: code " + n);
                        object = new com.smule.singandroid.dialogs.BusyDialog((Activity)this.a.a, this.a.a.getString(2131296896));
                        object.a(new com.smule.singandroid.dialogs.BusyDialog$BusyDialogListener(this){
                            final /* synthetic */ com.smule.singandroid.BaseActivity$9$2 a;
                            {
                                this.a = var1_1;
                            }

                            public void a() {
                                BaseActivity.b(false);
                                BaseActivity.a(this.a.a.a, null);
                            }
                        });
                        object.a(2, this.a.a.getString(2131296895), n);
                        object.show();
                    }
                });
            }
        });
    }

    static /* synthetic */ void a(BaseActivity baseActivity, Intent intent) {
        super.startActivity(intent);
    }

    static /* synthetic */ void a(BaseActivity baseActivity, NetworkResponse networkResponse) {
        baseActivity.a(networkResponse);
    }

    static /* synthetic */ void a(BaseActivity baseActivity, String string2) {
        baseActivity.c(string2);
    }

    static /* synthetic */ boolean a(BaseActivity baseActivity, boolean bl) {
        baseActivity.p = bl;
        return bl;
    }

    static /* synthetic */ Dialog b(BaseActivity baseActivity) {
        return baseActivity.o;
    }

    static /* synthetic */ String b(String string2) {
        k = string2;
        return string2;
    }

    private void b() {
        Log.b(a, "handleAutoLoginAccountFrozen");
        Intent intent = new Intent((Context)this, AccountFrozenActivity_.class);
        h = true;
        this.a(new Runnable(this, intent){
            final /* synthetic */ Intent a;
            final /* synthetic */ BaseActivity b;
            {
                this.b = baseActivity;
                this.a = intent;
            }

            public void run() {
                BaseActivity.a(this.b, this.a);
            }
        }, 200);
    }

    static /* synthetic */ void b(BaseActivity baseActivity, Intent intent) {
        super.startActivity(intent);
    }

    static /* synthetic */ boolean b(boolean bl) {
        i = bl;
        return bl;
    }

    static /* synthetic */ TextAlertDialog c(BaseActivity baseActivity) {
        return baseActivity.m;
    }

    private void c(String string2) {
        Log.b(a, "showForceUpgrade " + string2);
        Intent intent = new Intent((Context)this, ForcedUpgradeActivity_.class);
        intent.putExtra(ForcedUpgradeActivity.g, string2);
        h = true;
        this.a(new Runnable(this, intent){
            final /* synthetic */ Intent a;
            final /* synthetic */ BaseActivity b;
            {
                this.b = baseActivity;
                this.a = intent;
            }

            public void run() {
                BaseActivity.b(this.b, this.a);
            }
        }, 200);
    }

    static /* synthetic */ Intent s() {
        return l;
    }

    protected void a(int n) {
        this.a(this.getResources().getString(n), Toaster.b);
    }

    protected void a(int n, Toaster duration) {
        this.a(this.getResources().getString(n), duration);
    }

    public void a(int n, boolean bl, Runnable runnable) {
        this.a(n, bl, runnable, null);
    }

    public void a(int n, boolean bl, Runnable runnable, Runnable runnable2) {
        this.runOnUiThread(new Runnable(this, n, bl, runnable2, runnable){
            final /* synthetic */ int a;
            final /* synthetic */ boolean b;
            final /* synthetic */ Runnable c;
            final /* synthetic */ Runnable d;
            final /* synthetic */ BaseActivity e;
            {
                this.e = baseActivity;
                this.a = n;
                this.b = bl;
                this.c = runnable;
                this.d = runnable2;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                if (BaseActivity.c(this.e) != null && BaseActivity.c(this.e).isShowing()) {
                    return;
                }
                Object object = com.smule.singandroid.utils.MiscUtils.a((int)this.a, (java.lang.Boolean)this.b);
                Object object2 = this.e;
                Object object3 = (String)object.first;
                object = (CharSequence)object.second;
                boolean bl = this.c != null;
                object3 = new TextAlertDialog((Context)object2, (String)object3, (CharSequence)object, bl, true);
                object2 = this.c != null ? this.e.getString(2131296857) : null;
                object3.a((String)object2, this.e.getString(2131296705));
                if (this.d != null) {
                    object3.b(this.d);
                }
                if (this.c != null) {
                    object3.a(this.c);
                }
                object3.show();
            }
        });
    }

    @SupposeUiThread
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        if (this.q != null) {
            throw new IllegalStateException("A permission request is already in progress");
        }
        this.q = new RunTimePermissionsRequester(runTimePermissionsRequest, new RunTimePermissionsRequester.ResultCallback(this, resultCallback){
            final /* synthetic */ RunTimePermissionsRequester.ResultCallback a;
            final /* synthetic */ BaseActivity b;
            {
                this.b = baseActivity;
                this.a = resultCallback;
            }

            public void a(boolean bl, @NonNull java.util.Set<String> set) {
                BaseActivity.a(this.b, null);
                if (this.a != null) {
                    this.a.a(bl, set);
                }
            }
        });
        this.q.a(this);
    }

    public void a(Runnable runnable) {
        NotificationCenter.a().a(this.getClass().getName(), (Object)runnable);
    }

    public void a(Runnable runnable, long l) {
        this.n.postDelayed(new Runnable(this, runnable){
            final /* synthetic */ Runnable a;
            final /* synthetic */ BaseActivity b;
            {
                this.b = baseActivity;
                this.a = runnable;
            }

            public void run() {
                this.b.a(this.a);
            }
        }, l);
    }

    public void a(Runnable runnable, Runnable runnable2) {
        this.runOnUiThread(new Runnable(this, runnable, runnable2){
            final /* synthetic */ Runnable a;
            final /* synthetic */ Runnable b;
            final /* synthetic */ BaseActivity c;
            {
                this.c = baseActivity;
                this.a = runnable;
                this.b = runnable2;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                String string2 = null;
                boolean bl = true;
                Object object = this.c;
                Object object2 = this.c.getString(2131297114);
                String string3 = this.c.getString(2131297113);
                boolean bl2 = this.a != null;
                if (this.b == null) {
                    bl = false;
                }
                object2 = new TextAlertDialog((Activity)object, (String)object2, string3, bl2, bl){

                    @Override
                    public void onBackPressed() {
                        this.c();
                    }

                    @Override
                    public void setCanceledOnTouchOutside(boolean bl) {
                        this.a.setOnClickListener(new View.OnClickListener(){

                            public void onClick(View view) {
                                AlwaysTryAgainDialog.this.c();
                            }
                        });
                    }

                };
                object = this.a != null ? this.c.getString(2131296863) : null;
                if (this.b != null) {
                    string2 = this.c.getString(2131296742);
                }
                object2.a((String)object, string2);
                if (this.a != null) {
                    object2.a(this.a);
                }
                if (this.b != null) {
                    object2.b(this.b);
                }
                object2.show();
            }
        });
    }

    protected void a(String string2, Toaster duration) {
        com.smule.android.utils.Toaster.a((Context)this, string2, duration);
    }

    public final void a(Locale locale, boolean bl) {
        this.r.a((Context)this, locale, bl);
    }

    public void a(HasViews hasViews) {
    }

    protected void a(boolean bl) {
    }

    protected void a_(String string2) {
        this.a(string2, Toaster.b);
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext((Context)CalligraphyContextWrapper.a((Context)this.r.a(context)));
    }

    public final Locale c() {
        return this.r.c((Context)this);
    }

    public boolean d() {
        return false;
    }

    @AfterViews
    protected final void e() {
        if (!this.j()) {
            this.f();
        }
    }

    protected void f() {
    }

    public void finish() {
        this.g = .f;
        super.finish();
        Log.b(a, "finish:" + this.getClass().getName());
    }

    protected void g() {
    }

    public Context getApplicationContext() {
        return this.r.b(super.getApplicationContext());
    }

    public Resources getResources() {
        return this.r.a(super.getResources());
    }

    public boolean j() {
        if (this.g == .f || this.g == .g || this.isFinishing()) {
            return true;
        }
        return false;
    }

    public  k() {
        return this.g;
    }

    protected void l() throws IllegalStateException {
        if (((AudioManager)this.getApplicationContext().getSystemService("audio")).requestAudioFocus(this.s, 3, 1) == 1) {
            Log.b(a, "requestAudioFocus - AUDIOFOCUS_REQUEST_GRANTED");
            this.a(false);
            return;
        }
        throw new IllegalStateException("Audio focus Request Failed.");
    }

    protected void m() {
        Log.b(a, "abandonAudioFocus called");
        ((AudioManager)this.getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.s);
    }

    protected void n() {
    }

    protected void o() {
    }

    public void onBackPressed() {
        Log.d(a, "onBackPressed - DEFAULT implementation called in BaseActivity");
        super.onBackPressed();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        boolean bl = true;
        this.r.a(bundle);
        super.onCreate(bundle);
        Log.b(a, "onCreate:" + this.getClass().getName());
        if (bundle != null && bundle.containsKey("HAS_MEASURED_APP_STARTUP")) {
            SingAnalytics.c = bundle.getBoolean("HAS_MEASURED_APP_STARTUP");
        }
        this.g = .a;
        ReferenceMonitor.a().a((Object)this);
        b = Uri.parse((String)("android-app://" + this.getApplicationContext().getPackageName() + "/smulesing/"));
        this.n = new Handler(this.getMainLooper());
        NotificationCenter.a().a("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.y);
        NotificationCenter.a().a("MagicNetwork.UPGRADE_REQUIRED_EVENT", this.w);
        NotificationCenter.a().a("MagicNetwork.SERVER_MAINTENANCE_EVENT", this.v);
        if (this.e) {
            NotificationCenter.a().a("AUTO_LOGIN_FAILED", this.x);
        }
        if (!j) {
            InitAppTask.a((BaseActivity)this);
            j = true;
        }
        boolean bl2 = this instanceof StartupActivity;
        if (SingApplication.e || SingApplication.f) {
            bl = false;
        }
        if (!bl2) return;
        if (!this.isTaskRoot()) {
            bundle = this.getIntent();
            String string2 = bundle.getAction();
            if (bundle.hasCategory("android.intent.category.LAUNCHER") && string2 != null && string2.equals("android.intent.action.MAIN")) {
                this.finish();
            }
        }
        if (bl && UserManager.a().y()) {
            new InitAppTask((Activity)this).execute((Object[])new Void[0]);
        }
    }

    protected void onDestroy() {
        Log.b(a, "onDestroy:" + this.getClass().getName());
        this.g = .g;
        this.a();
        if (this.e) {
            NotificationCenter.a().b("AUTO_LOGIN_FAILED", this.x);
        }
        NotificationCenter.a().b("MagicNetwork.UPGRADE_REQUIRED_EVENT", this.w);
        NotificationCenter.a().b("MagicNetwork.SERVER_MAINTENANCE_EVENT", this.v);
        NotificationCenter.a().b("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.y);
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        Log.b(a, "onPause: " + this.getClass().getName());
        this.g = .d;
    }

    protected void onPostResume() {
        super.onPostResume();
        Log.b(a, "onPostResume: " + this.getClass().getName());
    }

    public void onRequestPermissionsResult(int n, @NonNull String[] arrstring, @NonNull int[] arrn) {
        if (this.q != null) {
            this.q.a(this, n, arrstring, arrn);
        }
    }

    protected void onRestart() {
        super.onRestart();
        Log.b(a, "onRestart: " + this.getClass().getName());
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onResume() {
        super.onResume();
        Log.b(a, "onResume: " + this.getClass().getName());
        this.g = .c;
        this.g();
        try {
            ((AudioManager)this.getSystemService("audio")).setMode(0);
        }
        catch (Exception exception) {
            MagicCrittercism.a(exception);
        }
        this.setVolumeControlStream(3);
        if (c) {
            this.a(d);
            c = false;
            d = null;
        }
        this.q();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        Log.b(a, "onSaveInstanceState:" + this.getClass().getName());
        bundle.putBoolean("HAS_MEASURED_APP_STARTUP", SingAnalytics.c);
        bundle.putBoolean("HAS_MEASURED_SONGBOOK_LOAD", SingAnalytics.d);
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        super.onStart();
        Log.b(a, "onStart: " + this.getClass().getName());
        this.g = .b;
        EventLogger2.a((Activity)this);
        NotificationCenter.a().a(this.getClass().getName(), this.u);
    }

    protected void onStop() {
        super.onStop();
        Log.b(a, "onStop:" + this.getClass().getName());
        this.g = .e;
        EventLogger2.b((Activity)this);
        NotificationCenter.a().b(this.getClass().getName(), this.u);
    }

    protected boolean p() {
        if (this.p || h) {
            return true;
        }
        return false;
    }

    public boolean p_() {
        if (this.g == .c) {
            return true;
        }
        return false;
    }

    public void q() {
        if (k != null) {
            this.c(k);
            k = null;
        }
    }

    public boolean q_() {
        if (this.g == .b || this.g == .c || this.g == .d) {
            return true;
        }
        return false;
    }

    protected boolean r() {
        return i;
    }

    public void startActivity(Intent intent) {
        if (!(intent.getComponent() == null || intent.getComponent().getClassName() == null || !i || intent.getComponent().getClassName().equals(RegistrationEntryActivity.class.getName()) || intent.getComponent().getClassName().equals(LoginActivity.class.getName()) || intent.getComponent().getClassName().equals(RegisterActivity.class.getName()) || intent.getComponent().getClassName().equals(WelcomeActivity_.class.getName()))) {
            Log.b(a, "startActivity login is initiated. Skipping activity start. Intent: " + (Object)intent);
            l = intent;
            return;
        }
        super.startActivity(intent);
    }

}

