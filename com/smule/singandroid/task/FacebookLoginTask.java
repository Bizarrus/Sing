package com.smule.singandroid.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PointerIconCompat;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.RewardsManager;
import com.smule.android.network.managers.RewardsManager.ClaimRewardListener;
import com.smule.android.network.managers.UserManager$LoginResponse;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.registration.NewHandleActivity_;
import com.smule.singandroid.registration.RegistrationContext;

public class FacebookLoginTask extends AsyncTask<Void, Void, UserManager$LoginResponse> {
    private static final String f24387a = FacebookLoginTask.class.getName();
    private Activity f24388b;
    private BusyDialog f24389c;
    private Boolean f24390d;
    private String f24391e;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25609a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25610a((UserManager$LoginResponse) obj);
    }

    public FacebookLoginTask(Activity activity, Boolean bool) {
        this.f24388b = activity;
        this.f24390d = bool;
    }

    protected void onPreExecute() {
        this.f24389c = new BusyDialog(this.f24388b, this.f24388b.getString(C1947R.string.facebook_connect_to_snp_facebook));
        this.f24389c.show();
        this.f24389c.setCancelable(false);
    }

    protected UserManager$LoginResponse m25609a(Void... voidArr) {
        return MagicFacebook.a().b(this.f24391e, this.f24390d.booleanValue());
    }

    protected void m25610a(final UserManager$LoginResponse userManager$LoginResponse) {
        int i = C1947R.string.login_cannot_connect_to_smule;
        if (userManager$LoginResponse != null && userManager$LoginResponse.a.a == NetworkResponse$Status.OK) {
            if (userManager$LoginResponse.a.b != 0) {
                switch (userManager$LoginResponse.a.b) {
                    case 0:
                        Log.e(f24387a, "Somehow got to the SUCCESS case in the onPostExecute switch");
                        break;
                    case 67:
                        i = C1947R.string.cm_email_taken;
                        break;
                    case PointerIconCompat.TYPE_CELL /*1006*/:
                        i = UserUpdateTask.m25692b(userManager$LoginResponse.a);
                        break;
                    case PointerIconCompat.TYPE_VERTICAL_TEXT /*1009*/:
                        i = C1947R.string.facebook_failed_to_connect_to_snp_facebook;
                        break;
                    default:
                        i = C1947R.string.facebook_generic_profile_error;
                        MagicNetwork.a(userManager$LoginResponse.a);
                        break;
                }
            }
            RegistrationContext.d(userManager$LoginResponse.f17333p.booleanValue());
            RewardsManager.m18319a().m18322a(new ClaimRewardListener(this) {
                final /* synthetic */ FacebookLoginTask f24386b;

                public void mo6412a() {
                    Log.b(FacebookLoginTask.f24387a, "rewardSuccessfullyClaimed");
                }

                public void mo6413b() {
                    Log.b(FacebookLoginTask.f24387a, "rewardAlreadyClaimed");
                }

                public void mo6414c() {
                    Log.b(FacebookLoginTask.f24387a, "errorClaimingReward");
                }

                public void mo6415d() {
                    Log.b(FacebookLoginTask.f24387a, "claimRewardCompleted");
                    this.f24386b.f24389c.dismiss();
                    this.f24386b.f24389c = null;
                    if (!this.f24386b.f24390d.booleanValue()) {
                        RegistrationContext.a(userManager$LoginResponse.f17338u.booleanValue());
                    }
                    if (userManager$LoginResponse.f17338u.booleanValue()) {
                        Intent intent = new Intent(this.f24386b.f24388b, NewHandleActivity_.class);
                        intent.putExtra("param_handle", userManager$LoginResponse.f17325h);
                        intent.putExtra("param_handle_prefill", userManager$LoginResponse.f17339v);
                        intent.putExtra("PARAM_LOGIN_METHOD", RegistrationFlow.FACEBOOK);
                        intent.putExtra("SHOW_EMAIL_OPT", userManager$LoginResponse.f17336s);
                        this.f24386b.f24388b.startActivity(intent);
                        this.f24386b.f24388b.finish();
                    } else if (!this.f24386b.f24390d.booleanValue()) {
                        RegistrationContext.a(this.f24386b.f24388b, true, RegistrationFlow.FACEBOOK);
                    }
                }
            });
            return;
        }
        if (this.f24389c != null) {
            this.f24389c.m23576a(2, this.f24388b.getString(i), true);
        }
    }
}
