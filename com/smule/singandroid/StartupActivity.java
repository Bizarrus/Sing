/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.view.View
 *  android.widget.ProgressBar
 *  com.crittercism.app.Crittercism
 *  com.facebook.AccessToken
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import com.crittercism.app.Crittercism;
import com.facebook.AccessToken;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.response.GetConnectedPerformancesResponse;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.SimpleBarrier;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatNotification;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.dialogs.WhatsNewDialog;
import com.smule.singandroid.onboarding.OnboardingActivity_;
import com.smule.singandroid.registration.RegistrationCallbacks;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.registration.WelcomeActivity_;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class StartupActivity
extends BaseActivity {
    private static final String h = StartupActivity.class.getName();
    private static boolean l;
    @ViewById
    protected ProgressBar g;
    private boolean i = false;
    private Uri j = null;
    private Handler k;
    private SimpleBarrier m;
    private TextAlertDialog n;
    private WhatsNewDialog o;
    private ErrorReason p;
    private boolean q = false;
    private long r;
    private Observer s;
    private boolean t;
    private Observer u;
    private HandleErrorRunnable v;

    public StartupActivity() {
        this.s = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                StartupActivity.this.finish();
            }
        };
        this.t = false;
        this.u = new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if (object instanceof com.smule.android.network.core.NetworkResponse) {
                    StartupActivity.this.runOnUiThread((Runnable)new HandleErrorRunnable(ErrorReason.b, (com.smule.android.network.core.NetworkResponse)((Object)object)));
                    return;
                }
                StartupActivity.this.runOnUiThread((Runnable)new HandleErrorRunnable(ErrorReason.b, null));
            }
        };
        this.v = new HandleErrorRunnable(ErrorReason.c, null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void u() {
        Log.c(h, "processIntentData: " + (Object)this.getIntent());
        String string2 = this.getIntent().getStringExtra("PARAMS");
        this.j = this.getIntent().getData();
        if (string2 == null) return;
        try {
            Object object = (Map)JsonUtils.a().readValue(string2, Map.class);
            String string3 = (String)object.get("y");
            object = (String)object.get("z");
            if (!(string3 != null && object != null || "v".equals(string3))) {
                Log.e(h, "Missing push type or/and id type=" + string3 + " id=" + (String)object);
                return;
            }
            if (ChatNotification.a(string3, (String)object, this.j, this.getIntent())) return;
            SingAnalytics.a((String)string3, (String)object, (String)this.j.toString());
            return;
        }
        catch (Exception exception) {
            Log.d(h, "Failed to parse push notification params " + string2, exception);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean v() {
        boolean bl = true;
        this.i = UserManager.a().y();
        Log.c(h, "setupBarrier: " + this.i + " / " + UserManager.a().E() + " / " + (Object)MagicFacebook.a().b());
        if (this.i && UserManager.a().E() && MagicFacebook.a().b() == null) {
            SingAnalytics.b((String)"facebook", (String)"client_error", (String)"forced logout due to null access token", (String)null);
        }
        if (!this.i && this.r()) {
            Log.c(h, "setupBarrier: BaseActivity has already started login process; retry intent later");
            this.startActivity(this.getIntent());
            return false;
        }
        this.m = new SimpleBarrier(1, new Runnable(){

            @Override
            public void run() {
                StartupActivity.this.b();
            }
        });
        boolean bl2 = bl;
        if (l) return bl2;
        l = true;
        this.m.d();
        this.k.postDelayed(new Runnable(){

            @Override
            public void run() {
                Log.b(h, "splash screen done");
                StartupActivity.this.m.a();
                if (StartupActivity.this.p_()) {
                    if (StartupActivity.this.o != null) {
                        StartupActivity.this.o.show();
                        SingAnalytics.a((BottomNavView.Tab)null);
                    }
                    StartupActivity.this.g.setVisibility(0);
                }
            }
        }, 1000);
        bl2 = bl;
        if (!SingApplication.h().o()) return bl2;
        bl2 = bl;
        if (!this.i) return bl2;
        this.m.d();
        bl2 = bl;
        if (this.o != null) return bl2;
        this.o = new WhatsNewDialog(this);
        this.o.a(new Runnable(){

            @Override
            public void run() {
                Log.b(h, "what's new done");
                StartupActivity.this.m.a();
                StartupActivity.this.k.postDelayed((Runnable)StartupActivity.this.v, (long)StartupActivity.this.getResources().getInteger(2131623974));
                StartupActivity.this.o = null;
            }
        });
        this.o.setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                Log.b(h, "what's new cancelled");
                StartupActivity.this.m.a();
                StartupActivity.this.k.postDelayed((Runnable)StartupActivity.this.v, (long)StartupActivity.this.getResources().getInteger(2131623974));
                StartupActivity.this.o = null;
            }
        });
        return true;
    }

    private void w() {
        if (!NetworkUtils.a((Context)this)) {
            this.k.postDelayed(new Runnable(){

                @Override
                public void run() {
                    StartupActivity.this.a(ErrorReason.e, null);
                }
            }, 0);
            return;
        }
        if (this.n != null) {
            this.n.dismiss();
        }
        if (!this.i) {
            NavigationUtils.a((Activity)this, (RegistrationCallbacks.LoggedInCallback)new RegistrationCallbacks.LoggedInCallback(){

                @Override
                public void a() {
                    if (RegistrationContext.e()) {
                        com.smule.android.network.managers.PerformanceManager.a().a(new PerformanceManager(){

                            @Override
                            public void handleResponse(GetConnectedPerformancesResponse getConnectedPerformancesResponse) {
                            }
                        });
                    }
                }

            }, (RegistrationCallbacks.DeviceLookupFailedCallback)new RegistrationCallbacks.DeviceLookupFailedCallback(){

                @Override
                public void a(com.smule.android.network.core.NetworkResponse networkResponse) {
                    StartupActivity.this.runOnUiThread((Runnable)new HandleErrorRunnable(ErrorReason.d, networkResponse));
                }
            });
            return;
        }
        this.x();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void x() {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (this.q) {
            if (!AppSettingsManager.a().d()) {
                Log.b(h, "Adding settings to dependencies list");
                arrayList.add("InitAppTask.OP_LOAD_SETTINGS");
            }
        } else {
            arrayList.addAll(Arrays.asList("InitAppTask.OP_LOAD_SETTINGS", "InitAppTask.OP_TRIM_CACHE"));
        }
        SingApplication.d().a("StartupActivity.OP_STARTUP_WAIT_FOR_DEPENDENCIES", arrayList, new Runnable(){

            @Override
            public void run() {
                SingApplication.d().a("StartupActivity.OP_STARTUP_WAIT_FOR_DEPENDENCIES");
                StartupActivity.this.runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        if (StartupActivity.this.q && !AppSettingsManager.a().d()) {
                            StartupActivity.this.a(ErrorReason.f, null);
                            return;
                        }
                        StartupActivity.this.a();
                    }
                });
            }

        });
        if (this.o == null) {
            this.k.postDelayed((Runnable)this.v, (long)this.getResources().getInteger(2131623974));
        }
    }

    private void y() {
        SingApplication.d().a("StartupActivity.OP_STARTUP_WAIT_FOR_DEPENDENCIES");
        this.k.removeCallbacks((Runnable)this.v);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void a() {
        Log.b(h, "onOperationDependenciesComplete");
        if (!NetworkUtils.a((Context)this)) {
            this.a(ErrorReason.e, null);
            return;
        }
        if (!this.i) {
            this.a(ErrorReason.a, null);
            return;
        }
        if (UserManager.a().z()) {
            this.a(ErrorReason.a, null);
            return;
        }
        if (!this.q) {
            boolean bl = MagicNetwork.a().l() && !MagicNetwork.a().i();
            if (!bl) {
                this.a(ErrorReason.a, null);
                return;
            }
        }
        this.m.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void a(@NonNull ErrorReason object, com.smule.android.network.core.NetworkResponse object2) {
        boolean bl = false;
        Object var5_4 = null;
        Object object3 = NetworkResponse.e;
        this.p = object;
        Log.b(h, "handleError:" + (Object)((Object)this.p));
        if (!this.p_()) {
            Log.b(h, "handleError:not running, ignoring");
            return;
        } else {
            switch (.a[this.p.ordinal()]) {
                case 3: 
                case 4: {
                    if (!this.i) {
                        Log.b(h, "handleError:ignore errors during registration");
                        return;
                    } else {
                        break;
                    }
                }
            }
            object = var5_4;
            if (object2 != null) {
                object3 = object2.a;
                object = object2.h();
            }
            this.y();
            this.g.setVisibility(8);
            if (object != null && (object.intValue() == 1009 || object.intValue() == 1032)) {
                UserManager.a().A();
                this.i = false;
                this.w();
                return;
            }
            if (this.n == null) {
                if (object == null) {
                    object = this.getString(2131296795);
                    object2 = this.getString(2131296895);
                    bl = true;
                } else {
                    if (object.intValue() == 2000) {
                        Log.b(h, "CODE_UPGRADE_REQUIRED handled in BaseActivity");
                        return;
                    }
                    if (object3 == NetworkResponse.g) {
                        Log.b(h, "SERVER_MAINTENANCE handled in BaseActivity");
                        return;
                    }
                    if (object.intValue() == 69 || object.intValue() == 72) {
                        object = this.getString(2131296909);
                        object2 = this.getString(2131296410);
                        UserManager.a().A();
                        bl = true;
                    } else if (object.intValue() == 1002) {
                        object = this.getString(2131296909);
                        object2 = this.getString(2131296894);
                    } else {
                        object2 = this.getString(2131296795);
                        object3 = this.getString(2131296895) + "\n" + this.getString(2131296725, new Object[]{object});
                        bl = true;
                        object = object2;
                        object2 = object3;
                    }
                }
                this.n = new TextAlertDialog((Context)this, (String)object, (CharSequence)object2, bl, bl);
                if (bl) {
                    this.n.a(2131296863, 2131296710);
                }
                this.n.c(true);
                this.n.a(new CustomAlertDialog.CustomAlertDialogListener(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void a(CustomAlertDialog customAlertDialog) {
                        StartupActivity.this.i = UserManager.a().y();
                        if (!StartupActivity.this.q && !StartupActivity.this.i) {
                            boolean bl = MagicNetwork.a().l() && !MagicNetwork.a().i();
                            if (!bl && !MagicNetwork.a().h()) {
                                MagicNetwork.a(new Runnable(){

                                    @Override
                                    public void run() {
                                        UserManager.a().D();
                                        MagicNetwork.a().a(true);
                                    }
                                });
                            }
                        }
                        if (StartupActivity.this.p == ErrorReason.f) {
                            SingApplication.d().b("InitAppTask.OP_LOAD_SETTINGS");
                        }
                        StartupActivity.this.w();
                        StartupActivity.this.g.setVisibility(0);
                        SingAnalytics.v();
                        SingAnalytics.a((long)(System.currentTimeMillis() - StartupActivity.this.r));
                    }

                    @Override
                    public void b(CustomAlertDialog customAlertDialog) {
                        StartupActivity.this.finish();
                        System.exit(0);
                    }

                });
            }
            if (this.n.isShowing()) return;
            {
                this.n.show();
                this.r = System.currentTimeMillis();
                object = NetworkUtils.a((Context)this) ? Analytics.b : Analytics.c;
            }
        }
        SingAnalytics.a(object);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void b() {
        Intent intent;
        Log.c(h, "onBarrierDone");
        if (this.q && this.p()) {
            Log.b(h, "onBarrierDone:BaseActivity showing dialog");
            return;
        }
        if (RegistrationContext.n() && !this.r()) {
            if (RegistrationContext.f() != null) {
                intent = new Intent((Context)this, WelcomeActivity_.class);
                intent.putExtra("param_handle", RegistrationContext.i());
                intent.putExtra("param_handle_prefill", RegistrationContext.j());
                intent.putExtra("PARAM_LOGIN_METHOD", (Serializable)((Object)Analytics.valueOf(RegistrationContext.k())));
                intent.putExtra("SHOW_EMAIL_OPT", RegistrationContext.l());
                intent.putExtra("FACEBOOK_PHOTO_AUTO_UPLOADED", RegistrationContext.m());
            } else {
                intent = new Intent((Context)this, OnboardingActivity_.class);
                String string2 = RegistrationContext.o();
                if (RegistrationContext.o() != null) {
                    intent.putExtra("ONBOARDING_TOPICS", string2);
                }
                intent.putExtra("BEGIN_ONBOARDING", true);
            }
        } else if (this.j != null) {
            intent = new Intent((Context)this, MasterActivity_.class);
            intent.setData(this.j);
            intent.putExtra("BEGIN_ONBOARDING", false);
            NotificationCenter.a().b("ONBOARDING_FINISHED", new Object[0]);
        } else {
            NotificationCenter.a().b("ONBOARDING_FINISHED", new Object[0]);
            intent = new Intent((Context)this, MasterActivity_.class);
        }
        if (!this.t) {
            this.t = true;
            this.startActivity(intent);
            if (this.r() && !RegistrationEntryActivity.a()) {
                return;
            }
        } else {
            Log.c(h, "MasterActivity already started");
        }
        this.finish();
    }

    @Override
    protected void o() {
        this.finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        this.e = this.q = MagicNetwork.a().s();
        super.onCreate(bundle);
        Crittercism.sendAppLoadData();
        this.k = new Handler();
    }

    @Override
    protected void onDestroy() {
        NotificationCenter.a().b("APP_RESTART_NOTIFICATION", this.s);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.u();
        if (this.v()) {
            NotificationCenter.a().a("APP_RESTART_NOTIFICATION", this.s);
            this.w();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!this.e) {
            NotificationCenter.a().a("AUTO_LOGIN_FAILED_NEW", this.u);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.isFinishing()) {
            this.setContentView(new View((Context)this));
        }
        this.y();
        if (!this.e) {
            NotificationCenter.a().b("AUTO_LOGIN_FAILED_NEW", this.u);
        }
    }

    protected static enum ErrorReason {
        a,
        b,
        c,
        d,
        e,
        f;
        

        private ErrorReason() {
        }
    }

    private class HandleErrorRunnable
    implements Runnable {
        final com.smule.android.network.core.NetworkResponse a;
        final ErrorReason b;

        HandleErrorRunnable(ErrorReason errorReason, com.smule.android.network.core.NetworkResponse networkResponse) {
            this.b = errorReason;
            this.a = networkResponse;
        }

        @Override
        public void run() {
            StartupActivity.this.a(this.b, this.a);
        }
    }

}

