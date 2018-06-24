package com.smule.singandroid.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PointerIconCompat;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.SubscriptionsRestoreHelper;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$LoginResponse;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.registration.NewHandleActivity_;
import com.smule.singandroid.registration.RegistrationContext;

public class GoogleLoginTask extends AsyncTask<Void, Void, UserManager$LoginResponse> {
    public static final String f24396a = GoogleLoginTask.class.getName();
    private Activity f24397b;
    private BusyDialog f24398c;
    private boolean f24399d;
    private boolean f24400e;
    private GoogleSignInAccount f24401f;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25612a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25613a((UserManager$LoginResponse) obj);
    }

    public GoogleLoginTask(Activity activity, GoogleSignInAccount googleSignInAccount, boolean z, boolean z2) {
        this.f24397b = activity;
        this.f24401f = googleSignInAccount;
        this.f24399d = z;
        this.f24400e = z2;
    }

    protected void onPreExecute() {
        this.f24398c = new BusyDialog(this.f24397b, this.f24397b.getString(C1947R.string.connect_to_snp_google));
        this.f24398c.setCancelable(false);
        this.f24398c.m23580a(false);
    }

    protected UserManager$LoginResponse m25612a(Void... voidArr) {
        return UserManager.a().a(this.f24401f.getIdToken(), UserManager.I(), this.f24399d);
    }

    protected void m25613a(final UserManager$LoginResponse userManager$LoginResponse) {
        int i = C1947R.string.login_cannot_connect_to_smule;
        if (userManager$LoginResponse != null && userManager$LoginResponse.a.a == NetworkResponse$Status.OK) {
            switch (userManager$LoginResponse.a.b) {
                case 0:
                    RegistrationContext.d(userManager$LoginResponse.f17333p.booleanValue());
                    if (this.f24398c != null) {
                        this.f24398c.dismiss();
                        this.f24398c = null;
                    }
                    final boolean booleanValue = userManager$LoginResponse.f17338u.booleanValue();
                    if (!this.f24399d) {
                        RegistrationContext.b(userManager$LoginResponse.f17338u.booleanValue());
                    }
                    if (!userManager$LoginResponse.f17338u.booleanValue()) {
                        if (!this.f24399d) {
                            new Runnable(this) {
                                final /* synthetic */ GoogleLoginTask f24395b;

                                public void run() {
                                    RegistrationContext.a(this.f24395b.f24397b, booleanValue, RegistrationFlow.GOOGLE);
                                }
                            }.run();
                        }
                        if (this.f24400e) {
                            SubscriptionsRestoreHelper.m18453a(Boolean.valueOf(true), this.f24397b);
                            SubscriptionsRestoreHelper.m18452a().m18457a(this.f24397b, true);
                            break;
                        }
                    }
                    new Runnable(this) {
                        final /* synthetic */ GoogleLoginTask f24393b;

                        public void run() {
                            Intent intent = new Intent(this.f24393b.f24397b, NewHandleActivity_.class);
                            intent.putExtra("param_handle", userManager$LoginResponse.f17325h);
                            intent.putExtra("param_handle_prefill", userManager$LoginResponse.f17339v);
                            intent.putExtra("PARAM_LOGIN_METHOD", RegistrationFlow.GOOGLE);
                            intent.putExtra("SHOW_EMAIL_OPT", userManager$LoginResponse.f17336s);
                            this.f24393b.f24397b.startActivity(intent);
                            this.f24393b.f24397b.finish();
                        }
                    }.run();
                    break;
                    break;
                case PointerIconCompat.TYPE_VERTICAL_TEXT /*1009*/:
                    i = C1947R.string.facebook_failed_to_connect_to_snp_facebook;
                    break;
                default:
                    i = C1947R.string.google_generic_profile_error;
                    MagicNetwork.a(userManager$LoginResponse.a);
                    break;
            }
        }
        if (this.f24398c != null) {
            this.f24398c.m23576a(2, this.f24397b.getString(i), true);
        }
    }
}
