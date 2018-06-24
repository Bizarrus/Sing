package com.smule.singandroid.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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

public class AccountKitLoginTask extends AsyncTask<Void, Void, UserManager$LoginResponse> {
    public static final String f24380a = GoogleLoginTask.class.getName();
    private Activity f24381b;
    private BusyDialog f24382c;
    private boolean f24383d;
    private boolean f24384e;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25598a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25599a((UserManager$LoginResponse) obj);
    }

    public AccountKitLoginTask(Activity activity, boolean z) {
        this(activity, z, true);
    }

    public AccountKitLoginTask(Activity activity, boolean z, boolean z2) {
        this.f24381b = activity;
        this.f24383d = z;
        this.f24384e = z2;
    }

    protected void onPreExecute() {
        this.f24382c = new BusyDialog(this.f24381b, this.f24381b.getString(C1947R.string.connect_to_snp));
        this.f24382c.setCancelable(false);
        this.f24382c.m23580a(false);
    }

    protected UserManager$LoginResponse m25598a(Void... voidArr) {
        return UserManager.a().b(this.f24383d);
    }

    protected void m25599a(final UserManager$LoginResponse userManager$LoginResponse) {
        int i;
        if (userManager$LoginResponse != null && userManager$LoginResponse.a.a == NetworkResponse$Status.OK) {
            switch (userManager$LoginResponse.a.b) {
                case 0:
                    if (userManager$LoginResponse.f17333p.booleanValue()) {
                        RegistrationContext.d(true);
                    }
                    if (this.f24382c != null) {
                        this.f24382c.dismiss();
                        this.f24382c = null;
                    }
                    boolean booleanValue = userManager$LoginResponse.f17338u.booleanValue();
                    if (!this.f24383d) {
                        RegistrationContext.c(userManager$LoginResponse.f17338u.booleanValue());
                    }
                    if (!userManager$LoginResponse.f17338u.booleanValue()) {
                        if (!this.f24383d) {
                            booleanValue = !booleanValue;
                            new Runnable(this) {
                                final /* synthetic */ AccountKitLoginTask f24379b;

                                public void run() {
                                    RegistrationContext.a(this.f24379b.f24381b, booleanValue, RegistrationFlow.PHONE);
                                }
                            }.run();
                        }
                        if (!this.f24384e) {
                            i = C1947R.string.login_cannot_connect_to_smule;
                            break;
                        }
                        SubscriptionsRestoreHelper.m18453a(Boolean.valueOf(true), this.f24381b);
                        SubscriptionsRestoreHelper.m18452a().m18457a(this.f24381b, true);
                        i = C1947R.string.login_cannot_connect_to_smule;
                        break;
                    }
                    new Runnable(this) {
                        final /* synthetic */ AccountKitLoginTask f24377b;

                        public void run() {
                            Intent intent = new Intent(this.f24377b.f24381b, NewHandleActivity_.class);
                            intent.putExtra("param_handle", userManager$LoginResponse.f17325h);
                            intent.putExtra("param_handle_prefill", userManager$LoginResponse.f17339v);
                            intent.putExtra("PARAM_LOGIN_METHOD", RegistrationFlow.PHONE);
                            intent.putExtra("SHOW_EMAIL_OPT", userManager$LoginResponse.f17336s);
                            this.f24377b.f24381b.startActivity(intent);
                            this.f24377b.f24381b.finish();
                        }
                    }.run();
                    i = C1947R.string.login_cannot_connect_to_smule;
                    break;
                case 1032:
                    i = C1947R.string.account_kit_token_failed;
                    break;
                default:
                    i = C1947R.string.google_generic_profile_error;
                    MagicNetwork.a(userManager$LoginResponse.a);
                    break;
            }
        }
        i = C1947R.string.login_cannot_connect_to_smule;
        if (this.f24382c != null) {
            this.f24382c.m23576a(2, this.f24381b.getString(i), true);
        }
    }
}
