package com.smule.singandroid.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PointerIconCompat;
import com.smule.android.google.plus.MagicGooglePlus;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.SubscriptionsRestoreHelper;
import com.smule.android.network.managers.UserManager$LoginResponse;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.registration.NewHandleActivity_;
import com.smule.singandroid.registration.RegistrationContext;

public class GooglePlusLoginTask extends AsyncTask<Void, Void, UserManager$LoginResponse> {
    private static final String f24411a = GooglePlusLoginTask.class.getName();
    private Activity f24412b;
    private BusyDialog f24413c;
    private MagicGooglePlus f24414d;
    private boolean f24415e;
    private boolean f24416f;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25618a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25619a((UserManager$LoginResponse) obj);
    }

    protected void onPreExecute() {
        this.f24413c = new BusyDialog(this.f24412b, this.f24412b.getString(C1947R.string.connect_to_snp_google));
        this.f24413c.setCancelable(false);
        this.f24413c.m23580a(false);
    }

    protected UserManager$LoginResponse m25618a(Void... voidArr) {
        return this.f24414d.a(this.f24415e);
    }

    protected void m25619a(final UserManager$LoginResponse userManager$LoginResponse) {
        int i = C1947R.string.login_cannot_connect_to_smule;
        if (userManager$LoginResponse != null && userManager$LoginResponse.a.a == NetworkResponse$Status.OK) {
            switch (userManager$LoginResponse.a.b) {
                case 0:
                    RegistrationContext.d(userManager$LoginResponse.f17333p.booleanValue());
                    if (this.f24413c != null) {
                        this.f24413c.dismiss();
                        this.f24413c = null;
                    }
                    final boolean booleanValue = userManager$LoginResponse.f17338u.booleanValue();
                    if (!this.f24415e) {
                        RegistrationContext.b(userManager$LoginResponse.f17338u.booleanValue());
                    }
                    if (!userManager$LoginResponse.f17338u.booleanValue()) {
                        if (!this.f24415e) {
                            new Runnable(this) {
                                final /* synthetic */ GooglePlusLoginTask f24410b;

                                public void run() {
                                    RegistrationContext.a(this.f24410b.f24412b, booleanValue, RegistrationFlow.GPLUS);
                                }
                            }.run();
                        }
                        if (this.f24416f) {
                            SubscriptionsRestoreHelper.m18453a(Boolean.valueOf(true), this.f24412b);
                            SubscriptionsRestoreHelper.m18452a().m18457a(this.f24412b, true);
                            break;
                        }
                    }
                    new Runnable(this) {
                        final /* synthetic */ GooglePlusLoginTask f24408b;

                        public void run() {
                            Intent intent = new Intent(this.f24408b.f24412b, NewHandleActivity_.class);
                            intent.putExtra("param_handle", userManager$LoginResponse.f17325h);
                            intent.putExtra("param_handle_prefill", userManager$LoginResponse.f17339v);
                            intent.putExtra("PARAM_LOGIN_METHOD", RegistrationFlow.GPLUS);
                            this.f24408b.f24412b.startActivity(intent);
                            this.f24408b.f24412b.finish();
                        }
                    }.run();
                    break;
                    break;
                case PointerIconCompat.TYPE_VERTICAL_TEXT /*1009*/:
                    i = C1947R.string.failed_to_connect_to_snp_facebook;
                    break;
                default:
                    i = C1947R.string.facebook_generic_profile_error;
                    MagicNetwork.a(userManager$LoginResponse.a);
                    break;
            }
        }
        if (this.f24413c != null) {
            this.f24413c.m23576a(2, this.f24412b.getString(i), true);
        }
    }
}
