package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.widget.ProgressBar;
import com.crittercism.app.Crittercism;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.AppLaunchErrorType;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$ConnectedPerformancesResponseCallback;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.response.GetConnectedPerformancesResponse;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.SimpleBarrier;
import com.smule.singandroid.chat.ChatNotification;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.dialogs.WhatsNewDialog;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EActivity
public class StartupActivity extends BaseActivity {
    private static final String f20383h = StartupActivity.class.getName();
    private static boolean f20384l;
    @ViewById
    protected ProgressBar f20385g;
    private boolean f20386i = false;
    private Uri f20387j = null;
    private Handler f20388k;
    private SimpleBarrier f20389m;
    private TextAlertDialog f20390n;
    private WhatsNewDialog f20391o;
    private ErrorReason f20392p;
    private boolean f20393q = false;
    private long f20394r;
    private boolean f20395s = false;
    private Observer f20396t = new C41989(this);
    private HandleErrorRunnable f20397u = new HandleErrorRunnable(this, ErrorReason.TIMEOUT, null);

    class C41881 implements Runnable {
        final /* synthetic */ StartupActivity f20362a;

        C41881(StartupActivity startupActivity) {
            this.f20362a = startupActivity;
        }

        public void run() {
            this.f20362a.mo6670b();
        }
    }

    class C41892 implements Runnable {
        final /* synthetic */ StartupActivity f20363a;

        C41892(StartupActivity startupActivity) {
            this.f20363a = startupActivity;
        }

        public void run() {
            Log.b(StartupActivity.f20383h, "splash screen done");
            this.f20363a.f20389m.m19034a();
            if (this.f20363a.f()) {
                if (this.f20363a.f20391o != null) {
                    this.f20363a.f20391o.show();
                    SingAnalytics.m26152p();
                }
                this.f20363a.f20385g.setVisibility(0);
            }
        }
    }

    class C41903 implements Runnable {
        final /* synthetic */ StartupActivity f20364a;

        C41903(StartupActivity startupActivity) {
            this.f20364a = startupActivity;
        }

        public void run() {
            Log.b(StartupActivity.f20383h, "what's new done");
            this.f20364a.f20389m.m19034a();
            this.f20364a.f20388k.postDelayed(this.f20364a.f20397u, (long) this.f20364a.getResources().getInteger(C1947R.integer.startup_network_timeout));
            this.f20364a.f20391o = null;
        }
    }

    class C41914 implements OnCancelListener {
        final /* synthetic */ StartupActivity f20365a;

        C41914(StartupActivity startupActivity) {
            this.f20365a = startupActivity;
        }

        public void onCancel(DialogInterface dialogInterface) {
            Log.b(StartupActivity.f20383h, "what's new cancelled");
            this.f20365a.f20389m.m19034a();
            this.f20365a.f20388k.postDelayed(this.f20365a.f20397u, (long) this.f20365a.getResources().getInteger(C1947R.integer.startup_network_timeout));
            this.f20365a.f20391o = null;
        }
    }

    class C41925 implements Runnable {
        final /* synthetic */ StartupActivity f20366a;

        C41925(StartupActivity startupActivity) {
            this.f20366a = startupActivity;
        }

        public void run() {
            this.f20366a.mo6669a(ErrorReason.NETWORK_UNAVAILABLE, null);
        }
    }

    class C41946 implements Runnable {
        final /* synthetic */ StartupActivity f20368a;

        class C41931 implements PerformanceManager$ConnectedPerformancesResponseCallback {
            final /* synthetic */ C41946 f20367a;

            C41931(C41946 c41946) {
                this.f20367a = c41946;
            }

            public void handleResponse(GetConnectedPerformancesResponse getConnectedPerformancesResponse) {
            }
        }

        C41946(StartupActivity startupActivity) {
            this.f20368a = startupActivity;
        }

        public void run() {
            if (RegistrationContext.e()) {
                PerformanceManager.a().a(new C41931(this));
            }
        }
    }

    class C41957 implements Runnable {
        final /* synthetic */ StartupActivity f20369a;

        C41957(StartupActivity startupActivity) {
            this.f20369a = startupActivity;
        }

        public void run() {
            this.f20369a.runOnUiThread(new HandleErrorRunnable(this.f20369a, ErrorReason.DEVICE_LOOKUP_FAILED, null));
        }
    }

    class C41978 implements Runnable {
        final /* synthetic */ StartupActivity f20371a;

        class C41961 implements Runnable {
            final /* synthetic */ C41978 f20370a;

            C41961(C41978 c41978) {
                this.f20370a = c41978;
            }

            public void run() {
                if (!this.f20370a.f20371a.f20393q || AppSettingsManager.a().d()) {
                    this.f20370a.f20371a.mo6668a();
                } else {
                    this.f20370a.f20371a.mo6669a(ErrorReason.SETTINGS_FAILED, null);
                }
            }
        }

        C41978(StartupActivity startupActivity) {
            this.f20371a = startupActivity;
        }

        public void run() {
            SingApplication.d().a("StartupActivity.OP_STARTUP_WAIT_FOR_DEPENDENCIES");
            this.f20371a.runOnUiThread(new C41961(this));
        }
    }

    class C41989 implements Observer {
        final /* synthetic */ StartupActivity f20372a;

        C41989(StartupActivity startupActivity) {
            this.f20372a = startupActivity;
        }

        public void update(Observable observable, Object obj) {
            if (obj instanceof NetworkResponse) {
                this.f20372a.runOnUiThread(new HandleErrorRunnable(this.f20372a, ErrorReason.AUTO_LOGIN_FAILED_NEW, (NetworkResponse) obj));
            } else {
                this.f20372a.runOnUiThread(new HandleErrorRunnable(this.f20372a, ErrorReason.AUTO_LOGIN_FAILED_NEW, null));
            }
        }
    }

    protected enum ErrorReason {
        LOGIN_FAILED,
        AUTO_LOGIN_FAILED_NEW,
        TIMEOUT,
        DEVICE_LOOKUP_FAILED,
        NETWORK_UNAVAILABLE,
        SETTINGS_FAILED
    }

    private class HandleErrorRunnable implements Runnable {
        final NetworkResponse f20380a;
        final ErrorReason f20381b;
        final /* synthetic */ StartupActivity f20382c;

        HandleErrorRunnable(StartupActivity startupActivity, ErrorReason errorReason, NetworkResponse networkResponse) {
            this.f20382c = startupActivity;
            this.f20381b = errorReason;
            this.f20380a = networkResponse;
        }

        public void run() {
            this.f20382c.mo6669a(this.f20381b, this.f20380a);
        }
    }

    protected void onCreate(Bundle bundle) {
        this.f20393q = MagicNetwork.a().s();
        this.e = this.f20393q;
        super.onCreate(bundle);
        Crittercism.sendAppLoadData();
        this.f20388k = new Handler();
    }

    protected void onStart() {
        super.onStart();
        if (!this.e) {
            NotificationCenter.m19011a().m19014a("AUTO_LOGIN_FAILED_NEW", this.f20396t);
        }
    }

    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            setContentView(new View(this));
        }
        m21917v();
        if (!this.e) {
            NotificationCenter.m19011a().m19016b("AUTO_LOGIN_FAILED_NEW", this.f20396t);
        }
    }

    protected void onResume() {
        super.onResume();
        m21913r();
        if (m21914s()) {
            m21915t();
        }
    }

    private void m21913r() {
        Log.c(f20383h, "processIntentData: " + getIntent());
        String stringExtra = getIntent().getStringExtra("PARAMS");
        this.f20387j = getIntent().getData();
        if (stringExtra != null) {
            try {
                Map map = (Map) JsonUtils.m18984a().readValue(stringExtra, Map.class);
                String str = (String) map.get("y");
                String str2 = (String) map.get("z");
                if ((str == null || str2 == null) && !"v".equals(str)) {
                    Log.e(f20383h, "Missing push type or/and id type=" + str + " id=" + str2);
                } else if (!ChatNotification.m22635a(str, str2, this.f20387j, getIntent())) {
                    Analytics.m17872a(str, str2, this.f20387j.toString());
                }
            } catch (Throwable e) {
                Log.d(f20383h, "Failed to parse push notification params " + stringExtra, e);
            }
        }
    }

    private boolean m21914s() {
        this.f20386i = UserManager.a().z();
        Log.c(f20383h, "setupBarrier: " + this.f20386i + " / " + UserManager.a().F() + " / " + MagicFacebook.a().b());
        if (this.f20386i && UserManager.a().F() && MagicFacebook.a().b() == null) {
            UserManager.a().B();
            this.f20386i = false;
            Analytics.m17890b("facebook", "client_error", "forced logout due to null access token", null);
        }
        if (this.f20386i || !o()) {
            this.f20389m = new SimpleBarrier(1, new C41881(this));
            if (!f20384l) {
                f20384l = true;
                this.f20389m.m19037d();
                this.f20388k.postDelayed(new C41892(this), 1000);
                if (SingApplication.g().q() && this.f20386i) {
                    this.f20389m.m19037d();
                    if (this.f20391o == null) {
                        this.f20391o = new WhatsNewDialog(this);
                        this.f20391o.m23725a(new C41903(this));
                        this.f20391o.setOnCancelListener(new C41914(this));
                    }
                }
            }
            return true;
        }
        Log.c(f20383h, "setupBarrier: BaseActivity has already started login process; retry intent later");
        startActivity(getIntent());
        return false;
    }

    private void m21915t() {
        if (NetworkUtils.m18113a((Context) this)) {
            if (this.f20390n != null) {
                this.f20390n.dismiss();
            }
            if (this.f20386i) {
                m21916u();
                return;
            } else {
                NavigationUtils.m25908a((Activity) this, new C41946(this), new C41957(this));
                return;
            }
        }
        this.f20388k.postDelayed(new C41925(this), 0);
    }

    private void m21916u() {
        Object arrayList = new ArrayList();
        if (!this.f20393q) {
            arrayList.addAll(Arrays.asList(new String[]{"InitAppTask.OP_LOAD_SETTINGS", "InitAppTask.OP_TRIM_CACHE"}));
        } else if (!AppSettingsManager.a().d()) {
            Log.b(f20383h, "Adding settings to dependencies list");
            arrayList.add("InitAppTask.OP_LOAD_SETTINGS");
        }
        if (this.f20387j != null) {
            Log.b(f20383h, "Adding store load to dependencies list");
            arrayList.add("StoreManager.loadStore");
        }
        SingApplication.d().a("StartupActivity.OP_STARTUP_WAIT_FOR_DEPENDENCIES", arrayList, new C41978(this));
        if (this.f20391o == null) {
            this.f20388k.postDelayed(this.f20397u, (long) getResources().getInteger(C1947R.integer.startup_network_timeout));
        }
    }

    private void m21917v() {
        SingApplication.d().a("StartupActivity.OP_STARTUP_WAIT_FOR_DEPENDENCIES");
        this.f20388k.removeCallbacks(this.f20397u);
    }

    @SupposeUiThread
    protected void mo6668a() {
        Log.b(f20383h, "onOperationDependenciesComplete");
        if (!NetworkUtils.m18113a((Context) this)) {
            mo6669a(ErrorReason.NETWORK_UNAVAILABLE, null);
        } else if (!this.f20386i) {
            mo6669a(ErrorReason.LOGIN_FAILED, null);
        } else if (UserManager.a().A()) {
            mo6669a(ErrorReason.LOGIN_FAILED, null);
        } else {
            if (!this.f20393q) {
                Object obj = (!MagicNetwork.a().l() || MagicNetwork.a().i()) ? null : 1;
                if (obj == null) {
                    mo6669a(ErrorReason.LOGIN_FAILED, null);
                    return;
                }
            }
            this.f20389m.m19034a();
        }
    }

    @SupposeUiThread
    protected void mo6670b() {
        Log.c(f20383h, "onBarrierDone");
        if (this.f20393q && m()) {
            Log.b(f20383h, "onBarrierDone:BaseActivity showing dialog");
            return;
        }
        Intent intent;
        if (RegistrationContext.f() && !o()) {
            intent = new Intent(this, OnboardingActivity_.class);
            String g = RegistrationContext.g();
            if (RegistrationContext.g() != null) {
                intent.putExtra("ONBOARDING_TOPICS", g);
            }
            intent.putExtra("BEGIN_ONBOARDING", true);
        } else if (this.f20387j != null) {
            intent = new Intent(this, MasterActivity_.class);
            intent.setData(this.f20387j);
            intent.putExtra("BEGIN_ONBOARDING", false);
        } else {
            intent = new Intent(this, MasterActivity_.class);
        }
        if (this.f20395s) {
            Log.c(f20383h, "MasterActivity already started");
        } else {
            this.f20395s = true;
            startActivity(intent);
            if (o() && !RegistrationEntryActivity.m25171a()) {
                return;
            }
        }
        finish();
    }

    @SupposeUiThread
    protected void mo6669a(@NonNull ErrorReason errorReason, NetworkResponse networkResponse) {
        int i = C1947R.string.login_failed;
        boolean z = false;
        int i2 = -1;
        NetworkResponse$Status networkResponse$Status = NetworkResponse$Status.UNINITIALIZED;
        this.f20392p = errorReason;
        Log.b(f20383h, "handleError:" + this.f20392p);
        if (f()) {
            switch (this.f20392p) {
                case LOGIN_FAILED:
                case AUTO_LOGIN_FAILED_NEW:
                    if (!this.f20386i) {
                        Log.b(f20383h, "handleError:ignore errors during registration");
                        return;
                    }
                    break;
            }
            if (networkResponse != null) {
                i2 = networkResponse.b;
                networkResponse$Status = networkResponse.a;
            }
            m21917v();
            this.f20385g.setVisibility(8);
            if (i2 == PointerIconCompat.TYPE_VERTICAL_TEXT || i2 == 1032) {
                UserManager.a().B();
                this.f20386i = false;
                m21915t();
                return;
            }
            if (this.f20390n == null) {
                if (i2 == 2000) {
                    Log.b(f20383h, "CODE_UPGRADE_REQUIRED handled in BaseActivity");
                    return;
                } else if (networkResponse$Status == NetworkResponse$Status.SERVER_MAINTENANCE) {
                    Log.b(f20383h, "SERVER_MAINTENANCE handled in BaseActivity");
                    return;
                } else {
                    int i3;
                    if (i2 == 69 || i2 == 72) {
                        i3 = C1947R.string.autologin_failed;
                        UserManager.a().B();
                        z = true;
                    } else if (i2 == 1002) {
                        i3 = C1947R.string.login_account_frozen;
                    } else {
                        i = C1947R.string.favorite_error_title;
                        i3 = C1947R.string.login_cannot_connect_to_smule;
                        z = true;
                    }
                    this.f20390n = new TextAlertDialog((Context) this, i, i3, z, z);
                    if (z) {
                        this.f20390n.m19800a((int) C1947R.string.invite_connect_fail_retry, (int) C1947R.string.invite_connect_fail_cancel);
                    }
                    this.f20390n.m19812c(true);
                    this.f20390n.m19803a(new CustomAlertDialogListener(this) {
                        final /* synthetic */ StartupActivity f20360a;

                        class C41871 implements Runnable {
                            final /* synthetic */ AnonymousClass10 f20359a;

                            C41871(AnonymousClass10 anonymousClass10) {
                                this.f20359a = anonymousClass10;
                            }

                            public void run() {
                                UserManager.a().E();
                                MagicNetwork.a().a(true);
                            }
                        }

                        {
                            this.f20360a = r1;
                        }

                        public void mo6385a(CustomAlertDialog customAlertDialog) {
                            this.f20360a.f20386i = UserManager.a().z();
                            if (!(this.f20360a.f20393q || this.f20360a.f20386i)) {
                                int i = (!MagicNetwork.a().l() || MagicNetwork.a().i()) ? 0 : 1;
                                if (i == 0 && !MagicNetwork.a().h()) {
                                    MagicNetwork.a(new C41871(this));
                                }
                            }
                            if (this.f20360a.f20392p == ErrorReason.SETTINGS_FAILED) {
                                SingApplication.d().b("InitAppTask.OP_LOAD_SETTINGS");
                            }
                            this.f20360a.m21915t();
                            this.f20360a.f20385g.setVisibility(0);
                            SingAnalytics.m26153q();
                            SingAnalytics.m26065a(System.currentTimeMillis() - this.f20360a.f20394r);
                        }

                        public void mo6386b(CustomAlertDialog customAlertDialog) {
                            this.f20360a.finish();
                            System.exit(0);
                        }
                    });
                }
            }
            if (!this.f20390n.isShowing()) {
                this.f20390n.show();
                this.f20394r = System.currentTimeMillis();
                SingAnalytics.m26068a(NetworkUtils.m18113a((Context) this) ? AppLaunchErrorType.TIMEOUT : AppLaunchErrorType.UNREACHABLE);
                return;
            }
            return;
        }
        Log.b(f20383h, "handleError:not running, ignoring");
    }

    protected void m21921l() {
        finish();
        System.exit(0);
    }
}
