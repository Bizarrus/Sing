package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.RegistrationFlow;
import com.smule.android.logging.Log;
import com.smule.android.logging.TrackedActivity;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconResponseCallback;
import com.smule.android.network.managers.UserManager$LoginResponse;
import com.smule.android.network.managers.UserManager.AccountIconResponse;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.task.LoginTask;
import com.smule.singandroid.task.LoginTask.LoginTaskListener;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.ValidationUtils;

public class LoginActivity extends BaseActivity implements TrackedActivity, UserManager$AccountIconResponseCallback, LoginTaskListener {
    private static final String f23837g = LoginActivity.class.getName();
    private EditText f23838h;
    private EditText f23839i;
    private BusyDialog f23840j;
    private boolean f23841k = false;
    private Dialog f23842l;
    private OnClickListener f23843m;
    private OnClickListener f23844n;
    private boolean f23845o = false;

    class C48211 implements OnClickListener {
        final /* synthetic */ LoginActivity f23831a;

        C48211(LoginActivity loginActivity) {
            this.f23831a = loginActivity;
        }

        public void onClick(View view) {
            this.f23831a.m25121r();
        }
    }

    class C48222 implements TextWatcher {
        final /* synthetic */ LoginActivity f23832a;

        C48222(LoginActivity loginActivity) {
            this.f23832a = loginActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    class C48233 implements OnClickListener {
        final /* synthetic */ LoginActivity f23833a;

        C48233(LoginActivity loginActivity) {
            this.f23833a = loginActivity;
        }

        public void onClick(View view) {
            BusyDialog busyDialog;
            if (!ValidationUtils.m26200a(this.f23833a.f23838h.getText().toString())) {
                busyDialog = new BusyDialog(this.f23833a, this.f23833a.getResources().getString(C1947R.string.settings_invalid_password));
                busyDialog.m23574a(2, this.f23833a.getString(C1947R.string.login_invalid_email_title), this.f23833a.getString(C1947R.string.login_invalid_email_body), true, this.f23833a.getString(C1947R.string.core_ok));
                busyDialog.show();
            } else if (this.f23833a.f23839i.getText().length() < 6 || this.f23833a.f23839i.getText().length() > 16) {
                busyDialog = new BusyDialog(this.f23833a, this.f23833a.getResources().getString(C1947R.string.settings_invalid_password));
                busyDialog.m23574a(2, this.f23833a.getString(C1947R.string.settings_invalid_password), this.f23833a.getString(C1947R.string.settings_password_length_invalid), true, this.f23833a.getString(C1947R.string.core_ok));
                busyDialog.show();
            } else {
                this.f23833a.m25122s();
            }
        }
    }

    class C48244 implements OnClickListener {
        final /* synthetic */ LoginActivity f23834a;

        C48244(LoginActivity loginActivity) {
            this.f23834a = loginActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.f23834a, ForgotPasswordActivity.class);
            String obj = this.f23834a.f23838h.getText().toString();
            if (!obj.isEmpty()) {
                intent.putExtra("EMAIL", obj);
            }
            this.f23834a.startActivity(intent);
            this.f23834a.finish();
        }
    }

    class C48255 implements OnClickListener {
        final /* synthetic */ LoginActivity f23835a;

        C48255(LoginActivity loginActivity) {
            this.f23835a = loginActivity;
        }

        public void onClick(View view) {
            this.f23835a.f23842l.dismiss();
            this.f23835a.f23842l = null;
        }
    }

    class C48266 implements OnClickListener {
        final /* synthetic */ LoginActivity f23836a;

        C48266(LoginActivity loginActivity) {
            this.f23836a = loginActivity;
        }

        public void onClick(View view) {
            if (this.f23836a.f23842l != null && this.f23836a.f23842l.isShowing()) {
                this.f23836a.f23842l.dismiss();
                this.f23836a.f23842l = null;
            }
            new NewAccountFlow(this.f23836a).m25132a(this.f23836a.f23838h.getText().toString(), this.f23836a.f23839i.getText().toString());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1947R.layout.login_activity);
        findViewById(C1947R.id.backbutton).setOnClickListener(new C48211(this));
        this.f23838h = (EditText) findViewById(C1947R.id.email_editText);
        WeakListener.m19083a(this.f23838h, new C48222(this));
        this.f23838h.requestFocus();
        MiscUtils.m25890a((Activity) this, this.f23838h);
        this.f23839i = (EditText) findViewById(C1947R.id.password_editText);
        Button button = (Button) findViewById(C1947R.id.login_button);
        button.setOnClickListener(new C48233(this));
        ((TextView) findViewById(C1947R.id.forgot_pass_button)).setOnClickListener(new C48244(this));
        CharSequence stringExtra = getIntent().getStringExtra("param_email");
        if (!(stringExtra == null || TextUtils.isEmpty(stringExtra))) {
            this.f23838h.setText(stringExtra);
            this.f23839i.requestFocus();
            MiscUtils.m25890a((Activity) this, this.f23839i);
        }
        this.f23843m = new C48255(this);
        this.f23844n = new C48266(this);
        m25120q();
        NavigationUtils.m25910a(this.f23838h, this.f23839i, button);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.b(f23837g, "onNewIntent : " + intent.getData());
        m25120q();
    }

    private void m25120q() {
        Uri data = getIntent().getData();
        if (data != null) {
            CharSequence queryParameter = data.getQueryParameter("email");
            CharSequence queryParameter2 = data.getQueryParameter("password");
            if (queryParameter != null) {
                this.f23838h.setText(queryParameter);
            }
            if (queryParameter2 != null) {
                this.f23839i.setText(queryParameter2);
            }
            if (queryParameter != null && queryParameter2 != null) {
                this.f23841k = false;
                if (this.f23845o) {
                    m25122s();
                } else {
                    this.f23841k = true;
                }
            }
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.f23840j != null) {
            this.f23840j.dismiss();
            this.f23840j = null;
        }
    }

    public void onBackPressed() {
        m25121r();
        if (this.f23842l != null) {
            this.f23842l.dismiss();
            this.f23842l = null;
        }
    }

    private void m25121r() {
        startActivity(RegistrationEntryActivity.m25166a(this, false, false, null, null, null));
        finish();
    }

    private void m25122s() {
        this.f23840j = new BusyDialog((Activity) this, getResources().getString(C1947R.string.login_));
        this.f23840j.show();
        new LoginTask(this, this, this.f23838h.getText().toString(), this.f23839i.getText().toString()).execute(new Void[0]);
    }

    public boolean mo6612a() {
        return true;
    }

    public String mo6613b() {
        return "RegisterExistingAccount";
    }

    protected void onResume() {
        super.onResume();
        this.f23845o = true;
        Analytics.m17905l();
        if (this.f23841k) {
            m25122s();
            this.f23841k = false;
        }
    }

    protected void onPause() {
        super.onPause();
        this.f23845o = false;
    }

    public void mo6916a(UserManager$LoginResponse userManager$LoginResponse) {
        if (userManager$LoginResponse != null && userManager$LoginResponse.a != null && userManager$LoginResponse.a.a == NetworkResponse$Status.OK) {
            switch (userManager$LoginResponse.a.b) {
                case 0:
                    if (this.f23840j != null) {
                        this.f23840j.dismiss();
                    }
                    RegistrationContext.d(userManager$LoginResponse.f17333p.booleanValue());
                    RegistrationContext.c();
                    if (userManager$LoginResponse.f17338u.booleanValue()) {
                        Intent intent = new Intent(this, NewHandleActivity_.class);
                        intent.putExtra("PARAM_LOGIN_METHOD", RegistrationFlow.EMAIL);
                        startActivity(intent);
                        finish();
                        return;
                    }
                    RegistrationContext.a(this, true, RegistrationFlow.EMAIL);
                    return;
                case 69:
                    UserManager.a().a(this.f23838h.getText().toString(), this);
                    return;
                default:
                    this.f23840j.m23576a(2, getResources().getString(C1947R.string.login_failed), true);
                    MagicNetwork.a(userManager$LoginResponse.a);
                    return;
            }
        } else if (this.f23840j != null) {
            this.f23840j.m23577a(2, getResources().getString(C1947R.string.login_error_with_servers), true, getString(C1947R.string.core_ok));
        }
    }

    public void handleResponse(AccountIconResponse accountIconResponse) {
        if (accountIconResponse.a == null) {
            MagicNetwork.a(null);
            if (this.f23840j != null) {
                this.f23840j.m23577a(2, getResources().getString(C1947R.string.login_error_with_servers), true, getString(C1947R.string.core_ok));
            }
        } else if (accountIconResponse.a.c()) {
            if (this.f23840j != null) {
                this.f23840j.m23574a(2, getResources().getString(C1947R.string.settings_invalid_password), getResources().getString(C1947R.string.settings_invalid_password_text), true, getString(C1947R.string.core_ok));
            }
        } else if (accountIconResponse.a.b == PointerIconCompat.TYPE_NO_DROP || accountIconResponse.a.b == 65) {
            if (this.f23840j != null) {
                this.f23840j.dismiss();
            }
            this.f23842l = NavigationUtils.m25903a(this, this.f23838h.getText().toString(), this.f23843m, this.f23844n);
        } else {
            MagicNetwork.a(accountIconResponse.a);
            if (this.f23840j != null) {
                this.f23840j.m23577a(2, getResources().getString(C1947R.string.login_generic_login_error), true, getString(C1947R.string.core_ok));
            }
        }
    }
}
