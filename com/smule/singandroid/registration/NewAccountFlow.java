package com.smule.singandroid.registration;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PointerIconCompat;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconResponse;
import com.smule.android.network.managers.UserManager.RegistrationResponse;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.task.RegisterTask;
import com.smule.singandroid.task.RegisterTask.RegisterTaskListener;
import com.smule.singandroid.utils.ValidationUtils;

public class NewAccountFlow {
    private static final String f23847a = NewAccountFlow.class.getName();
    private Activity f23848b;
    private BusyDialog f23849c;
    private String f23850d;

    private class Listeners implements UserManager$AccountIconResponseCallback, RegisterTaskListener {
        final /* synthetic */ NewAccountFlow f23846a;

        private Listeners(NewAccountFlow newAccountFlow) {
            this.f23846a = newAccountFlow;
        }

        public void mo6917a(RegistrationResponse registrationResponse) {
            NetworkResponse networkResponse = registrationResponse.a;
            if (networkResponse.a != NetworkResponse$Status.OK) {
                this.f23846a.f23849c.m23577a(2, this.f23846a.f23848b.getResources().getString(C1947R.string.login_cannot_connect_to_smule), true, "OK");
                MagicNetwork.d().showConnectionError();
                return;
            }
            switch (networkResponse.b) {
                case 0:
                    RegistrationContext.d();
                    if (this.f23846a.f23849c != null) {
                        this.f23846a.f23849c.dismiss();
                    }
                    Intent intent = new Intent(this.f23846a.f23848b, NewHandleActivity_.class);
                    intent.putExtra("PARAM_LOGIN_METHOD", RegistrationFlow.EMAIL);
                    intent.putExtra("SHOW_EMAIL_OPT", registrationResponse.showEmailOpt);
                    this.f23846a.f23848b.startActivity(intent);
                    this.f23846a.f23848b.finish();
                    return;
                case 56:
                case 67:
                case PointerIconCompat.TYPE_CELL /*1006*/:
                    if (networkResponse.f == 13) {
                        UserManager.a().a(this.f23846a.f23850d, this);
                        return;
                    } else {
                        this.f23846a.f23849c.m23577a(2, this.f23846a.f23848b.getResources().getString(C1947R.string.settings_email_invalid), true, "OK");
                        return;
                    }
                default:
                    MagicNetwork.a(networkResponse);
                    this.f23846a.f23849c.m23577a(2, this.f23846a.f23848b.getResources().getString(C1947R.string.login_error_with_servers), true, "OK");
                    return;
            }
        }

        public void handleResponse(AccountIconResponse accountIconResponse) {
            if (accountIconResponse.a == null || accountIconResponse.a.a != NetworkResponse$Status.OK) {
                this.f23846a.f23849c.m23577a(2, this.f23846a.f23848b.getResources().getString(C1947R.string.login_cannot_connect_to_smule), true, "OK");
            } else if (!accountIconResponse.a.c()) {
                this.f23846a.f23849c.m23577a(2, this.f23846a.f23848b.getResources().getString(C1947R.string.login_error_with_servers), true, "OK");
                MagicNetwork.a(accountIconResponse.a);
            } else if (accountIconResponse.mAccount.handle != null) {
                if (this.f23846a.f23849c != null) {
                    this.f23846a.f23849c.dismiss();
                    this.f23846a.f23849c = null;
                }
                this.f23846a.f23848b.startActivity(RegistrationEntryActivity.m25165a(this.f23846a.f23848b, accountIconResponse.mAccount.handle, this.f23846a.f23850d, accountIconResponse.mAccount.picUrl));
                this.f23846a.f23848b.finish();
            } else {
                this.f23846a.f23849c.m23577a(2, this.f23846a.f23848b.getResources().getString(C1947R.string.cm_email_taken), true, "OK");
            }
        }
    }

    public NewAccountFlow(Activity activity) {
        this.f23848b = activity;
    }

    public boolean m25132a(String str, String str2) {
        if (this.f23849c != null) {
            this.f23849c.dismiss();
            this.f23849c = null;
        }
        this.f23850d = str;
        RegisterTaskListener listeners = new Listeners();
        this.f23849c = new BusyDialog(this.f23848b, this.f23848b.getResources().getString(C1947R.string.login_checking));
        this.f23849c.show();
        if (ValidationUtils.m26200a(str)) {
            new RegisterTask(this.f23848b, this.f23850d, str2, listeners).execute(new Void[0]);
            return true;
        }
        this.f23849c.m23574a(2, this.f23848b.getString(C1947R.string.login_invalid_email_title), this.f23848b.getString(C1947R.string.login_invalid_email_body), true, this.f23848b.getString(C1947R.string.core_ok));
        return false;
    }
}
